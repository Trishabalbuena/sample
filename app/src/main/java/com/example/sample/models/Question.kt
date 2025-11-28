package com.example.sample.models

import androidx.annotation.DrawableRes

data class Question(
    val text: String,
    val options: List<String>,
    val correctAnswerIndex: Int,
    @DrawableRes val imageResId: Int? = null
)
