package com.example.cafeok.data.localDB

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.cafeok.data.models.OrderModel


@Database(entities = [OrderModel::class], version = 1)
abstract class OrDB: RoomDatabase() {
    abstract val ordersDao:OrdersDao
}