package com.example.mosabeghe1

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.SeekBar
import com.example.mosabeghe1.databinding.ActivityMainBinding
import com.example.mosabeghe1.databinding.ActivityMenuBinding

class ActivityMenu : AppCompatActivity() {
    lateinit var binding : ActivityMenuBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_menu)
        binding = ActivityMenuBinding.inflate(layoutInflater)
        setContentView(binding.root)
        this.title = "Menu"
        initViews()
        binding.btnStart.setOnClickListener{
            Game.maxA = binding.seekBarA.progress
            Game.maxB = binding.seekBarB.progress
            intent = Intent(this, MainActivity::class.java)
//            intent.putExtra("maxA", maxA)
//            intent.putExtra("maxB", maxB)
            startActivity(intent)
        }

        binding.seekBarA.setOnSeekBarChangeListener(object:
            SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(
                seek: SeekBar,
                progress: Int, fromUser: Boolean
            ) {
                binding.tvMaxA.text = binding.seekBarA.progress.toString()
            }

            override fun onStartTrackingTouch(p0: SeekBar?) {
            }

            override fun onStopTrackingTouch(p0: SeekBar?) {
            }
        })

        binding.seekBarB.setOnSeekBarChangeListener(object:
            SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(
                seek: SeekBar,
                progress: Int, fromUser: Boolean
            ) {
                binding.tvMaxB.text = binding.seekBarB.progress.toString()
            }

            override fun onStartTrackingTouch(p0: SeekBar?) {
            }

            override fun onStopTrackingTouch(p0: SeekBar?) {
            }
        })
    }

    private fun initViews() {
        binding.seekBarA.progress = Game.maxA
        binding.seekBarB.progress = Game.maxB
        binding.tvMaxA.text = binding.seekBarA.progress.toString()
    }
}