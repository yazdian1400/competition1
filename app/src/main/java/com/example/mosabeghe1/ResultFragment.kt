package com.example.mosabeghe1


import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.example.mosabeghe1.databinding.FragmentResultBinding


class ResultFragment : Fragment() {
    private lateinit var binding: FragmentResultBinding
    private val mainViewModel: MainViewModel by activityViewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentResultBinding.inflate(layoutInflater, container, false)
        return  binding.root
    }

    @SuppressLint("SetTextI18n")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        var score = 0
        if (arguments?.getInt("score",0) != null) {
            score = arguments?.getInt("score", 0)!!
        }
        mainViewModel.setMaximumScore(score)
        binding.textViewMaxScore.text = mainViewModel.maxScore.toString()
        binding.tvShowScore.text = " $score"
        binding.btnExit.setOnClickListener{
            requireActivity().finishAffinity()
        }
        binding.btnNewGame.setOnClickListener{
            //mainViewModel.reset()     moved to SettingFragment
            resetGame()
        }
    }
    fun resetGame() {
        findNavController().navigate(R.id.action_resultFragment_to_settingFragment)
    }
}