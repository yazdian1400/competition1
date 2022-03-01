package com.example.mosabeghe1

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
        binding.textViewChoice1.text = Game.generateCorrectChoice().toString()
        binding.textViewChoice2.text = Game.generateWrongChoices()[0].toString()
        binding.textViewChoice3.text = Game.generateWrongChoices()[1].toString()
        binding.textViewChoice4.text = Game.generateWrongChoices()[2].toString()
        binding.tvScore.text = Game.score.toString()
    }

    fun checkAnswer(view: View){
        if ((view as TextView).text == Game.generateCorrectChoice().toString()){
            Game.nextLevel(true)
        } else {
            Game.nextLevel(false)
        }
        binding.tvScore.text = Game.score.toString()
        if (Game.level > 5){
            val intent = Intent(this, Activity2::class.java)
            intent.putExtra("score", Game.score)
            startActivity(intent)
        }
    }
}