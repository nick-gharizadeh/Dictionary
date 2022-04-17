package com.example.hw14.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.example.hw14.R
import com.example.hw14.databinding.ActivityMainBinding
import com.example.hw14.databinding.FragmentInsertWordBinding


class InsertWordFragment : Fragment() {
    private lateinit var binding: FragmentInsertWordBinding
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

        binding.editTextTextWordTitle.setOnClickListener {
            Toast.makeText(context, binding.editTextTextWordTitle.editText?.text.toString(),Toast.LENGTH_SHORT).show()

        }
    }
}