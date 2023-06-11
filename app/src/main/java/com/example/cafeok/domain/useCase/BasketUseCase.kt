package com.example.cafeok.domain.useCase

import android.content.Context
import androidx.lifecycle.LiveData
import com.example.cafeok.data.models.BuyBasketModel
import com.example.cafeok.data.models.CoffeeModel
import com.example.cafeok.domain.repository.BasketCall
import com.example.cafeok.domain.repository.CoffeeCall

class BasketUseCase(private val basketCall: BasketCall) {

     suspend fun insertCoffeeToBasket(buyBasketModel: BuyBasketModel){
        basketCall.insertCoffeeToBasket(buyBasketModel)
    }

    suspend fun updateCoffeeFromBasket(buyBasketModel: BuyBasketModel){
        basketCall.updateCoffeeFromBasket(buyBasketModel)
    }

     fun loadCoffeeFromBasket(): LiveData<List<BuyBasketModel>> {
        return basketCall.loadCoffeeFromBasket()
    }

     fun getAllCoffeeToBasketFromBasket(idProduct:String): LiveData<List<BuyBasketModel>> {
        return basketCall.getAllCoffeeToBasketFromBasket(idProduct)
    }

    suspend fun deleteCoffeeToBasketFromBasket(idProduct:String){
        basketCall.deleteCoffeeToBasketFromBasket(idProduct)
    }

    suspend fun deleteCoffeeFromBasket(idProductToBasket: Int) {
        basketCall.deleteCoffeeFromBasket(idProductToBasket)
    }

     suspend fun clear(){
        basketCall.clear()
    }


}