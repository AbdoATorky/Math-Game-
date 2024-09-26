package com.abs.mathgame

import android.content.Intent
import android.os.Bundle
import android.os.CountDownTimer
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.AppCompatButton
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.android.material.appbar.MaterialToolbar
import java.util.Locale
import kotlin.random.Random

class GameActivity : AppCompatActivity() {

    lateinit var toolbar: MaterialToolbar

    lateinit var textScore : TextView
    lateinit var textLife : TextView
    lateinit var textTime : TextView

    lateinit var textQuestion : TextView
    lateinit var editTextAnswer: EditText

    lateinit var buttonNext : AppCompatButton

    var correctAnswer = 0
    var userScore = 0
    var userLife = 3

    lateinit var  timer : CountDownTimer
    private val startTimerInMillis : Long = 60000

    var timeLeftInMillis : Long = startTimerInMillis

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_game)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        textScore = findViewById(R.id.textViewScore)
        textLife = findViewById(R.id.textViewLife)
        textTime = findViewById(R.id.textViewTime)
        textQuestion = findViewById(R.id.textViewQuestion)
        editTextAnswer = findViewById(R.id.editTextAnswer)
        buttonNext = findViewById(R.id.buttonNext)

        gameContinue()


        buttonNext.setOnClickListener {
            val input = editTextAnswer.text.toString()

            if (input == ""){
                Toast.makeText(applicationContext, "please write an answer or click the next button", Toast.LENGTH_SHORT).show()
            }else{

                pauseTimer()

                val userAnswer = input.toInt()

                if (userAnswer == correctAnswer){
                    userScore = userScore+10
                    Toast.makeText(applicationContext, "Congratulation your answer is correct", Toast.LENGTH_SHORT).show()
                    textScore.text = userScore.toString()

                    resetTimer()
                    gameContinue()


                }else{
                    userLife--
                    Toast.makeText(applicationContext, "Sry your answer is wrong", Toast.LENGTH_SHORT).show()
                    textLife.text = userLife.toString()

                    resetTimer()
                    gameContinue()

                }
            }

//            pauseTimer()
//            resetTimer()

//            gameContinue()


            editTextAnswer.setText("")
            if(userLife == 0){
                Toast.makeText(applicationContext, "Game Over", Toast.LENGTH_LONG).show()
                val intent = Intent(this@GameActivity,ResultActivity::class.java)
                intent.putExtra("Score",userScore)
                startActivity(intent)
                finish()
            }else{

            }
        }



    }
    fun gameContinue(){
        val number1 = Random.nextInt(0,100)
        val number2 = Random.nextInt(0,100)

        textQuestion.text = "$number1 + $number2"

        correctAnswer = number1+number2

        startTimer()

    }

    fun startTimer(){
        timer = object : CountDownTimer(timeLeftInMillis,1000){
            override fun onTick(millisUntilFinished: Long) {

                timeLeftInMillis = millisUntilFinished
                updateText()

            }

            override fun onFinish() {

                pauseTimer()
                resetTimer()
                updateText()

                userLife--
                textLife.text = userLife.toString()
                textQuestion.text = "Sry your time is up!"
            }

        }.start()
    }

    fun updateText(){
        val remainingTime : Int = (timeLeftInMillis/1000).toInt()
        textTime.text = String.format(Locale.getDefault(),"%02d",remainingTime)
    }
    fun pauseTimer(){
        timer.cancel()
    }
    fun resetTimer(){
        timeLeftInMillis = startTimerInMillis
        updateText()
    }



}