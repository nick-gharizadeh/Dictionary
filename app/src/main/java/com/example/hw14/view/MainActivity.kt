package com.example.hw14.view

import android.graphics.Color
import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.example.hw14.databinding.ActivityMainBinding
import com.example.hw14.viewmodel.WordViewModel


class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    val mainViewModel: WordViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        supportActionBar?.hide()
        binding.imageViewsplash.alpha=0f
        binding.imageViewsplash.animate().setDuration(3000).alpha(1f).withEndAction{
           binding.group.visibility=View.GONE
            supportActionBar?.show()
            binding.constaint.setBackgroundColor(Color.TRANSPARENT)
            binding.fragmentContainerView.visibility=View.VISIBLE
            overridePendingTransition(android.R.anim.fade_in,android.R.anim.fade_out)
        }
    }



}