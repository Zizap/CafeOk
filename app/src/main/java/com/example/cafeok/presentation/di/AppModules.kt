package com.example.cafeok.presentation.di

import androidx.room.Room
import com.example.cafeok.data.dataSource.CoffeeApiDataSource
import com.example.cafeok.data.dataSource.CoffeeDataSource
import com.example.cafeok.data.dataSourceIMPL.CoffeeApiDataSourceIMPL
import com.example.cafeok.data.dataSourceIMPL.CoffeeDataSourceIMPL
import com.example.cafeok.data.localDB.BaDB
import com.example.cafeok.data.localDB.CofDB
import com.example.cafeok.data.repository.BasketRepository
import com.example.cafeok.data.repository.CoffeeRepository
import com.example.cafeok.domain.repository.BasketCall
import com.example.cafeok.domain.repository.CoffeeCall
import com.example.cafeok.domain.useCase.BasketUseCase
import com.example.cafeok.domain.useCase.CoffeeUseCase
import com.example.cafeok.presentation.viewModels.BasketViewModel
import com.example.cafeok.presentation.viewModels.CoffeeViewModel
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

    single<CoffeeCall> { CoffeeRepository(get(),get()) }

    single { CoffeeUseCase(get()) }

    viewModel { CoffeeViewModel(get()) }

}

val basket = module {

    single{
        Room.databaseBuilder(androidContext(), BaDB::class.java,
            "baDB").build()
    }

    single { get<BaDB>().basketDao }

    single<BasketCall> { BasketRepository(get()) }

    single { BasketUseCase(get()) }

    viewModel { BasketViewModel(get()) }

}