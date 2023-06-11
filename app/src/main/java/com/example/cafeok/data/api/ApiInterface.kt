package com.example.cafeok.data.api

import com.example.cafeok.data.models.CoffeeApiModel
import retrofit2.Call
import retrofit2.http.GET

interface ApiInterface {
    @GET("getCoffee.php")
    fun getCoffee(): Call<ArrayList<CoffeeApiModel>>
}