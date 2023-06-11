package com.example.cafeok.data.localDB

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.cafeok.data.models.CoffeeModel

@Database(entities = [CoffeeModel::class], version = 1)
abstract class CofDB: RoomDatabase() {
    abstract val coffeeDao: CoffeeDao
}