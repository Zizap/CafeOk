package com.example.cafeok.presentation

import android.os.Bundle
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
import com.example.cafeok.databinding.FragmentBasketBinding
import com.example.cafeok.presentation.adapters.BasketAdapter
import com.example.cafeok.presentation.adapters.CatalogAdapter
import com.example.cafeok.presentation.viewModels.BasketViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel


class Basket : Fragment() {

    private var binding: FragmentBasketBinding? = null
    private var basketAdapter: BasketAdapter? = null
    private val basketViewModel: BasketViewModel by viewModel()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentBasketBinding.inflate(inflater, container, false)

        initRecyclerCoffee()
        loadCoffeeFromBasket()
        getTotalSum()

        binding?.buttonClear?.setOnClickListener(View.OnClickListener {
            basketViewModel.clearBasket()
        })

        return binding?.root
    }

    private fun initRecyclerCoffee() {
        binding?.recyclerBasket?.layoutManager = LinearLayoutManager(context)
        basketAdapter =
            BasketAdapter({ buyBasketModel: BuyBasketModel -> addCoffee(buyBasketModel) },
                { buyBasketModel: BuyBasketModel -> deleteCoffee(buyBasketModel) },
                { buyBasketModel: BuyBasketModel -> openDescription(buyBasketModel) })
        binding?.recyclerBasket?.adapter = basketAdapter
    }

    private fun loadCoffeeFromBasket() {
        basketViewModel.loadCoffeeFromBasket.observe(viewLifecycleOwner, Observer {
            basketAdapter?.setList(it)
            basketAdapter?.notifyDataSetChanged()
        })
    }

    private fun addCoffee(buyBasketModel: BuyBasketModel) {
        var count = buyBasketModel.count!!.toInt()
        count += 1
        basketViewModel.startUpdateCoffeeFromBasket(
            buyBasketModel.id,
            buyBasketModel.image2,
            buyBasketModel.name,
            buyBasketModel.price,
            count.toString(),
            buyBasketModel.idProduct
        )
    }

    private fun deleteCoffee(buyBasketModel: BuyBasketModel) {
        if (buyBasketModel.count!!.toInt()<=1){
            basketViewModel.deleteCoffeeToBasketFromBasket(buyBasketModel.id.toString())
        } else {
            var count = buyBasketModel.count!!.toInt()
            count -= 1
            basketViewModel.startUpdateCoffeeFromBasket(
                buyBasketModel.id,
                buyBasketModel.image2,
                buyBasketModel.name,
                buyBasketModel.price,
                count.toString(),
                buyBasketModel.idProduct
            )
        }

    }

    fun getTotalSum(){

        val coffeeList = basketViewModel.loadCoffeeFromBasket

        var sum = 0

        coffeeList.observe(viewLifecycleOwner, Observer { allElements ->
            sum = 0
            for (element in allElements){
                sum = sum + (element.price!!.toInt() * element.count!!.toInt())
            }

            binding?.sumTotal?.post {
                binding?.sumTotal?.text = "$$sum"
            }
        })

    }

    private fun openDescription(buyBasketModel: BuyBasketModel) {

    }
}