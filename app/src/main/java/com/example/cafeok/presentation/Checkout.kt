package com.example.cafeok.presentation

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.cafeok.R
import com.example.cafeok.databinding.FragmentCheckoutBinding
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

class Checkout : BottomSheetDialogFragment() {

    var binding: FragmentCheckoutBinding? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentCheckoutBinding.inflate(inflater,container,false)



        return binding?.root
    }


}