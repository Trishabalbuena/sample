package com.example.sample.data

import com.example.sample.models.Question

object CssBasicsQuiz {
    val questions = listOf(
        Question(
            text = "Which syntax is correct for changing the text color of a paragraph to red in a CSS file?",
            options = listOf("p {color: red;}", "p.color = red;", "<p style=\"color:red;\">", "p.style.color = \"red\""),
            correctAnswerIndex = 0
        )
    )
}
