package com.example.week4_lab.model

import androidx.annotation.DrawableRes

data class Feed(
    val username : String,
    val profilePic : String,
    val content : String,
    val isLiked : Boolean,
    val isBookmarked : Boolean,
    val likes : Int,
    val captions : String,
    val date: String


)
