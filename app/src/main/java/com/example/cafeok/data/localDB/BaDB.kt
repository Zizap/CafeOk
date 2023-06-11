package com.example.cafeok.data.localDB

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.cafeok.data.models.BuyBasketModel
import com.example.cafeok.data.models.CoffeeModel

@Database(entities = [BuyBasketModel::class], version = 1)
abstract class BaDB: RoomDatabase() {
    abstract val basketDao:BasketDao
}