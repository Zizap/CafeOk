package com.example.cafeok.data.dataSource

import android.content.Context
import androidx.lifecycle.LiveData
import com.example.cafeok.data.models.UserModel
import com.google.firebase.auth.FirebaseUser

interface FirebaseDataSource {

    fun registerUser(email: String, password: String, username: String, callback: (Boolean, String?) -> Unit)
    fun loginUser(email: String, password: String, callback: (Boolean, String?) -> Unit)
    fun getCurrentUser(): FirebaseUser?
    fun logoutUser()
    fun compareEmail(email: String, context: Context)
    fun authenticationCheck(): Boolean
    fun readDataFromDatabase(): LiveData<UserModel?>

    }