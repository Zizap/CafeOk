package com.example.cafeok.data.repository


import com.example.cafeok.data.localDB.OrdersDao
import com.example.cafeok.data.models.OrderModel


class OrderRepository(private val ordersDao: OrdersDao
) {

    suspend fun insert(orderModel: OrderModel){
        ordersDao.insert(orderModel)
    }
}