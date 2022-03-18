package com.example.mosabeghe1


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.mosabeghe1.databinding.FragmentResultBinding


class ResultFragment : Fragment() {
    lateinit var binding: FragmentResultBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentResultBinding.inflate(layoutInflater, container, false)
        return  binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        var score = 0
        if (arguments?.getInt("score",0) != null) {
            score = arguments?.getInt("score", 0)!!
        }
        Game.setMaximumScore(score)
        binding.textViewMaxScore.text = Game.maxScore.toString()
        binding.tvShowScore.text = " ${score.toString()}"
        binding.btnExit.setOnClickListener{
            requireActivity().finishAffinity()
        }
        binding.btnNewGame.setOnClickListener{
            Game.reset()
            resetGame()
        }
    }
    fun resetGame() {
        findNavController().navigate(R.id.action_resultFragment_to_settingFragment)
    }
}