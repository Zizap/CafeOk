package com.example.cafeok.data.api

import com.example.cafeok.constants.Constants
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class ApiClient private constructor() {
    val api:ApiInterface
        get() = retrofit!!.create(
            ApiInterface::class.java
        )

    init {
        retrofit = Retrofit.Builder().baseUrl(BASE_URL).addConverterFactory(GsonConverterFactory.create()).build()
    }

    companion object {
        private const val BASE_URL = Constants.BASE_URL
        private var apiClient: ApiClient? = null
        private var retrofit: Retrofit? = null

        val instanse: ApiClient?
            @Synchronized get() {
                if (apiClient == null) {
                    apiClient = ApiClient()
                }
                return apiClient
            }
    }

}