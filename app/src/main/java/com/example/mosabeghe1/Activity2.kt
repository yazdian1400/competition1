package com.example.mosabeghe1

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.mosabeghe1.databinding.Activity2Binding
import com.example.mosabeghe1.databinding.ActivityMainBinding

class Activity2 : AppCompatActivity() {
    private lateinit var binding: Activity2Binding
    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        this.title = "Math Game"
        super.onCreate(savedInstanceState)
        binding = Activity2Binding.inflate(layoutInflater)
        setContentView(binding.root)
        val score = intent.getIntExtra("score",0)
        Game.setMaximumScore(score)
        binding.textViewMaxScore.text = Game.maxScore.toString()
        binding.tvShowScore.text = " ${score.toString()}"
        binding.btnExit.setOnClickListener{
            this.finishAffinity()
        }
        binding.btnNewGame.setOnClickListener{
            Game.reset()
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }


    }
}