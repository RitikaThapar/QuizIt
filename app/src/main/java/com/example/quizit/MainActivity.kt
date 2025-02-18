package com.example.quizit

import android.graphics.Color
import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.quizit.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    private val questions= arrayOf( "What is the capital of France?",
        "Who wrote 'To Kill a Mockingbird'?",
        "What is the square root of 64?",
        "Which planet is known as the Red Planet?",
        "Who painted the Mona Lisa?")
    private val options= arrayOf(arrayOf("Paris","New York", "Delhi","Mexico"),
        arrayOf("Mark Twain","Harper Lee","William Shakespear","Jane Austen"),
        arrayOf("6","2","4","8"),
        arrayOf("Jupiter","Mars","Earth","Saturn"),
        arrayOf("Pabblo Picasso","Vincent Van Gogh","Leonardo Da Vinci","Claude Monet")
    )
    private val correctAnswers= arrayOf( 0,1,3,1,2)

    private var currentQuestIndex=0
    private var score=0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        enableEdgeToEdge()
        setContentView(binding.root)

        displayQuestion()
        binding.option1Button.setOnClickListener{
            checkAnswer(0)
        }
        binding.option2Button.setOnClickListener{
            checkAnswer(1)
        }
        binding.option3Button.setOnClickListener{
            checkAnswer(2)
        }
        binding.option4Button.setOnClickListener{
            checkAnswer(3)
        }
        binding.restartButton.setOnClickListener{
            restartQuiz()
        }
    }
    private fun correctButtonColors(buttonIndex: Int){
        when(buttonIndex){
            0 ->binding.option1Button.setBackgroundColor(Color.GREEN)
            1 ->binding.option2Button.setBackgroundColor(Color.GREEN)
            2 ->binding.option3Button.setBackgroundColor(Color.GREEN)
            3 ->binding.option4Button.setBackgroundColor(Color.GREEN)
        }
    }
    private fun wrongButtonColors(buttonIndex: Int){
        when(buttonIndex){
            0 ->binding.option1Button.setBackgroundColor(Color.RED)
            1 ->binding.option2Button.setBackgroundColor(Color.RED)
            2 ->binding.option3Button.setBackgroundColor(Color.RED)
            3 ->binding.option4Button.setBackgroundColor(Color.RED)
        }
    }

    private fun resetButtonColors(){
        binding.option1Button.setBackgroundColor(Color.rgb(50,59,96))
        binding.option2Button.setBackgroundColor(Color.rgb(50,59,96))
        binding.option3Button.setBackgroundColor(Color.rgb(50,59,96))
        binding.option4Button.setBackgroundColor(Color.rgb(50,59,96))
    }

    private fun showResults(){
        Toast.makeText(this, "Your Score: $score out of ${questions.size}", Toast.LENGTH_LONG).show()
        binding.restartButton.isEnabled =true
    }

    private fun displayQuestion(){
        binding.questionText.text=questions[currentQuestIndex]
        binding.option1Button.text=options[currentQuestIndex][0]
        binding.option2Button.text=options[currentQuestIndex][1]
        binding.option3Button.text=options[currentQuestIndex][2]
        binding.option4Button.text=options[currentQuestIndex][3]
        resetButtonColors()

    }

    private fun checkAnswer(selectedAnswerIndex: Int){
        val correctAnswerIndex=correctAnswers[currentQuestIndex]

        if(selectedAnswerIndex==correctAnswerIndex){
            score++
            correctButtonColors(selectedAnswerIndex)
        }
        else{
            wrongButtonColors(selectedAnswerIndex)
            correctButtonColors(correctAnswerIndex)
        }
        if(currentQuestIndex<questions.size-1){
            currentQuestIndex++
            binding.questionText.postDelayed({displayQuestion()},1000)
        }
        else{
            showResults()
        }
    }

    private fun restartQuiz(){
        currentQuestIndex=0
        score=0
        displayQuestion()
        binding.restartButton.isEnabled=false

    }
}