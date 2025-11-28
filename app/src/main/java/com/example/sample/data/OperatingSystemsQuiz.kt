package com.example.sample.data

import com.example.sample.models.Question

object OperatingSystemsQuiz {
    val questions = listOf(
        Question(
            text = "You\'ve just downloaded a photo of your cat named 'fluffy.jpg'. According to standard file organization practices, which is the best folder to save it in?",
            options = listOf("My Documents", "My Pictures", "Program Files", "Windows"),
            correctAnswerIndex = 1
        )
    )
}
