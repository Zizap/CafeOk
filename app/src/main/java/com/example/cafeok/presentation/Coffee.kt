package com.example.cafeok.presentation

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import android.widget.Toast
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.cafeok.R
import com.example.cafeok.data.models.CoffeeModel
import com.example.cafeok.databinding.FragmentCoffeeBinding
import com.example.cafeok.presentation.adapters.CatalogAdapter
import com.example.cafeok.presentation.viewModels.BasketViewModel
import com.example.cafeok.presentation.viewModels.CoffeeViewModel
import com.example.cafeok.presentation.viewModels.FirebaseViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel


class Coffee : Fragment() {


    private var _binding: FragmentCoffeeBinding? = null
    private val binding get() = _binding!!
    private var catalogAdapter: CatalogAdapter? = null
    private val coffeeViewModel: CoffeeViewModel by viewModel()
    private val basketViewModel: BasketViewModel by viewModel()
    private val firebaseViewModel: FirebaseViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentCoffeeBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        initRecyclerCoffee()
        loadCoffee()

        val animation = AnimationUtils.loadAnimation(context as FragmentActivity,R.anim.animation_for_button)
        binding.textMorning.startAnimation(animation)

        firebaseViewModel.fireData.observe(viewLifecycleOwner, Observer { userData ->
            if (userData != null) {
                binding.textMorning.text = "Good Morning ${userData.username}!"
                Log.e("UserName", userData.username)
            } else {
                Toast.makeText(requireActivity(), "Error from textMorning", Toast.LENGTH_SHORT).show()
            }
        })

        binding.searchTF.addTextChangedListener(object: TextWatcher {

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                val query = s?.toString()?:""
                coffeeViewModel.searchCoffee("%$query%")
                loadCoffee()
            }

            override fun afterTextChanged(s: Editable?) {}
        })
    }

    private fun initRecyclerCoffee(){
        binding.recyclerCoffee.layoutManager = LinearLayoutManager(context)
        catalogAdapter = CatalogAdapter({   coffeeModel: CoffeeModel -> openDialogAddCoffee(coffeeModel)},
            {coffeeModel: CoffeeModel -> deleteCoffee(coffeeModel)}, { coffeeModel: CoffeeModel -> openDescription(coffeeModel)})
        binding.recyclerCoffee.adapter = catalogAdapter
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
            coffeeModel.image2,
            coffeeModel.price,
            coffeeModel.id.toString(),
            count.toString())
    }

    private fun openDialogAddCoffee(coffeeModel: CoffeeModel){
        val panelDialogAddCoffee = AddCoffeeDialog(coffeeModel,{count:Int,coffeeModel: CoffeeModel -> addCoffee(coffeeModel,count)})
        val parameters = Bundle()
        parameters.putString("nameCoffee", coffeeModel.name)
        panelDialogAddCoffee.arguments = parameters
        panelDialogAddCoffee.show((requireActivity()).supportFragmentManager, "DialogAddCoffee")
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

        panelDescription.show((requireActivity()).supportFragmentManager, "panelDescription")
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}