package com.example.cafeok.data.repository

import android.content.Context
import android.util.Patterns
import android.widget.Toast
import com.example.cafeok.data.models.UserModel
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class FirebaseRepository {

    private val auth: FirebaseAuth = FirebaseAuth.getInstance()
    private val databaseReference: DatabaseReference = FirebaseDatabase.getInstance().reference

    fun registerUser(email: String, password: String, username: String, callback: (Boolean, String?) -> Unit) {
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

    fun loginUser(email: String, password: String, callback: (Boolean, String?) -> Unit) {
        auth.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    callback(true, null)
                } else {
                    callback(false, task.exception?.message)
                }
            }
    }

    fun getCurrentUser(): FirebaseUser? {
        return auth.currentUser
    }


    fun logoutUser() {
        auth.signOut()
    }

    fun compareEmail(email:String,context: Context){
        if (email.isEmpty()){
            Toast.makeText(context, "Email should not be empty", Toast.LENGTH_SHORT).show()
            return
        }
        if (!Patterns.EMAIL_ADDRESS.matcher(email.toString()).matches()){
            Toast.makeText(context, "Invalid email format", Toast.LENGTH_SHORT).show()
            //Toast.makeText(context, "${email.toString()}", Toast.LENGTH_SHORT).show()
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

    fun authenticationСheck():Boolean{
            return auth.currentUser!=null
    }

}
