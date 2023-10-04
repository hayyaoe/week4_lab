package com.example.week4_lab.model

import androidx.annotation.DrawableRes

data class products(
    @DrawableRes val image_path : Int,
    val product_name : String,
    val price : Int,
    val location : String,
    val sold : Int
)
