package com.example.cafeok.data.dataSourceIMPL

import android.content.Context
import android.util.Patterns
import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.cafeok.data.dataSource.FirebaseDataSource
import com.example.cafeok.data.models.UserModel
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

class FirebaseDataSourceIMPL:FirebaseDataSource {

    private val auth: FirebaseAuth = FirebaseAuth.getInstance()
    private val databaseReference: DatabaseReference = FirebaseDatabase.getInstance().reference

    override fun registerUser(email: String, password: String, username: String, callback: (Boolean, String?) -> Unit) {
        auth.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    val user = auth.currentUser
                    user?.let {
                        val userId = it.uid
                        val newUser = UserModel(userId, username, email, password)
                        databaseReference.child("users").child(userId).setValue(newUser)
                        callback(true, null)
                    }
                } else {
                    callback(false, task.exception?.message)
                }
            }
    }

    override fun loginUser(email: String, password: String, callback: (Boolean, String?) -> Unit) {
        auth.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    callback(true, null)
                } else {
                    callback(false, task.exception?.message)
                }
            }
    }

    override fun getCurrentUser(): FirebaseUser? {
        return auth.currentUser
    }


    override fun logoutUser() {
        auth.signOut()
    }

    override fun compareEmail(email:String,context: Context){
        if (email.isEmpty()){
            Toast.makeText(context, "Email should not be empty", Toast.LENGTH_SHORT).show()
            return
        }
        if (!Patterns.EMAIL_ADDRESS.matcher(email.toString()).matches()){
            Toast.makeText(context, "Invalid email format", Toast.LENGTH_SHORT).show()
            return
        }
        auth.sendPasswordResetEmail(email).addOnCompleteListener {task ->
            if (task.isSuccessful){
                Toast.makeText(context, "Check your email for password reset instructions", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(context, "Failed to send password reset email", Toast.LENGTH_SHORT).show()
            }
        }
    }

    override fun authenticationCheck():Boolean{
        return auth.currentUser!=null
    }

    override fun readDataFromDatabase(): LiveData<UserModel?> {
        val currentUser = getCurrentUser()
        val data = MutableLiveData<UserModel?>()

        currentUser?.uid?.let { userId ->
            databaseReference.child("users").child(userId).addListenerForSingleValueEvent(object : ValueEventListener {
                override fun onDataChange(dataSnapshot: DataSnapshot) {
                    val user = dataSnapshot.getValue(UserModel::class.java)
                    data.value = user
                }

                override fun onCancelled(databaseError: DatabaseError) {
                    data.value = null
                }
            })
        }

        return data
    }

}