package com.example.hw14.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.example.hw14.R
import com.example.hw14.databinding.FragmentDetailBinding
import com.example.hw14.viewmodel.WordViewModel


class DetailFragment : Fragment() {
    private lateinit var binding: FragmentDetailBinding
    val wordViewModel: WordViewModel by activityViewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentDetailBinding.inflate(layoutInflater)
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViews()
        binding.textViewURL.setOnClickListener {
            findNavController().navigate(R.id.action_detailFragment_to_webViewFragment)
        }
        binding.buttonDelete.setOnClickListener {
            wordViewModel.selectedWord?.wordTitle?.let { it1 -> wordViewModel.deleteWord(it1) }
            Toast.makeText(context,"Word Deleted successfully",Toast.LENGTH_SHORT).show()
            findNavController().navigate(R.id.action_detailFragment_to_searchWordFragment)
        }

        binding.buttonEdit.setOnClickListener {
            findNavController().navigate(R.id.action_detailFragment_to_editWordFragment)
        }

    }

    fun initViews() {
        clearText(binding.textViewWord)
        clearText(binding.textViewMeaning)
        clearText(binding.textViewExample)
        clearText(binding.textViewSynonym)
        clearText(binding.textViewURL)
        if (wordViewModel.selectedWord != null) {
            binding.textViewWord.append(wordViewModel.selectedWord?.wordTitle)
            binding.textViewMeaning.append(wordViewModel.selectedWord?.meaning)
            binding.textViewExample.append(wordViewModel.selectedWord?.example)
            binding.textViewSynonym.append(wordViewModel.selectedWord?.synonyms)
            binding.textViewURL.append(wordViewModel.selectedWord?.URL)
        }
    }

    fun clearText(view: TextView): String {
        val testList = view.text.split(':')
        return testList[0] + ": "

    }


}