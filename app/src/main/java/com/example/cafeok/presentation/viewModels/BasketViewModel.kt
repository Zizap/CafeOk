package com.example.cafeok.presentation.viewModels

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.cafeok.data.models.BuyBasketModel
import com.example.cafeok.domain.useCase.BasketUseCase
import com.example.cafeok.domain.useCase.CoffeeUseCase
import kotlinx.coroutines.launch

class BasketViewModel(private val basketUseCase: BasketUseCase): ViewModel() {

    val loadCoffeeFromBasket = basketUseCase.loadCoffeeFromBasket()

    fun startInsert (id:Int?,name:String?,image:String?,price:String?,idProduct:String?,count:String?){
        insert(
            BuyBasketModel(id,image,name,price,count,idProduct)
        )
    }

    fun startUpdateCoffeeFromBasket(idBasket:Int?,image2:String?,name: String?,price: String?,count: String?,idProduct: String?){
        updateCoffeeFromBasket(BuyBasketModel(idBasket,image2,name,price,count,idProduct))
    }

    private fun updateCoffeeFromBasket(buyBasketModel: BuyBasketModel) = viewModelScope.launch{
        basketUseCase.updateCoffeeFromBasket(buyBasketModel)
    }

    private fun insert (buyBasketModel: BuyBasketModel) = viewModelScope.launch{
        basketUseCase.insertCoffeeToBasket(buyBasketModel)
    }

    fun clearBasket() = viewModelScope.launch {
        basketUseCase.clear()
    }

    fun deleteCoffeeToBasketFromBasket(idProduct:String) = viewModelScope.launch{
        basketUseCase.deleteCoffeeToBasketFromBasket(idProduct)
    }

     fun deleteCoffeeFromBasket(idProductToBasket: Int) = viewModelScope.launch {
        basketUseCase.deleteCoffeeFromBasket(idProductToBasket)
    }

    fun loadCoffeeFromBasket(idProduct:String): LiveData<List<BuyBasketModel>>{
        return basketUseCase.getAllCoffeeToBasketFromBasket(idProduct)
    }

}