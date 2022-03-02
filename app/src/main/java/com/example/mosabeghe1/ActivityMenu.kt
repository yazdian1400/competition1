package com.example.mosabeghe1

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.mosabeghe1.databinding.ActivityMenuBinding

class ActivityMenu : AppCompatActivity() {
    lateinit var binding : ActivityMenuBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_menu)
        binding = ActivityMenuBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnStart.setOnClickListener{
            val maxA = binding.seekBarA.progress
            val maxB = binding.seekBarB.progress

            intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }
    }
}