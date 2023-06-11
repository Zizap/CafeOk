package com.example.cafeok.presentation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Observer
import com.example.cafeok.R
import com.example.cafeok.databinding.ActivityMainBinding
import com.example.cafeok.presentation.viewModels.BasketViewModel
import com.example.cafeok.presentation.viewModels.CoffeeViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : AppCompatActivity() {

    private var binding:ActivityMainBinding? = null
    private val coffeeViewModel: CoffeeViewModel by viewModel()
    private val basketViewModel: BasketViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)

        coffeeViewModel.migration(this)

        supportFragmentManager.beginTransaction().replace(R.id.mainContent,Home()).commit()
        binding?.bottomMainMenu?.selectedItemId = R.id.bottomMenuHome

        binding?.bottomMainMenu?.setOnItemSelectedListener { item ->
            when (item.itemId) {
                R.id.bottomMenuHome -> supportFragmentManager.beginTransaction().replace(R.id.mainContent, Home()).commit()
                R.id.bottomMenuAccount -> supportFragmentManager.beginTransaction().replace(R.id.mainContent, Account()).commit()
                R.id.bottomMenuCard -> supportFragmentManager.beginTransaction().replace(R.id.mainContent, Basket()).commit()
                R.id.bottomMenuCoffee -> supportFragmentManager.beginTransaction().replace(R.id.mainContent, Coffee()).commit()
                R.id.bottomMenuFavorite -> supportFragmentManager.beginTransaction().replace(R.id.mainContent, Favorite()).commit()

            }
            return@setOnItemSelectedListener true
        }

        setContentView(binding?.root)
    }

    private fun loadCoffeeToBasket(){
        basketViewModel.loadCoffeeFromBasket.observe(this, Observer {
            val count = it.count()
            val badge = binding?.bottomMainMenu?.getOrCreateBadge(R.id.bottomMenuCard)
            badge?.isVisible = count>0
            badge?.number = count
        })
    }

}