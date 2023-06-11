package com.example.cafeok.data.repository

import android.content.Context
import androidx.lifecycle.LiveData
import com.example.cafeok.data.dataSource.CoffeeApiDataSource
import com.example.cafeok.data.dataSource.CoffeeDataSource
import com.example.cafeok.data.models.CoffeeModel
import com.example.cafeok.domain.repository.CoffeeCall

class CoffeeRepository(private val coffeeApiDataSource: CoffeeApiDataSource,
                       private val coffeeDataSource: CoffeeDataSource) : CoffeeCall {

    override fun loadCoffee(): LiveData<List<CoffeeModel>> {
        return coffeeDataSource.loadCoffee()
    }

    override suspend fun startMigration(context: Context) {
        coffeeDataSource.clear()
        coffeeApiDataSource.startMigration(context)
    }

    override fun searchCoffeeByName(searchName: String): LiveData<List<CoffeeModel>> {
        return coffeeDataSource.searchCoffeeByName(searchName)
    }
}