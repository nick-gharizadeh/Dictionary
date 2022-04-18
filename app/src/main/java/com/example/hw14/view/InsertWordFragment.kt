package com.example.hw14.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import com.example.hw14.databinding.FragmentInsertWordBinding
import com.example.hw14.model.Word
import com.example.hw14.viewmodel.WordViewModel
import com.google.android.material.textfield.TextInputLayout


class InsertWordFragment : Fragment() {
    private lateinit var binding: FragmentInsertWordBinding
    val wordViewModel: WordViewModel by activityViewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentInsertWordBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.button.setOnClickListener {
            setError(binding.editTextTextWordTitle)
            setError(binding.editTextTextmeaning)
            setError(binding.editTextTextsynonym)
            setError(binding.editTextTextExample)
            if (validate()) {
                wordViewModel.insert(
                    Word(
                        0,
                        binding.editTextTextWordTitle.editText?.text.toString(),
                        binding.editTextTextmeaning.editText?.text.toString(),
                        binding.editTextTextExample.editText?.text.toString(),
                        binding.editTextTextsynonym.editText?.text.toString()
                    )
                )
                binding.editTextTextWordTitle.editText?.text?.clear()
                binding.editTextTextmeaning.editText?.text?.clear()
                binding.editTextTextsynonym.editText?.text?.clear()
                binding.editTextTextExample.editText?.text?.clear()
            }
        }


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


        return true
    }

    fun setError(view: TextInputLayout) {
        view.error =null
    }

}