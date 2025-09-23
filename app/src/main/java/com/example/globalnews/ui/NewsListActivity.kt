package com.example.globalnews.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.globalnews.databinding.ActivityNewsListBinding
import com.example.globalnews.viewmodel.NewsViewModel

class NewsListActivity : AppCompatActivity(){

    private lateinit var viewModel: NewsViewModel
    private lateinit var binding: ActivityNewsListBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityNewsListBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setSupportActionBar(binding.appBarMain.toolbar)

    }
}