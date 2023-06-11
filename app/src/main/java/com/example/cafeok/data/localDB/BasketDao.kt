package com.example.cafeok.data.localDB

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.cafeok.data.models.BuyBasketModel


@Dao
interface BasketDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertCoffeeToBasket(buyBasketModel: BuyBasketModel)

    @Update
    suspend fun updateCoffeeFromBasket(buyBasketModel: BuyBasketModel)

    @Query("SELECT * FROM basket_data_table")
    fun getAllCoffeeFromBasket(): LiveData<List<BuyBasketModel>>

    @Query("SELECT * FROM basket_data_table WHERE basket_idProduct =:idProduct")
    fun getAllCoffeeToBasketFromBasket(idProduct:String): LiveData<List<BuyBasketModel>>

    @Query("DELETE FROM basket_data_table WHERE basket_id = :idProductToBasket")
    suspend fun deleteCoffeeFromBasket(idProductToBasket:Int)

    @Query("DELETE FROM basket_data_table WHERE basket_idProduct =:idProduct")
    suspend fun deleteCoffeeToBasketFromBasket(idProduct:String)

    @Query("DELETE FROM basket_data_table")
    suspend fun clearBasket()


}