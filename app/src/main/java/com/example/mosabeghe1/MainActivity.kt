package com.example.mosabeghe1

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import android.view.View
import android.widget.TextView
import androidx.annotation.RequiresApi
import com.example.mosabeghe1.databinding.ActivityMainBinding
import kotlin.concurrent.timer

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    val timer = object : CountDownTimer(10000, 1000) {
        @RequiresApi(Build.VERSION_CODES.N)
        override fun onTick(millisUntilFinished: Long) {
            binding.textViewTime.text = (millisUntilFinished / 1000).toString()
            if ((millisUntilFinished / 1000) > 3) binding.textViewTime.setTextColor(getColor(R.color.yellow))
            else  binding.textViewTime.setTextColor(getColor(R.color.my_red))
        }

        @RequiresApi(Build.VERSION_CODES.N)
        override fun onFinish() {
            failTime()
        }
    }
    @RequiresApi(Build.VERSION_CODES.N)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initViews()
        binding.button.setOnClickListener {
            timer.start()
            binding.button.isClickable = false
            binding.textViewChoice1.isClickable = true
            binding.textViewChoice2.isClickable = true
            binding.textViewChoice3.isClickable = true
            binding.textViewChoice4.isClickable = true
                Game.dice()
                Game.generateAllChoicesRandomly()
                initViews()
        }
        binding.textViewChoice1.setOnClickListener {
            checkAnswer(it)
        }
        binding.textViewChoice2.setOnClickListener {
            checkAnswer(it)
        }
        binding.textViewChoice3.setOnClickListener {
            checkAnswer(it)
        }
        binding.textViewChoice4.setOnClickListener {
            checkAnswer(it)
        }
    }

    @RequiresApi(Build.VERSION_CODES.N)
    fun failTime() {
        Game.nextLevel(false)
        if (Game.level > 5) {
            timer.cancel()
            val intent = Intent(this, Activity2::class.java)
            intent.putExtra("score", Game.score)
            startActivity(intent)
        } else {
            Game.dice()
            Game.generateAllChoicesRandomly()
            initViews()
            timer.start()
        }

    }
    @RequiresApi(Build.VERSION_CODES.M)
    fun initViews() {
        binding.tvOperator.text = when (Game.operator){
            Operator.REMINDER -> "%"
            else -> "+"
        }
        binding.textViewChoice1.setBackgroundColor(getColor(R.color.background))
        binding.textViewChoice2.setBackgroundColor(getColor(R.color.background))
        binding.textViewChoice3.setBackgroundColor(getColor(R.color.background))
        binding.textViewChoice4.setBackgroundColor(getColor(R.color.background))
        binding.textViewValueOfA.text = Game.a.toString()
        binding.textViewValueOfB.text = Game.b.toString()
        if (!Game.choiceList.isEmpty()) {
            binding.textViewChoice1.text = Game.choiceList[0].toString()
            binding.textViewChoice2.text = Game.choiceList[1].toString()
            binding.textViewChoice3.text = Game.choiceList[2].toString()
            binding.textViewChoice4.text = Game.choiceList[3].toString()
        }
        binding.tvScore.text = Game.score.toString()
    }

    @RequiresApi(Build.VERSION_CODES.N)
    fun checkAnswer(view: View) {
        timer.cancel()
        if ((view as TextView).text == Game.generateCorrectChoice().toString()) {
            view.setBackgroundColor(getColor(R.color.my_green))
            Game.nextLevel(true)

        } else {
            view.setBackgroundColor(getColor(R.color.my_red))
            Game.nextLevel(false)
        }
        binding.button.isClickable = true
        binding.textViewChoice1.isClickable = false
        binding.textViewChoice2.isClickable = false
        binding.textViewChoice3.isClickable = false
        binding.textViewChoice4.isClickable = false
        binding.tvScore.text = Game.score.toString()

        if (Game.level > 5) {
            timer.cancel()
            val intent = Intent(this, Activity2::class.java)
            intent.putExtra("score", Game.score)
            startActivity(intent)
        }
    }

}