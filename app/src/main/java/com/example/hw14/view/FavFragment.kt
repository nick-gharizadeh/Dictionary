package com.example.hw14.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.example.hw14.R
import com.example.hw14.adaptor.FavWordAdaptor
import com.example.hw14.databinding.FragmentFavBinding
import com.example.hw14.databinding.FragmentInsertWordBinding
import com.example.hw14.databinding.FragmentSearchWordBinding
import com.example.hw14.viewmodel.WordViewModel


class FavFragment : Fragment() {
    private lateinit var binding: FragmentFavBinding
    val wordViewModel: WordViewModel by activityViewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentFavBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        wordViewModel.allFavWords?.observe(viewLifecycleOwner) {
            if (it != null) {
                val adapter = FavWordAdaptor() {
                    wordViewModel.selectedWord = it
                    findNavController().navigate(R.id.action_favFragment_to_detailFragment)
                }
                binding.recyclerviewFav.adapter = adapter
                adapter.submitList(it)
            }
        }
    }
}