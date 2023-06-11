package com.example.cafeok.data.repository

import android.content.Context
import androidx.lifecycle.LiveData
import com.example.cafeok.data.localDB.BasketDao
import com.example.cafeok.data.models.BuyBasketModel
import com.example.cafeok.data.models.CoffeeModel
import com.example.cafeok.domain.repository.BasketCall
import com.example.cafeok.domain.repository.CoffeeCall

class BasketRepository(private val basketDao: BasketDao
) : BasketCall {

    override suspend fun insertCoffeeToBasket(buyBasketModel: BuyBasketModel){
        basketDao.insertCoffeeToBasket(buyBasketModel)
    }

    override suspend fun updateCoffeeFromBasket(buyBasketModel: BuyBasketModel) {
        basketDao.updateCoffeeFromBasket(buyBasketModel)
    }

    override fun loadCoffeeFromBasket(): LiveData<List<BuyBasketModel>> {
        return basketDao.getAllCoffeeFromBasket()
    }

    override fun getAllCoffeeToBasketFromBasket(idProduct:String): LiveData<List<BuyBasketModel>> {
        return basketDao.getAllCoffeeToBasketFromBasket(idProduct)
    }

    override suspend fun deleteCoffeeToBasketFromBasket(idProduct: String) {
        basketDao.deleteCoffeeToBasketFromBasket(idProduct)
    }

    override suspend fun deleteCoffeeFromBasket(idProductToBasket: Int) {
        basketDao.deleteCoffeeFromBasket(idProductToBasket)
    }

    override suspend fun clear(){
        basketDao.clearBasket()
    }


}