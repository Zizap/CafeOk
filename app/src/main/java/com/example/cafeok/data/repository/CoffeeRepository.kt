package com.example.cafeok.data.repository

import android.content.Context
import androidx.lifecycle.LiveData
import com.example.cafeok.data.dataSource.CoffeeApiDataSource
import com.example.cafeok.data.dataSource.CoffeeDataSource
import com.example.cafeok.data.models.CoffeeModel

class CoffeeRepository(private val coffeeApiDataSource: CoffeeApiDataSource,
                       private val coffeeDataSource: CoffeeDataSource) {

    fun loadCoffee(): LiveData<List<CoffeeModel>> {
        return coffeeDataSource.loadCoffee()
    }

    suspend fun startMigration(context: Context) {
        coffeeDataSource.clear()
        coffeeApiDataSource.startMigration(context)
    }

    fun searchCoffeeByName(searchName: String): LiveData<List<CoffeeModel>> {
        return coffeeDataSource.searchCoffeeByName(searchName)
    }
}