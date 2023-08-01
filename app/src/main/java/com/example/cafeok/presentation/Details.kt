package com.example.cafeok.presentation

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.cafeok.R
import com.example.cafeok.data.models.CoffeeModel
import com.example.cafeok.databinding.FragmentCheckoutBinding
import com.example.cafeok.databinding.FragmentDetailsBinding
import com.example.cafeok.presentation.viewModels.BasketViewModel
import com.example.cafeok.presentation.viewModels.CoffeeViewModel
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.squareup.picasso.Picasso
import org.koin.androidx.viewmodel.ext.android.viewModel


class Details(coffeeModel: CoffeeModel,private val openDialogAddCoffee:(CoffeeModel)->Unit,
              private val deleteCoffee:(CoffeeModel)->Unit) : BottomSheetDialogFragment() {


    private var _binding: FragmentDetailsBinding? = null
    private val binding get() = _binding!!
    private var idCoffee:Int? = null
    private val coffee = coffeeModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentDetailsBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        idCoffee = arguments?.getString("idCoffee")?.toInt()

        binding.coffeeName.setText(arguments?.getString("nameCoffee").toString())

        val price:String = arguments?.getString("priceCoffee").toString()
        binding.coffeePrice.setText("$$price")

        binding.textView.setText(arguments?.getString("descriptionCoffee").toString())

        val getImage = arguments?.getString("imageCoffee").toString()
        Picasso.get().load(getImage).into(binding.coffeeImage)

        binding.buttonMinus.setOnClickListener(View.OnClickListener {
            deleteCoffee(coffee)
        })
        binding.buttonPlus.setOnClickListener(View.OnClickListener {
            openDialogAddCoffee(coffee)
        })
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}