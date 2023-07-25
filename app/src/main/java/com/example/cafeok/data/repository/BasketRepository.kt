package com.example.cafeok.data.repository

import android.content.Context
import androidx.lifecycle.LiveData
import com.example.cafeok.data.localDB.BasketDao
import com.example.cafeok.data.models.BuyBasketModel
import com.example.cafeok.data.models.CoffeeModel

class BasketRepository(private val basketDao: BasketDao
) {

    suspend fun insertCoffeeToBasket(buyBasketModel: BuyBasketModel){
        basketDao.insertCoffeeToBasket(buyBasketModel)
    }

    suspend fun updateCoffeeFromBasket(buyBasketModel: BuyBasketModel) {
        basketDao.updateCoffeeFromBasket(buyBasketModel)
    }

    fun loadCoffeeFromBasket(): LiveData<List<BuyBasketModel>> {
        return basketDao.getAllCoffeeFromBasket()
    }

    fun getAllCoffeeToBasketFromBasket(idProduct:String): LiveData<List<BuyBasketModel>> {
        return basketDao.getAllCoffeeToBasketFromBasket(idProduct)
    }

    suspend fun deleteCoffeeToBasketFromBasket(idProduct: String) {
        basketDao.deleteCoffeeToBasketFromBasket(idProduct)
    }

    suspend fun deleteCoffeeFromBasket(idProductToBasket: Int) {
        basketDao.deleteCoffeeFromBasket(idProductToBasket)
    }

    suspend fun clear(){
        basketDao.clearBasket()
    }


}