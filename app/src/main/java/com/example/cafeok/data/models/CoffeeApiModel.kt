package com.example.cafeok.data.models

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class CoffeeApiModel (

    @SerializedName("id") @Expose
    var id: Int? = null,
    @SerializedName("image1") @Expose
    var image1: String? = null,
    @SerializedName("image2") @Expose
    var image2: String? = null,
    @SerializedName("name") @Expose
    var name: String? = null,
    @SerializedName("description") @Expose
    var description: String? = null,
    @SerializedName("price") @Expose
    var price: String? = null,

    )



