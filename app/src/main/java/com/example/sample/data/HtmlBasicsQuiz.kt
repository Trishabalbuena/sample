package com.example.sample.data

import com.example.sample.models.Question

object HtmlBasicsQuiz {
    val questions = listOf(
        Question(
            text = "To create a hyperlink in HTML, you need to use the `<a>` tag. Which attribute is used to specify the link\'s destination?",
            options = listOf("src", "href", "link", "dest"),
            correctAnswerIndex = 1
        )
    )
}
