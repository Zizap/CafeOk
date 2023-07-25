package com.example.cafeok.presentation.viewModels

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.cafeok.data.models.CoffeeModel
import com.example.cafeok.data.repository.CoffeeRepository
import kotlinx.coroutines.launch

class CoffeeViewModel(private val coffeeRepository: CoffeeRepository): ViewModel() {

    var loadCoffee = coffeeRepository.loadCoffee()

    fun searchCoffee(query: String) {
        loadCoffee = if (query.isEmpty()) {
            loadCoffee
        } else {
            searchCoffeeByName(query)
        }
    }

    private fun searchCoffeeByName(searchName: String): LiveData<List<CoffeeModel>> {
        return coffeeRepository.searchCoffeeByName(searchName)
    }

    fun migration(context: Context) = viewModelScope.launch {
        coffeeRepository.startMigration(context)
    }

}