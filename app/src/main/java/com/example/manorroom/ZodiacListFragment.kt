package com.example.manorroom
// missing package declaration

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.manorroom.ZodiacHolder
import com.example.manorroom.ZodiacListViewModel
import com.example.manorroom.databinding.FragmentZodiacListBinding
import kotlinx.coroutines.launch

private const val TAG = "ZodiacListFragment"

class ZodiacListFragment : Fragment() {
    //changed to upper case Z

    private var _binding: FragmentZodiacListBinding? = null
    private val binding
        get() = checkNotNull(_binding) {
            "Cannot access binding because it is null. Is the view visible?"
        }

    private val zodiacListViewModel: ZodiacListViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentZodiacListBinding.inflate(inflater, container, false)

        binding.zodiacRecyclerView.layoutManager = LinearLayoutManager(context)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                zodiacListViewModel.zodiacs.collect { zodiacs ->
                    binding.zodiacRecyclerView.adapter =
                        ZodiacListAdapter(zodiacs) {zodiacId ->
                            findNavController().navigate(
                                ZodiacListFragmentDirections.showZodiacDetail(zodiacId)
                            )
                        }
                }
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}