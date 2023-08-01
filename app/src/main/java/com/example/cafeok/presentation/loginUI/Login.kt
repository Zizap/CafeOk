package com.example.cafeok.presentation.loginUI

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.AttributeSet
import android.view.View
import android.view.animation.AnimationUtils
import android.widget.Toast
import com.example.cafeok.R
import com.example.cafeok.databinding.ActivityLoginBinding
import com.example.cafeok.presentation.MainActivity
import com.example.cafeok.presentation.di.auth
import com.example.cafeok.presentation.viewModels.FirebaseViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class Login : AppCompatActivity() {

    private lateinit var binding: ActivityLoginBinding
    private val firebaseViewModel: FirebaseViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityLoginBinding.inflate(layoutInflater)


        binding.signIn.setOnClickListener {
           val intent = Intent(this,SignInActivity::class.java)
            startActivity(intent)
        }

        binding.signUp.setOnClickListener {
            val intent = Intent(this,SignUpActivity::class.java)
            startActivity(intent)
        }

        binding.next.setOnClickListener {
            val intent = Intent(this,MainActivity::class.java)
            startActivity(intent)
            finish()
        }

        setContentView(binding.root)
    }

    override fun onCreateView(name: String, context: Context, attrs: AttributeSet): View? {

        val animation = AnimationUtils.loadAnimation(this, R.anim.animation_for_login_icon)
        val animation2 = AnimationUtils.loadAnimation(this,R.anim.animation_for_button)

        binding.tvName.startAnimation(animation)
        binding.appCompatImageView.startAnimation(animation)

        binding.signIn.startAnimation(animation2)
        binding.signUp.startAnimation(animation2)
        binding.next.startAnimation(animation2)



        return super.onCreateView(name, context, attrs)
    }

    override fun onStart() {
        super.onStart()
        if(firebaseViewModel.authenticationСheck()){
            Toast.makeText(this, "Login Successful", Toast.LENGTH_SHORT).show()
            val intent = Intent(this,MainActivity::class.java)
            startActivity(intent)
            finish()
        }
    }
}