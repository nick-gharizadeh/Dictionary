package com.example.hw14.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.example.hw14.R
import com.example.hw14.databinding.FragmentEditWordBinding
import com.example.hw14.model.Word
import com.example.hw14.viewmodel.WordViewModel
import com.google.android.material.textfield.TextInputLayout


class EditWordFragment : Fragment() {
    private lateinit var binding: FragmentEditWordBinding
    val wordViewModel: WordViewModel by activityViewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentEditWordBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViews()
        binding.buttonSubmit.setOnClickListener {
            setError(binding.editTextTextWordTitle)
            setError(binding.editTextTextmeaning)
            setError(binding.editTextTextsynonym)
            setError(binding.editTextTextExample)
            setError(binding.editTextTextURL)
            if (validate()) {
                val newWord =
                    Word(
                        wordViewModel.selectedWord!!.id,
                        binding.editTextTextWordTitle.editText?.text.toString(),
                        binding.editTextTextmeaning.editText?.text.toString(),
                        binding.editTextTextExample.editText?.text.toString(),
                        binding.editTextTextsynonym.editText?.text.toString(),
                        binding.editTextTextURL.editText?.text.toString()
                    )
                wordViewModel.updateWord(newWord)
                Toast.makeText(context, "successful ✏️✔️", Toast.LENGTH_SHORT).show()
                findNavController().navigate(R.id.action_editWordFragment_to_searchWordFragment)

            }
        }
    }

    private fun initViews() {
        binding.editTextTextWordTitle.editText?.append(wordViewModel.selectedWord?.wordTitle)
        binding.editTextTextmeaning.editText?.append(wordViewModel.selectedWord?.meaning)
        binding.editTextTextsynonym.editText?.append(wordViewModel.selectedWord?.synonyms)
        binding.editTextTextExample.editText?.append(wordViewModel.selectedWord?.example)
        binding.editTextTextURL.editText?.append(wordViewModel.selectedWord?.example)
    }

    fun validate(): Boolean {
        if (binding.editTextTextWordTitle.editText?.text.isNullOrBlank()) {
            binding.editTextTextWordTitle.error = "please fill word  "
            return false
        }

        if (binding.editTextTextmeaning.editText?.text.toString().isBlank()) {
            binding.editTextTextmeaning.error = "please fill meaning  "
            return false
        }

        if (binding.editTextTextExample.editText?.text.toString().isBlank()) {
            binding.editTextTextExample.error = "please fill example  "
            return false
        }

        if (binding.editTextTextsynonym.editText?.text.toString().isBlank()) {
            binding.editTextTextsynonym.error = "please fill synonym "
            return false
        }
        if (binding.editTextTextURL.editText?.text.toString().isBlank()) {
            binding.editTextTextURL.error = "please fill URL "
            return false
        }


        return true
    }
    fun setError(view: TextInputLayout) {
        view.error =null
    }
}