package com.example.cafeok.domain.useCase

import android.content.Context
import androidx.lifecycle.LiveData
import com.example.cafeok.data.models.CoffeeModel
import com.example.cafeok.domain.repository.CoffeeCall

class CoffeeUseCase(private val coffeeCall: CoffeeCall) {

    fun loadCoffee(): LiveData<List<CoffeeModel>> {
        return coffeeCall.loadCoffee()
    }

    suspend fun startMigration(context: Context){
        coffeeCall.startMigration(context)
    }

    fun searchCoffeeByName(searchName: String): LiveData<List<CoffeeModel>> {
        return coffeeCall.searchCoffeeByName(searchName)
    }


}