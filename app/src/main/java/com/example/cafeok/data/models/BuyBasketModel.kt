package com.example.cafeok.data.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "basket_data_table")
class BuyBasketModel (

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "basket_id")
    var id: Int? = null,

    @ColumnInfo(name = "basket_image2")
    var image2: String? = null,

    @ColumnInfo(name = "basket_name")
    var name: String? = null,

    @ColumnInfo(name = "basket_price")
    var price: String? = null,

    @ColumnInfo(name = "basket_count")
    var count: String? = null,

    @ColumnInfo(name = "basket_idProduct")
    var idProduct: String? = null,

    )