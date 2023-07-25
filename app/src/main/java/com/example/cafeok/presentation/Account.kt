package com.example.cafeok.presentation

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.FragmentActivity
import com.example.cafeok.R
import com.example.cafeok.databinding.FragmentAccountBinding
import com.example.cafeok.presentation.loginUI.Login
import com.example.cafeok.presentation.viewModels.FirebaseViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel


class Account : Fragment() {

    val firebaseViewModel: FirebaseViewModel by viewModel()

    var binding: FragmentAccountBinding? = null
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentAccountBinding.inflate(inflater,container,false)

        binding?.signOut?.setOnClickListener {
            firebaseViewModel.logoutUser()
            Toast.makeText(context as FragmentActivity, "You are logged out", Toast.LENGTH_SHORT).show()
            val intent = Intent(context as FragmentActivity,Login::class.java)
            startActivity(intent)
        }

        return binding?.root
    }

}