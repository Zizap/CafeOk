package com.example.cafeok.data.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "order_data_table")
class OrderModel (

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "order_coffee_id")
    var id: Int,

    @ColumnInfo(name = "order_name_user")
    var nameUser: String,

    @ColumnInfo(name = "order_phone_user")
    var phoneUser: String,

    @ColumnInfo(name = "order_description")
    var description: String,

    @ColumnInfo(name = "order_total_price")
    var totalPrice: String,

    )