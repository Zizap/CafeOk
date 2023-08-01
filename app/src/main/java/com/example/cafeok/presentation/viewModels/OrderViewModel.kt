package com.example.cafeok.presentation.viewModels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.cafeok.data.models.BuyBasketModel
import com.example.cafeok.data.models.OrderModel
import com.example.cafeok.data.repository.OrderRepository
import kotlinx.coroutines.launch

class OrderViewModel(private val orderRepository: OrderRepository): ViewModel() {

    fun startInsert (id:Int?,nameUser:String?,phoneUser:String?,description:String?,totalPrice:String?){
        insert(
            OrderModel(id,nameUser,phoneUser, description, totalPrice)
        )
    }


    private fun insert (orderModel: OrderModel) = viewModelScope.launch{
        orderRepository.insert(orderModel)
    }

}