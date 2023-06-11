package com.example.cafeok.presentation

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
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
import com.example.cafeok.databinding.FragmentCoffeeBinding
import com.example.cafeok.presentation.adapters.CatalogAdapter
import com.example.cafeok.presentation.viewModels.BasketViewModel
import com.example.cafeok.presentation.viewModels.CoffeeViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel


class Coffee : Fragment() {

    private var binding: FragmentCoffeeBinding? = null
    private var catalogAdapter: CatalogAdapter? = null
    private val coffeeViewModel: CoffeeViewModel by viewModel()
    private val basketViewModel: BasketViewModel by viewModel()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentCoffeeBinding.inflate(inflater,container,false)

        initRecyclerCoffee()
        loadCoffee()

        binding?.searchTF?.addTextChangedListener(object: TextWatcher {

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                val query = s?.toString()?:""
                coffeeViewModel.searchCoffee("%$query%")
                loadCoffee()
            }

            override fun afterTextChanged(s: Editable?) {}
        })
        return binding?.root
    }

    private fun initRecyclerCoffee(){
        binding?.recyclerCoffee?.layoutManager = LinearLayoutManager(context)
        catalogAdapter = CatalogAdapter({   coffeeModel: CoffeeModel -> openDialogAddCoffee(coffeeModel)},
            {coffeeModel: CoffeeModel -> deleteCoffee(coffeeModel)}, { coffeeModel: CoffeeModel -> openDescription(coffeeModel)})
        binding?.recyclerCoffee?.adapter = catalogAdapter
    }

    private fun loadCoffee(){
        coffeeViewModel.loadCoffee.observe(viewLifecycleOwner, Observer {
            catalogAdapter?.setList(it)
            catalogAdapter?.notifyDataSetChanged()
        })
    }

    private fun addCoffee(coffeeModel: CoffeeModel, count:Int){
        basketViewModel.startInsert(
            coffeeModel.id,
            coffeeModel.name,
            coffeeModel.image,
            coffeeModel.price,
            coffeeModel.id.toString(),
            count.toString())
    }

    private fun openDialogAddCoffee(coffeeModel: CoffeeModel){
        val panelDialogAddCoffee = AddCoffeeDialog(coffeeModel,{count:Int,coffeeModel: CoffeeModel -> addCoffee(coffeeModel,count)})
        val parameters = Bundle()
        parameters.putString("nameCoffee", coffeeModel.name)
        panelDialogAddCoffee.arguments = parameters
        panelDialogAddCoffee.show((context as FragmentActivity).supportFragmentManager, "DialogAddCoffee")
    }

    private fun deleteCoffee(coffeeModel: CoffeeModel){
        basketViewModel.deleteCoffeeToBasketFromBasket(coffeeModel.id.toString())
    }

    private fun openDescription(coffeeModel: CoffeeModel){
        val panelDescription = Details(coffeeModel,{coffeeModel: CoffeeModel -> openDialogAddCoffee(coffeeModel)},
                                          {coffeeModel: CoffeeModel -> deleteCoffee(coffeeModel)})
        val parameters = Bundle()

        parameters.putString("idCoffee", coffeeModel.id.toString())
        parameters.putString("imageCoffee", coffeeModel.image)
        parameters.putString("nameCoffee", coffeeModel.name)
        parameters.putString("priceCoffee", coffeeModel.price)
        parameters.putString("descriptionCoffee", coffeeModel.description)
        panelDescription.arguments = parameters

        panelDescription.show((context as FragmentActivity).supportFragmentManager, "panelDescription")
    }


}