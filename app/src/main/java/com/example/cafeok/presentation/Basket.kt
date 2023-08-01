package com.example.cafeok.presentation

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.cafeok.R
import com.example.cafeok.data.models.BuyBasketModel
import com.example.cafeok.data.models.CoffeeModel
import com.example.cafeok.databinding.FragmentAddCoffeeDialogBinding
import com.example.cafeok.databinding.FragmentBasketBinding
import com.example.cafeok.presentation.adapters.BasketAdapter
import com.example.cafeok.presentation.adapters.CatalogAdapter
import com.example.cafeok.presentation.viewModels.BasketViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel


class Basket : Fragment() {


    private var _binding: FragmentBasketBinding? = null
    private val binding get() = _binding!!
    private var basketAdapter: BasketAdapter? = null
    private val basketViewModel: BasketViewModel by viewModel()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentBasketBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        initRecyclerCoffee()
        loadCoffeeFromBasket()
        getTotalSum()

        binding.buttonClear.setOnClickListener(View.OnClickListener {
            basketViewModel.clearBasket()
        })

    }

    private fun initRecyclerCoffee() {
        binding.recyclerBasket.layoutManager = LinearLayoutManager(context)
        basketAdapter =
            BasketAdapter({ buyBasketModel: BuyBasketModel -> addCoffee(buyBasketModel) },
                { buyBasketModel: BuyBasketModel -> deleteCoffee(buyBasketModel) },
                { buyBasketModel: BuyBasketModel -> openDescription(buyBasketModel) })
        binding.recyclerBasket.adapter = basketAdapter
    }

    private fun loadCoffeeFromBasket() {
        basketViewModel.loadCoffeeFromBasket.observe(viewLifecycleOwner, Observer {
            basketAdapter?.setList(it)
            basketAdapter?.notifyDataSetChanged()
        })
    }

    private fun addCoffee(buyBasketModel: BuyBasketModel) {
        basketViewModel.add(buyBasketModel)
    }

    private fun deleteCoffee(buyBasketModel: BuyBasketModel) {
       basketViewModel.delete(buyBasketModel)
    }

    private fun getTotalSum(){
        var sum = 0
        basketViewModel.loadCoffeeFromBasket.observe(viewLifecycleOwner, Observer {
            allElements ->
            sum = 0
            for (element in allElements){
                sum += (element.price!!.toInt() * element.count!!.toInt())
                Log.e("Check image", element.image2.toString())
            }
            binding.sumTotal.post {
                binding.sumTotal.text = "$$sum"
            }
        })
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun openDescription(buyBasketModel: BuyBasketModel) {

    }
}