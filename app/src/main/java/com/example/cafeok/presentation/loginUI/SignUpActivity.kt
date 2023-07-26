package com.example.cafeok.presentation.loginUI

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Patterns
import android.widget.Toast
import androidx.lifecycle.ViewModel
import com.example.cafeok.R
import com.example.cafeok.databinding.ActivitySignUpBinding
import com.example.cafeok.presentation.MainActivity
import com.example.cafeok.presentation.viewModels.FirebaseViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class SignUpActivity : AppCompatActivity() {

    private var binding: ActivitySignUpBinding? = null
    private val firebaseViewModel: FirebaseViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivitySignUpBinding.inflate(layoutInflater)

        binding?.signUp?.setOnClickListener {


            if (binding?.email?.text!!.isEmpty() || binding?.pass?.text!!.isEmpty() || binding?.name?.text!!.isEmpty() || binding?.pass2?.text!!.isEmpty()){

                if (binding?.name?.text!!.isEmpty()){
                    Toast.makeText(this, "Enter name", Toast.LENGTH_SHORT).show()
                    binding?.name?.requestFocus()
                }

                else if (binding?.email?.text!!.isEmpty()){
                    Toast.makeText(this, "Enter email", Toast.LENGTH_SHORT).show()
                    binding?.email?.requestFocus()
                }

                else if (binding?.pass?.text!!.isEmpty()){
                    Toast.makeText(this, "Enter password", Toast.LENGTH_SHORT).show()
                    binding?.pass?.requestFocus()
                }

                else if (binding?.pass2?.text!!.isEmpty()){
                    Toast.makeText(this, "Repeat password", Toast.LENGTH_SHORT).show()
                    binding?.pass?.requestFocus()
                }


            }
            else  if (!Patterns.EMAIL_ADDRESS.matcher(binding?.email?.text.toString()).matches()){
                Toast.makeText(this, "Wrong email address", Toast.LENGTH_SHORT).show()
                binding?.email?.requestFocus()
            }
            else if (!binding?.pass?.text.toString().equals(binding?.pass2?.text.toString())){
                Toast.makeText(this, "Wrong password", Toast.LENGTH_SHORT).show()
                binding?.pass2?.requestFocus()
            }
            else {
                val name = binding?.name?.text.toString()
                val email = binding?.email?.text.toString()
                val pass = binding?.pass?.text.toString()
                firebaseViewModel.registerUser(email,pass,name) { isSuccess, errorMessage ->
                    if (isSuccess) {
                        Toast.makeText(this, "Registration successful", Toast.LENGTH_SHORT).show()
                        val currentUser = firebaseViewModel.getCurrentUser()
                        startActivity(Intent(this,MainActivity::class.java))
                        finish()
                    } else {
                        Toast.makeText(this, "Registration error: $errorMessage", Toast.LENGTH_SHORT).show()
                    }
                }
            }

        }

        setContentView(binding?.root)
    }
}