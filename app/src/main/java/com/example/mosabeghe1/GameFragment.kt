package com.example.mosabeghe1

import android.os.Build
import android.os.Bundle
import android.os.CountDownTimer
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.annotation.RequiresApi
import androidx.core.content.ContextCompat.getColor
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.example.mosabeghe1.databinding.FragmentGameBinding

class GameFragment : Fragment() {
    private val mainViewModel: MainViewModel by activityViewModels()
    lateinit var binding: FragmentGameBinding
    private val timer = object : CountDownTimer(10000, 1000) {
        @RequiresApi(Build.VERSION_CODES.N)
        override fun onTick(millisUntilFinished: Long) {
            binding.textViewTime.text = (millisUntilFinished / 1000).toString()
            if ((millisUntilFinished / 1000) > 3) binding.textViewTime.setTextColor(getColor(requireContext(),R.color.yellow))
            else  binding.textViewTime.setTextColor(getColor(requireContext(),R.color.my_red))
        }

        @RequiresApi(Build.VERSION_CODES.N)
        override fun onFinish() {
            failTime()
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentGameBinding.inflate(layoutInflater, container, false)
        return  binding.root
    }

    @RequiresApi(Build.VERSION_CODES.N)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initViews()
        binding.button.setOnClickListener {
            mainViewModel.isAnswered = false
            timer.start()
            binding.button.isClickable = false
            binding.textViewChoice1.isClickable = true
            binding.textViewChoice2.isClickable = true
            binding.textViewChoice3.isClickable = true
            binding.textViewChoice4.isClickable = true
            mainViewModel.dice()
            mainViewModel.generateAllChoicesRandomly()
            initViews()
        }
        binding.textViewChoice1.setOnClickListener {
            checkAnswer(it)
        }
        binding.textViewChoice2.setOnClickListener {
            checkAnswer(it)
        }
        binding.textViewChoice3.setOnClickListener {
            checkAnswer(it)
        }
        binding.textViewChoice4.setOnClickListener {
            checkAnswer(it)
        }
    }

    @RequiresApi(Build.VERSION_CODES.M)
    fun failTime() {
        mainViewModel.nextLevel(false)
        if (mainViewModel.level > 5) {
            finishGame()
        } else {
            mainViewModel.dice()
            mainViewModel.generateAllChoicesRandomly()
            initViews()
            timer.start()
        }

    }
    @RequiresApi(Build.VERSION_CODES.M)
    fun initViews() {
        binding.tvOperator.text = when (mainViewModel.operator){
            Operator.REMINDER -> "%"
            else -> "+"
        }
        binding.textViewChoice1.setBackgroundColor(getColor(requireContext(),R.color.background))
        binding.textViewChoice2.setBackgroundColor(getColor(requireContext(),R.color.background))
        binding.textViewChoice3.setBackgroundColor(getColor(requireContext(),R.color.background))
        binding.textViewChoice4.setBackgroundColor(getColor(requireContext(),R.color.background))
        binding.textViewValueOfA.text = mainViewModel.a.toString()
        binding.textViewValueOfB.text = mainViewModel.b.toString()
        if (mainViewModel.choiceList.isNotEmpty()) {
            binding.textViewChoice1.text = mainViewModel.choiceList[0].toString()
            binding.textViewChoice2.text = mainViewModel.choiceList[1].toString()
            binding.textViewChoice3.text = mainViewModel.choiceList[2].toString()
            binding.textViewChoice4.text = mainViewModel.choiceList[3].toString()
        }
        binding.tvScore.text = mainViewModel.score.toString()

        if (mainViewModel.isAnswered) {
            var view: TextView = when (mainViewModel.userChoice) {
                1 -> binding.textViewChoice1
                2 -> binding.textViewChoice2
                3 -> binding.textViewChoice3
                else -> binding.textViewChoice4
            }
            if (view.text == mainViewModel.generateCorrectChoice().toString()) {
                view.setBackgroundColor(getColor(requireContext(), R.color.my_green))
            } else {
                view.setBackgroundColor(getColor(requireContext(), R.color.my_red))
            }
        }
    }

    @RequiresApi(Build.VERSION_CODES.N)
    fun checkAnswer(view: View) {
        mainViewModel.isAnswered = true
        timer.cancel()
        if ((view as TextView).text == mainViewModel.generateCorrectChoice().toString()) {
            view.setBackgroundColor(getColor(requireContext(),R.color.my_green))
            mainViewModel.nextLevel(true)

        } else {
            view.setBackgroundColor(getColor(requireContext(),R.color.my_red))
            mainViewModel.nextLevel(false)
        }
        binding.button.isClickable = true
        binding.textViewChoice1.isClickable = false
        binding.textViewChoice2.isClickable = false
        binding.textViewChoice3.isClickable = false
        binding.textViewChoice4.isClickable = false
        binding.tvScore.text = mainViewModel.score.toString()

        mainViewModel.userChoice = when(view) {
             binding.textViewChoice1 -> 1
             binding.textViewChoice2 -> 2
             binding.textViewChoice3 -> 3
             binding.textViewChoice4 -> 4
            else -> 0
        }

        if (mainViewModel.level > 5) {
            finishGame()
        }
    }

    private fun finishGame() {
        timer.cancel()
        val bundle = Bundle()
        bundle.putInt("score", mainViewModel.score)
        findNavController().navigate(R.id.action_gameFragment_to_resultFragment,bundle)
    }
}