package com.example.cafeok.presentation.viewModels

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.cafeok.data.models.BuyBasketModel
import com.example.cafeok.data.repository.BasketRepository
import kotlinx.coroutines.launch

class BasketViewModel(private val basketRepository: BasketRepository): ViewModel() {

    val loadCoffeeFromBasket = basketRepository.loadCoffeeFromBasket()

    fun startInsert (id:Int,name:String,image:String,price:String,idProduct:String,count:String){
        insert(
            BuyBasketModel(id,image,name,price,count,idProduct)
        )
    }

    fun startUpdateCoffeeFromBasket(idBasket:Int,image2:String,name: String,price: String,count: String,idProduct: String){
        updateCoffeeFromBasket(BuyBasketModel(idBasket,image2,name,price,count,idProduct))
    }

    private fun updateCoffeeFromBasket(buyBasketModel: BuyBasketModel) = viewModelScope.launch{
        basketRepository.updateCoffeeFromBasket(buyBasketModel)
    }

    private fun insert (buyBasketModel: BuyBasketModel) = viewModelScope.launch{
        basketRepository.insertCoffeeToBasket(buyBasketModel)
    }

    fun clearBasket() = viewModelScope.launch {
        basketRepository.clear()
    }

    fun deleteCoffeeToBasketFromBasket(idProduct:String) = viewModelScope.launch{
        basketRepository.deleteCoffeeToBasketFromBasket(idProduct)
    }

     fun deleteCoffeeFromBasket(idProductToBasket: Int) = viewModelScope.launch {
         basketRepository.deleteCoffeeFromBasket(idProductToBasket)
    }

    fun loadCoffeeFromBasket(idProduct:String): LiveData<List<BuyBasketModel>>{
        return basketRepository.getAllCoffeeToBasketFromBasket(idProduct)
    }

    fun add(buyBasketModel: BuyBasketModel){
        var count = buyBasketModel.count!!.toInt()
        count += 1
        startUpdateCoffeeFromBasket(
            buyBasketModel.id,
            buyBasketModel.image2,
            buyBasketModel.name,
            buyBasketModel.price,
            count.toString(),
            buyBasketModel.idProduct
        )
    }

     fun delete(buyBasketModel: BuyBasketModel) {
        if (buyBasketModel.count!!.toInt()<=1){
            deleteCoffeeToBasketFromBasket(buyBasketModel.id.toString())
        } else {
            var count = buyBasketModel.count!!.toInt()
            count -= 1
            startUpdateCoffeeFromBasket(
                buyBasketModel.id,
                buyBasketModel.image2,
                buyBasketModel.name,
                buyBasketModel.price,
                count.toString(),
                buyBasketModel.idProduct
            )
        }

    }

}