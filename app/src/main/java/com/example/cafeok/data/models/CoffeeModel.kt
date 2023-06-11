package com.example.cafeok.data.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "coffee_data_table")
class CoffeeModel (

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "coffee_id")
    var id: Int? = null,

    @ColumnInfo(name = "coffee_image")
    var image: String? = null,

    @ColumnInfo(name = "coffee_image2")
    var image2: String? = null,

    @ColumnInfo(name = "coffee_name")
    var name: String? = null,

    @ColumnInfo(name = "coffee_description")
    var description: String? = null,

    @ColumnInfo(name = "coffee_price")
    var price: String? = null,

    )