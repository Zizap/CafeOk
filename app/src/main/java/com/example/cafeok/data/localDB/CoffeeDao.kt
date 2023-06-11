package com.example.cafeok.data.localDB

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.cafeok.data.models.CoffeeModel

@Dao
interface CoffeeDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertCoffee(coffeeModel: CoffeeModel)

    @Query("SELECT * FROM coffee_data_table")
    fun getAllCoffee(): LiveData<List<CoffeeModel>>

    @Query("DELETE FROM coffee_data_table")
    suspend fun clear()

    @Query("SELECT * FROM coffee_data_table WHERE coffee_name LIKE :searchName")
    fun searchCoffeeByName(searchName: String): LiveData<List<CoffeeModel>>


}