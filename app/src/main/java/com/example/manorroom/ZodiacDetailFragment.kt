package com.example.manorroom

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.navArgs
import com.example.manorroom.databinding.FragmentZodiacDetailBinding
import kotlinx.coroutines.launch
import java.util.*

class ZodiacDetailFragment : Fragment() {
    private var _binding: FragmentZodiacDetailBinding? = null
    private val binding
        get() = checkNotNull(_binding) {
            "Cannot access binding because it is null. Is the view visible?"
        }

    private val args: ZodiacDetailFragmentArgs by navArgs()


    private val zodiacDetailViewModel: ZodiacDetailViewModel by viewModels {
        ZodiacDetailViewModelFactory(args.zodiacId)
    }

     override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding =
            FragmentZodiacDetailBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.lifecycle.repeatOnLifecycle(Lifecycle.State.STARTED) {
                zodiacDetailViewModel.zodiac.collect { zodiac ->
                    zodiac?.let { updateUi(it) }
                }
            }
        }

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun updateUi(zodiac: Zodiac) {
        binding.apply {
            if (zodiacName.text.toString() != zodiac.name) {
                zodiacName.setText(zodiac.name)
            }
            zodiacDescription.text = zodiac.description
            zodiacSymbol.text = zodiac.symbol
            zodiacMonth.text = zodiac.month

        }
    }
}