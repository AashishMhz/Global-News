package com.example.globalnews

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.globalnews.databinding.ActivityMainBinding
import com.example.globalnews.ui.newslist.NewsListFragment
import com.example.globalnews.utils.setCustomStatusBar

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setSupportActionBar(binding.appBarMain.toolbar)
        setCustomStatusBar()

        supportFragmentManager.beginTransaction()
            .replace(binding.container.id, NewsListFragment())
            .commit()
    }
}