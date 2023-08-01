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

    private val firebaseViewModel: FirebaseViewModel by viewModel()
    private var _binding: FragmentAccountBinding? = null
    private val binding get() = _binding!!
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentAccountBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding.signOut.setOnClickListener {
            firebaseViewModel.logoutUser()
            Toast.makeText(requireActivity(), "You are logged out", Toast.LENGTH_SHORT).show()
            val intent = Intent(requireActivity(),Login::class.java)
            startActivity(intent)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}