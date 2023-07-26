package com.example.cafeok.data.repository

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

class FirebaseRepository(private val firebaseDataSource: FirebaseDataSource) {

    fun registerUser(email: String, password: String, username: String, callback: (Boolean, String?) -> Unit) {
        firebaseDataSource.registerUser(email,password,username,callback)
    }

    fun loginUser(email: String, password: String, callback: (Boolean, String?) -> Unit) {
        firebaseDataSource.loginUser(email,password,callback)
    }

    fun getCurrentUser(): FirebaseUser? {
        return firebaseDataSource.getCurrentUser()
    }


    fun logoutUser() {
        firebaseDataSource.logoutUser()
    }

    fun compareEmail(email:String,context: Context){
        firebaseDataSource.compareEmail(email,context)
    }

    fun authenticationCheck():Boolean{
            return firebaseDataSource.authenticationCheck()
    }

    fun readDataFromDatabase(): LiveData<UserModel?> {
        return firebaseDataSource.readDataFromDatabase()
    }

}
