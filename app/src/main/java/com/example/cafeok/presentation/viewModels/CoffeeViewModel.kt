package com.example.cafeok.presentation.viewModels

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.cafeok.data.models.CoffeeModel
import com.example.cafeok.domain.useCase.CoffeeUseCase
import kotlinx.coroutines.launch

class CoffeeViewModel(private val coffeeUseCase: CoffeeUseCase): ViewModel() {

    var loadCoffee = coffeeUseCase.loadCoffee()

    fun searchCoffee(query: String) {
        loadCoffee = if (query.isEmpty()) {
            loadCoffee
        } else {
            searchCoffeeByName(query)
        }
    }

    private fun searchCoffeeByName(searchName: String): LiveData<List<CoffeeModel>> {
        return coffeeUseCase.searchCoffeeByName(searchName)
    }

    fun migration(context: Context) = viewModelScope.launch {
        coffeeUseCase.startMigration(context)
    }

}