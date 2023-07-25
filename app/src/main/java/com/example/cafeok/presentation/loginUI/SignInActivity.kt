package com.example.cafeok.presentation.loginUI

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Patterns
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import com.example.cafeok.R
import com.example.cafeok.databinding.ActivitySignInBinding
import com.example.cafeok.presentation.MainActivity
import com.example.cafeok.presentation.viewModels.FirebaseViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class SignInActivity : AppCompatActivity() {

    var binding: ActivitySignInBinding? = null
    val firebaseViewModel: FirebaseViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivitySignInBinding.inflate(layoutInflater)

        binding?.signIn?.setOnClickListener {

            if (binding?.email?.text!!.isEmpty() || binding?.pass?.text!!.isEmpty()){


                if (binding?.email?.text!!.isEmpty()){
                    Toast.makeText(this, "Enter email", Toast.LENGTH_SHORT).show()
                    binding?.email?.requestFocus()
                }

                else if (binding?.pass?.text!!.isEmpty()){
                    Toast.makeText(this, "Enter password", Toast.LENGTH_SHORT).show()
                    binding?.pass?.requestFocus()
                }


            }
            else  if (!Patterns.EMAIL_ADDRESS.matcher(binding?.email?.text.toString()).matches()){
                Toast.makeText(this, "Wrong email address", Toast.LENGTH_SHORT).show()
                binding?.email?.requestFocus()
            } else {
                val email = binding?.email?.text.toString()
                val pass = binding?.pass?.text.toString()
                firebaseViewModel.loginUser(email, pass) { isSuccess, errorMessage ->
                    if (isSuccess) {
                        Toast.makeText(this, "Login Successful", Toast.LENGTH_SHORT).show()
                        val currentUser = firebaseViewModel.getCurrentUser()
                        startActivity(Intent(this,MainActivity::class.java))
                        finish()
                    } else {
                        Toast.makeText(this, "Error Login: $errorMessage", Toast.LENGTH_SHORT).show()
                    }
                }
            }

        }

        binding?.resetPass?.setOnClickListener {
            val builder = AlertDialog.Builder(this)
            val view = layoutInflater.inflate(R.layout.reset_email_dialog,null)
            val userEmail = view.findViewById<EditText>(R.id.email_reset)
            builder.setView(view)
            val dialog = builder.create()
            view.findViewById<Button>(R.id.reset_button).setOnClickListener {
                firebaseViewModel.compareEmail(userEmail.text.toString(),this)
                dialog.dismiss()
            }
            dialog.show()
        }

        setContentView(binding?.root)
    }




}