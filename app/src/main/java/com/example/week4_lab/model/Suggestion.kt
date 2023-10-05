package com.example.week4_lab.model

import androidx.annotation.DrawableRes

data class Suggestion(
    val username : String,
    val profilePic : String,
    val isLiked : Boolean,
    val isBookmarked : Boolean,
)
