package com.example.cafeok.domain.repository

import android.content.Context
import androidx.lifecycle.LiveData
import com.example.cafeok.data.models.CoffeeModel

interface CoffeeCall {

    fun loadCoffee(): LiveData<List<CoffeeModel>>

    suspend fun startMigration(context: Context)

    fun searchCoffeeByName(searchName: String):LiveData<List<CoffeeModel>>

}