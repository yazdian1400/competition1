package com.example.mosabeghe1

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.mosabeghe1.databinding.ActivityMainBinding
import kotlin.random.Random

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
       Game.dice()


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
    }

}