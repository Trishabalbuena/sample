package com.example.sample.data

import com.example.sample.models.Question

object IntroductionToProgrammingQuiz {
    val questions = listOf(
        Question(
            text = "In many programming languages, the first program you write is a 'Hello, World!' program. Fill in the blank to complete the code: `print('Hello, _____!')`",
            options = listOf("Earth", "Galaxy", "World", "Universe"),
            correctAnswerIndex = 2
        )
    )
}
