package com.example.mosabeghe1

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SeekBar
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.example.mosabeghe1.databinding.FragmentSettingBinding

class SettingFragment : Fragment() {
    lateinit var binding: FragmentSettingBinding
    private val mainViewModel: MainViewModel by activityViewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentSettingBinding.inflate(layoutInflater, container, false)
        return  binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initViews()
        binding.btnStart.setOnClickListener{
            mainViewModel.maxA = binding.seekBarA.progress
            mainViewModel.maxB = binding.seekBarB.progress
            mainViewModel.operator = when (binding.rgOperators.checkedRadioButtonId){
                binding.rbReminder.id ->   Operator.REMINDER
                else -> Operator.ADDITION
            }
           startGame()
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
        binding.seekBarA.progress = mainViewModel.maxA
        binding.seekBarB.progress = mainViewModel.maxB
        binding.tvMaxA.text = binding.seekBarA.progress.toString()
        when (mainViewModel.operator) {
            Operator.REMINDER-> binding.rbReminder.isChecked = true
            else -> binding.rbAddition.isChecked = true
        }
    }

    fun startGame() {
        findNavController().navigate(R.id.action_settingFragment_to_gameFragment)
    }
}