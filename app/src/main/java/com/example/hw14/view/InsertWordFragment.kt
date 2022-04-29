package com.example.hw14.view

import android.Manifest
import android.content.pm.PackageManager
import android.media.MediaPlayer
import android.media.MediaRecorder
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Patterns
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.example.hw14.databinding.FragmentInsertWordBinding
import com.example.hw14.model.Word
import com.example.hw14.viewmodel.WordViewModel
import com.google.android.material.textfield.TextInputLayout
import java.io.IOException


private const val REQUEST_RECORD_AUDIO_PERMISSION = 200


class InsertWordFragment : Fragment() {
    var countRecordState = 0
    private var recorder: MediaRecorder? = null
    private var fileName: String = ""
    private var player: MediaPlayer? = null
    private var permissionToRecordAccepted = false
    private var permissions: Array<String> = arrayOf(Manifest.permission.RECORD_AUDIO)
    private lateinit var binding: FragmentInsertWordBinding
    val wordViewModel: WordViewModel by activityViewModels()
    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        permissionToRecordAccepted = if (requestCode == REQUEST_RECORD_AUDIO_PERMISSION) {
            grantResults[0] == PackageManager.PERMISSION_GRANTED
        } else {
            false
        }
        if (!permissionToRecordAccepted)
            requireActivity().finish()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentInsertWordBinding.inflate(layoutInflater)
        ActivityCompat.requestPermissions(requireActivity(), permissions, REQUEST_RECORD_AUDIO_PERMISSION)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.buttonRecordVoice.setOnClickListener {
            if(validate()) {
                if (countRecordState == 0) {
                    binding.editTextTextWordTitle.isEnabled=false
                    val filename = binding.editTextTextWordTitle.editText?.text.toString()
                    fileName = "${activity?.externalCacheDir?.absolutePath}/$filename.3gp"
                }
                if (countRecordState % 2 == 0) {
                    startRecording()
                    Toast.makeText(
                        context,
                        "please stop recording after your work is finish",
                        Toast.LENGTH_SHORT
                    ).show()
                    binding.buttonRecordVoice.text = "Stop Record Voice"
                } else {
                    stopRecording()
                    binding.buttonRecordVoice.text = "Record Voice"
                }
                countRecordState++
            }
        }
        binding.buttonInsert.setOnClickListener {
            setError(binding.editTextTextWordTitle)
            setError(binding.editTextTextmeaning)
            setError(binding.editTextTextsynonym)
            setError(binding.editTextTextExample)
            setError(binding.editTextTextURL)
            if (validate()) {
                wordViewModel.insert(
                    Word(
                        0,
                        binding.editTextTextWordTitle.editText?.text.toString(),
                        binding.editTextTextmeaning.editText?.text.toString(),
                        binding.editTextTextExample.editText?.text.toString(),
                        binding.editTextTextsynonym.editText?.text.toString(),
                        binding.editTextTextURL.editText?.text.toString(),
                        binding.checkBoxfav.isChecked
                    )
                )

                binding.editTextTextWordTitle.editText?.text?.clear()
                binding.editTextTextmeaning.editText?.text?.clear()
                binding.editTextTextsynonym.editText?.text?.clear()
                binding.editTextTextExample.editText?.text?.clear()
                binding.editTextTextURL.editText?.text?.clear()
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

        if (binding.editTextTextURL.editText?.text.toString().isBlank()) {
            val word = binding.editTextTextWordTitle.editText?.text.toString().trim()
            binding.editTextTextURL.editText?.setText("https://en.wikipedia.org/wiki/$word")
            return false
        }

        // validate URL
        if (!Patterns.WEB_URL.matcher(binding.editTextTextURL.editText?.text.toString()).matches()) {
            binding.editTextTextURL.error = "please fill this field with a valid URL "
            return false
        }

        return true
    }

    fun setError(view: TextInputLayout) {
        view.error = null
    }

    private fun startRecording() {
        recorder = MediaRecorder().apply {
            setAudioSource(MediaRecorder.AudioSource.MIC)
            setOutputFormat(MediaRecorder.OutputFormat.THREE_GPP)
            setOutputFile(fileName)
            setAudioEncoder(MediaRecorder.AudioEncoder.AMR_NB)

            try {
                prepare()
            } catch (e: IOException) {
                Toast.makeText(context, "failed", Toast.LENGTH_SHORT).show()
            }

            start()
        }
    }

    private fun stopRecording() {
        recorder?.apply {
            stop()
            release()
        }
        recorder = null
    }

}