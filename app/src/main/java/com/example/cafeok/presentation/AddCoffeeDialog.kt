package com.example.cafeok.presentation

import android.os.Bundle
import android.view.Gravity
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.cafeok.R
import com.example.cafeok.constants.Constants
import com.example.cafeok.data.models.CoffeeModel
import com.example.cafeok.databinding.FragmentAddCoffeeDialogBinding
import com.google.android.material.bottomsheet.BottomSheetDialogFragment


class AddCoffeeDialog(coffeeModel: CoffeeModel,
                      private val addCoffee:(Int,CoffeeModel)->Unit): BottomSheetDialogFragment() {

    private var binding:FragmentAddCoffeeDialogBinding? = null
    private var count:Int = Constants.NUMBER_ONE
    private val coffee = coffeeModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentAddCoffeeDialogBinding.inflate(inflater,container,false)

        dialog?.window?.setGravity(Gravity.CENTER)

        binding?.countCoffee?.text = count.toString()
        //binding?.coffeeName?.setText(arguments?.getString("nameCoffee").toString())

        val name = arguments?.getString("nameCoffee").toString()

        binding?.buttonAddCoffee?.text = "Add $name to basket"

        binding?.buttonPlus?.setOnClickListener(View.OnClickListener {
            count += 1
            binding?.countCoffee?.text = count.toString()
        })

        binding?.buttonMinus?.setOnClickListener(View.OnClickListener {
            if (count>=1) {
                count -= 1
                binding?.countCoffee?.text = count.toString()
            }
        })

        binding?.buttonAddCoffee?.setOnClickListener(View.OnClickListener {
            addCoffee(count,coffee)
        })

        return binding?.root
    }

}