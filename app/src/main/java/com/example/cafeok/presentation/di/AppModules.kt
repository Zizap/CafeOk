package com.example.cafeok.presentation.di

import androidx.room.Room
import com.example.cafeok.data.dataSource.CoffeeApiDataSource
import com.example.cafeok.data.dataSource.CoffeeDataSource
import com.example.cafeok.data.dataSource.FirebaseDataSource
import com.example.cafeok.data.dataSourceIMPL.CoffeeApiDataSourceIMPL
import com.example.cafeok.data.dataSourceIMPL.CoffeeDataSourceIMPL
import com.example.cafeok.data.dataSourceIMPL.FirebaseDataSourceIMPL
import com.example.cafeok.data.localDB.BaDB
import com.example.cafeok.data.localDB.CofDB
import com.example.cafeok.data.localDB.OrDB
import com.example.cafeok.data.repository.BasketRepository
import com.example.cafeok.data.repository.CoffeeRepository
import com.example.cafeok.data.repository.FirebaseRepository
import com.example.cafeok.data.repository.OrderRepository
import com.example.cafeok.presentation.viewModels.BasketViewModel
import com.example.cafeok.presentation.viewModels.CoffeeViewModel
import com.example.cafeok.presentation.viewModels.FirebaseViewModel
import com.example.cafeok.presentation.viewModels.OrderViewModel
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val coffee = module {


    single{
        Room.databaseBuilder(androidContext(),CofDB::class.java,
        "cofDB").build()
    }

    single { get<CofDB>().coffeeDao }

    single<CoffeeDataSource> { CoffeeDataSourceIMPL(get()) }

    single<CoffeeApiDataSource> { CoffeeApiDataSourceIMPL(get()) }

    single { CoffeeRepository(get(),get()) }

    viewModel { CoffeeViewModel(get()) }

}

val basket = module {

    single{
        Room.databaseBuilder(androidContext(), BaDB::class.java,
            "baDB").build()
    }

    single { get<BaDB>().basketDao }

    single { BasketRepository(get()) }

    viewModel { BasketViewModel(get()) }

}

val order = module {

    single{
        Room.databaseBuilder(androidContext(), OrDB::class.java,
            "OrDB").build()
    }

    single { get<OrDB>().ordersDao }

    single{ OrderRepository(get()) }

    viewModel { OrderViewModel(get()) }

}

val auth = module {

    single<FirebaseDataSource> { FirebaseDataSourceIMPL() }

    single { FirebaseRepository(get()) }

    viewModel{FirebaseViewModel(get())}

}