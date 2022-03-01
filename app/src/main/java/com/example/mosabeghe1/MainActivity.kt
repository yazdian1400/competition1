package com.example.mosabeghe1

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.mosabeghe1.databinding.ActivityMainBinding
import kotlin.random.Random

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    var a = 0
    var b = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        a=(0..100).random()
        b=(0..10).random()
        setRandomNumbers()
    }

    fun setRandomNumbers() {
        binding.textViewValueOfA.text = a.toString()
        binding.textViewValueOfB.text = b.toString()

    }

    fun setChoices()
    {
        var choice1 = (0..50).random().toString()
        var choice2 = (0..50).random().toString()
        var choice3 = (0..50).random().toString()
       

    }

}