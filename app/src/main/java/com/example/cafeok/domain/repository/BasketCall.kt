package com.example.cafeok.domain.repository

import android.content.Context
import androidx.lifecycle.LiveData
import com.example.cafeok.data.models.BuyBasketModel
import com.example.cafeok.data.models.CoffeeModel

interface BasketCall {

    suspend fun insertCoffeeToBasket(buyBasketModel: BuyBasketModel)

    suspend fun updateCoffeeFromBasket(buyBasketModel: BuyBasketModel)

    fun loadCoffeeFromBasket(): LiveData<List<BuyBasketModel>>

    fun getAllCoffeeToBasketFromBasket(idProduct:String): LiveData<List<BuyBasketModel>>

    suspend fun deleteCoffeeToBasketFromBasket(idProduct:String)

    suspend fun deleteCoffeeFromBasket(idProductToBasket:Int)

    suspend fun clear()

}