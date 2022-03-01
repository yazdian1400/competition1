package com.example.mosabeghe1

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.mosabeghe1.databinding.Activity2Binding
import com.example.mosabeghe1.databinding.ActivityMainBinding

class Activity2 : AppCompatActivity() {
    private lateinit var binding: Activity2Binding
    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = Activity2Binding.inflate(layoutInflater)
        setContentView(binding.root)
        val score = intent.getIntExtra("score",0)
        binding.tvShowScore.text = "your score: ${score.toString()}"
    }
}