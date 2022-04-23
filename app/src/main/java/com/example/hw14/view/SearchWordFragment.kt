package com.example.hw14.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.example.hw14.R
import com.example.hw14.adaptor.WordAdaptor
import com.example.hw14.databinding.FragmentSearchWordBinding
import com.example.hw14.model.Word
import com.example.hw14.viewmodel.WordViewModel
import com.google.android.material.dialog.MaterialAlertDialogBuilder


class SearchWordFragment : Fragment() {
    private lateinit var binding: FragmentSearchWordBinding
    val wordViewModel: WordViewModel by activityViewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentSearchWordBinding.inflate(inflater, container, false)
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        wordViewModel.allWords?.observe(viewLifecycleOwner) {
            if (it != null) {
                val adapter = WordAdaptor() {
                    wordViewModel.selectedWord = it
                    findNavController().navigate(R.id.action_searchWordFragment_to_detailFragment)
                }
                binding.recyclerview.adapter = adapter
                adapter.submitList(it)
            }
        }
        val wordCountObserver = Observer<Int> { count ->
            binding.textViewCount.text = count.toString()
        }
        wordViewModel.countLiveData?.observe(viewLifecycleOwner, wordCountObserver)
        binding.floatingActionButtonAdd.setOnClickListener {
            findNavController().navigate(R.id.action_searchWordFragment_to_insertWordFragment)
        }
        binding.buttonfav.setOnClickListener {
            findNavController().navigate(R.id.action_searchWordFragment_to_favFragment)
        }
        binding.buttonSearch.setOnClickListener {
            if (validate()) {
                val word =
                    wordViewModel.findWord(binding.editTextTextWordTitle.editText?.text.toString())
                if (word != null) {
                    wordViewModel.selectedWord = word
                    findNavController().navigate(R.id.action_searchWordFragment_to_detailFragment)
                } else {
                    MaterialAlertDialogBuilder(requireContext())
                        .setTitle("Not Found!")
                        .setMessage("This word is not found!! please try another word...")
                        .setPositiveButton("OK") { dialog, which ->
                            binding.editTextTextWordTitle.editText?.text?.clear()
                        }
                        .show()
                }
            }
        }

    }

    fun validate(): Boolean {
        if (binding.editTextTextWordTitle.editText?.text.isNullOrBlank()) {
            Toast.makeText(context, "Please fill the search box", Toast.LENGTH_SHORT).show()
            return false
        }
        return true
    }


}