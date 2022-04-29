package com.example.hw14.view

import android.media.MediaPlayer
import android.os.Bundle
import android.util.Log
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
import java.io.IOException


class DetailFragment : Fragment() {
    private lateinit var binding: FragmentDetailBinding
    var countPlayState = 0
    private var player: MediaPlayer? = null
    private var fileName: String = ""
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
        val filename =wordViewModel.selectedWord?.wordTitle
        fileName = "${activity?.externalCacheDir?.absolutePath}/$filename.3gp"
        if (player == null)
        { binding.buttonPlayVoice.visibility=View.GONE
        }
        binding.buttonPlayVoice.setOnClickListener {
            if (countPlayState%2==0)
            { startPlaying()
                binding.buttonPlayVoice.text="Stop Playing"
            }
            else {
                stopPlaying()
                binding.buttonPlayVoice.text="Play Voice"
            }
            countPlayState++
        }
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

    private fun startPlaying() {
        player = MediaPlayer().apply {
            try {
                setDataSource(fileName)
                prepare()
                start()
            } catch (e: IOException) {
                Toast.makeText(context, "You don't record voice for this word", Toast.LENGTH_SHORT).show()
            }
        }

    }

    private fun stopPlaying() {
        player?.release()
        player = null
    }

    override fun onStop() {
        super.onStop()
        player?.release()
        player = null
    }
}