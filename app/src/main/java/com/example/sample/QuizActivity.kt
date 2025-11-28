package com.example.sample

import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.sample.data.BasicHardwareQuiz
import com.example.sample.data.CssBasicsQuiz
import com.example.sample.data.HtmlBasicsQuiz
import com.example.sample.data.IntroToITQuiz
import com.example.sample.data.IntroductionToProgrammingQuiz
import com.example.sample.data.OperatingSystemsQuiz
import com.example.sample.models.Question

class QuizActivity : AppCompatActivity() {

    private lateinit var questionTextView: TextView
    private lateinit var optionsRadioGroup: RadioGroup
    private lateinit var backButton: Button
    private lateinit var nextButton: Button
    private lateinit var questionImageView: ImageView

    private var currentQuestionIndex = 0
    private lateinit var questions: List<Question>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_quiz)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        val skillName = intent.getStringExtra("SKILL_NAME")
        val level = intent.getIntExtra("LEVEL", 1)
        title = "$skillName - Level $level"

        questions = when (skillName) {
            "Introduction to IT" -> IntroToITQuiz.questions
            "Basic Computer Hardware" -> BasicHardwareQuiz.questions
            "Operating Systems" -> OperatingSystemsQuiz.questions
            "Introduction to Programming" -> IntroductionToProgrammingQuiz.questions
            "HTML Basics" -> HtmlBasicsQuiz.questions
            "CSS Basics" -> CssBasicsQuiz.questions
            else -> emptyList()
        }

        questionTextView = findViewById(R.id.question_text)
        optionsRadioGroup = findViewById(R.id.options_radio_group)
        backButton = findViewById(R.id.back_button)
        nextButton = findViewById(R.id.next_button)
        questionImageView = findViewById(R.id.question_image)

        if (questions.isNotEmpty()) {
            displayQuestion(questions[currentQuestionIndex])
        } else {
            Toast.makeText(this, "No questions available for this skill yet.", Toast.LENGTH_LONG).show()
            finish()
        }


        backButton.setOnClickListener { showPreviousQuestion() }
        nextButton.setOnClickListener { showNextQuestion() }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == android.R.id.home) {
            val intent = Intent(this, MainActivity::class.java)
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_SINGLE_TOP)
            startActivity(intent)
            return true
        }
        return super.onOptionsItemSelected(item)
    }

    private fun displayQuestion(question: Question) {
        questionTextView.text = question.text

        if (question.imageResId != null) {
            questionImageView.visibility = View.VISIBLE
            questionImageView.setImageResource(question.imageResId)
        } else {
            questionImageView.visibility = View.GONE
        }

        optionsRadioGroup.removeAllViews()
        question.options.forEachIndexed { index, option ->
            val radioButton = RadioButton(this)
            radioButton.text = option
            radioButton.id = index
            optionsRadioGroup.addView(radioButton)
        }
        optionsRadioGroup.clearCheck()
    }

    private fun showPreviousQuestion() {
        if (currentQuestionIndex > 0) {
            currentQuestionIndex--
            displayQuestion(questions[currentQuestionIndex])
        } else {
            Toast.makeText(this, "This is the first question.", Toast.LENGTH_SHORT).show()
        }
    }

    private fun showNextQuestion() {
        val checkedRadioButtonId = optionsRadioGroup.checkedRadioButtonId
        if (checkedRadioButtonId == -1) {
            Toast.makeText(this, "Please select an answer.", Toast.LENGTH_SHORT).show()
            return
        }

        val currentQuestion = questions[currentQuestionIndex]
        if (checkedRadioButtonId == currentQuestion.correctAnswerIndex) {
            Toast.makeText(this, "Correct!", Toast.LENGTH_SHORT).show()
        } else {
            Toast.makeText(this, "Wrong!", Toast.LENGTH_SHORT).show()
        }

        if (currentQuestionIndex < questions.size - 1) {
            currentQuestionIndex++
            displayQuestion(questions[currentQuestionIndex])
        } else {
            Toast.makeText(this, "Quiz complete!", Toast.LENGTH_SHORT).show()
            finish()
        }
    }
}
