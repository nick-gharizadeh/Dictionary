package com.example.hw14.view

import android.annotation.SuppressLint
import android.os.Build
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebViewClient
import androidx.annotation.RequiresApi
import androidx.fragment.app.activityViewModels
import com.example.hw14.R
import com.example.hw14.databinding.FragmentDetailBinding
import com.example.hw14.databinding.FragmentSearchWordBinding
import com.example.hw14.databinding.FragmentWebViewBinding
import com.example.hw14.viewmodel.WordViewModel


class WebViewFragment : Fragment() {
    private lateinit var binding: FragmentWebViewBinding
    val wordViewModel: WordViewModel by activityViewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentWebViewBinding.inflate(layoutInflater)
        return binding.root
    }

    @RequiresApi(Build.VERSION_CODES.O)
    @SuppressLint("SetJavaScriptEnabled")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.webView.webViewClient= WebViewClient()
        binding.webView.apply {
            var prefix1="https://"
            var prefix2="http://"
            wordViewModel.selectedWord?.let {
                if (it.URL.contains(prefix1) or it.URL.contains(prefix2))
                loadUrl(it.URL)
            else
                {
                   val validURL = prefix1 + it.URL
                    loadUrl(validURL)
                }
            }
            settings.javaScriptEnabled=true
        }
    }
}