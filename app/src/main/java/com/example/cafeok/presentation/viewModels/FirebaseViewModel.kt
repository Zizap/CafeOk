package com.example.cafeok.presentation.viewModels

import android.content.Context
import android.content.Intent
import android.util.Patterns
import androidx.core.content.ContextCompat.startActivity
import androidx.lifecycle.ViewModel
import com.example.cafeok.data.repository.FirebaseRepository
import com.example.cafeok.presentation.MainActivity
import com.example.cafeok.presentation.di.auth
import com.google.firebase.auth.FirebaseUser

class FirebaseViewModel(private val repository: FirebaseRepository): ViewModel() {

    fun registerUser(email: String, password: String, username: String, callback: (Boolean, String?) -> Unit) {
        repository.registerUser(email, password, username, callback)
    }

    fun loginUser(email: String, password: String, callback: (Boolean, String?) -> Unit) {
        repository.loginUser(email, password, callback)
    }

    fun getCurrentUser(): FirebaseUser? {
        return repository.getCurrentUser()
    }

    fun logoutUser(){
        repository.logoutUser()
    }

    fun compareEmail(email:String,context: Context){
        repository.compareEmail(email,context)
    }

    fun authenticationСheck():Boolean{
       return repository.authenticationСheck()
    }

}