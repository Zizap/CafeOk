package com.example.cafeok.data.dataSourceIMPL

import android.content.Context
import android.widget.Toast
import com.example.cafeok.data.api.ApiClient
import com.example.cafeok.data.dataSource.CoffeeApiDataSource
import com.example.cafeok.data.dataSource.CoffeeDataSource
import com.example.cafeok.data.models.CoffeeApiModel
import com.example.cafeok.data.models.CoffeeModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class CoffeeApiDataSourceIMPL(private val coffeeDataSource: CoffeeDataSource): CoffeeApiDataSource {

    override fun startMigration(context: Context) {
        val call = ApiClient.instanse?.api?.getCoffee()
        call?.enqueue(object: Callback<ArrayList<CoffeeApiModel>> {

            override fun onResponse(
                call: Call<ArrayList<CoffeeApiModel>>,
                response: Response<ArrayList<CoffeeApiModel>>
            ) {
                var loadCoffee: ArrayList<CoffeeApiModel>? = null
                loadCoffee?.clear()
                loadCoffee = (response.body() as ArrayList<CoffeeApiModel>?)!!
                for (audit in loadCoffee) {
                    audit.id?.let {
                        CoffeeModel(
                            it,
                            audit.image1.toString(),
                            audit.image2.toString(),
                            audit.name.toString(),
                            audit.description.toString(),
                            audit.price.toString()
                        )
                    }?.let {
                        coffeeDataSource.insert(
                            it
                        )
                    }
                }
                Toast.makeText(context,"ЗАГРУЗКА", Toast.LENGTH_SHORT).show()
            }

            override fun onFailure(call: Call<ArrayList<CoffeeApiModel>>, t: Throwable) {
                Toast.makeText(context,"ОШИБКА!", Toast.LENGTH_SHORT).show()
            }

        })
    }

}