package com.example.cafeok.data.dataSource

import androidx.lifecycle.LiveData
import com.example.cafeok.data.models.CoffeeModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

interface CoffeeDataSource {

    fun insert(coffeeModel: CoffeeModel)

    fun loadCoffee(): LiveData<List<CoffeeModel>>

    suspend fun clear()

    fun searchCoffeeByName(searchName: String):LiveData<List<CoffeeModel>>

}