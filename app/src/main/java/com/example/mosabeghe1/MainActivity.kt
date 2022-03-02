package com.example.mosabeghe1

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.TextView
import com.example.mosabeghe1.databinding.ActivityMainBinding
import kotlin.random.Random

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.button.setOnClickListener{
            binding.button.isClickable = false
            binding.textViewChoice1.isClickable = true
            binding.textViewChoice2.isClickable = true
            binding.textViewChoice3.isClickable = true
            binding.textViewChoice4.isClickable = true
            Game.dice()
            initViews()
        }
        binding.textViewChoice1.setOnClickListener{
            checkAnswer(it)
        }
        binding.textViewChoice2.setOnClickListener{
            checkAnswer(it)
        }
        binding.textViewChoice3.setOnClickListener{
            checkAnswer(it)
        }
        binding.textViewChoice4.setOnClickListener{
            checkAnswer(it)
        }
    }

    fun setRandomNumbers(a: Int, b: Int) {
        binding.textViewValueOfA.text = a.toString()
        binding.textViewValueOfB.text = b.toString()
    }
    fun initViews(){
        setRandomNumbers(Game.a, Game.b)
        val choices = Game.generateAllChoicesRandomly()
        binding.textViewChoice1.text = choices[0].toString()
        binding.textViewChoice2.text = choices[1].toString()
        binding.textViewChoice3.text = choices[2].toString()
        binding.textViewChoice4.text = choices[3].toString()
        binding.tvScore.text = Game.score.toString()
    }

    fun checkAnswer(view: View){
        if ((view as TextView).text == Game.generateCorrectChoice().toString()){
            Game.nextLevel(true)

        }
        else {
            Game.nextLevel(false)
        }
        binding.button.isClickable = true
        binding.textViewChoice1.isClickable = false
        binding.textViewChoice2.isClickable = false
        binding.textViewChoice3.isClickable = false
        binding.textViewChoice4.isClickable = false
        binding.tvScore.text = Game.score.toString()

        if (Game.level > 5){
            val intent = Intent(this, Activity2::class.java)
            intent.putExtra("score", Game.score)
            startActivity(intent)
        }


    }
}