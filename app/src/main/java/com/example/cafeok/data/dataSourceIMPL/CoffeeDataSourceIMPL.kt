package com.example.cafeok.data.dataSourceIMPL

import androidx.lifecycle.LiveData
import com.example.cafeok.data.dataSource.CoffeeDataSource
import com.example.cafeok.data.localDB.CoffeeDao
import com.example.cafeok.data.models.CoffeeModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class CoffeeDataSourceIMPL(private val coffeeDao: CoffeeDao):CoffeeDataSource {

    override fun insert(coffeeModel: CoffeeModel) {
        CoroutineScope(Dispatchers.IO).launch {
            coffeeDao.insertCoffee(coffeeModel)
        }
    }

    override fun loadCoffee():LiveData<List<CoffeeModel>>{
        return coffeeDao.getAllCoffee()
    }

    override suspend fun clear(){
        CoroutineScope(Dispatchers.IO).launch {
            coffeeDao.clear()
        }
    }

    override fun searchCoffeeByName(searchName: String): LiveData<List<CoffeeModel>> {
        return coffeeDao.searchCoffeeByName(searchName)
    }


}