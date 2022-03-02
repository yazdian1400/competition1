package com.example.mosabeghe1

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.TextView
import android.widget.Toast
import androidx.annotation.RequiresApi
import com.example.mosabeghe1.databinding.ActivityMainBinding
import kotlin.properties.Delegates
import kotlin.random.Random

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    @RequiresApi(Build.VERSION_CODES.N)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        Game.maxA = intent.getIntExtra("maxA", 100)
        Game.maxB = intent.getIntExtra("maxB", 10)
        initViews()
        binding.button.setOnClickListener{
            binding.button.isClickable = false
            binding.textViewChoice1.isClickable = true
            binding.textViewChoice2.isClickable = true
            binding.textViewChoice3.isClickable = true
            binding.textViewChoice4.isClickable = true
            Game.dice()
            Game.generateAllChoicesRandomly()
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

    @RequiresApi(Build.VERSION_CODES.N)
    fun initViews(){
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