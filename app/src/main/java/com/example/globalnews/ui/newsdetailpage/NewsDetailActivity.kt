package com.example.globalnews.ui.newsdetailpage

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.View
import android.webkit.WebChromeClient
import android.webkit.WebResourceError
import android.webkit.WebResourceRequest
import android.webkit.WebSettings
import android.webkit.WebView
import android.webkit.WebViewClient
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.globalnews.databinding.ActivityNewsDetailBinding
import com.example.globalnews.utils.setCustomStatusBar
import com.google.android.material.snackbar.Snackbar

class NewsDetailActivity : AppCompatActivity() {

    private lateinit var binding: ActivityNewsDetailBinding

    @SuppressLint("SetJavaScriptEnabled")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityNewsDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setSupportActionBar(binding.appBar.toolbar)

        setCustomStatusBar()

        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowTitleEnabled(false)

        val url = intent.getStringExtra(URL)
        binding.progressBar.visibility = View.VISIBLE

        binding.detailPageWebView.webViewClient = object : WebViewClient() {
            override fun onPageFinished(view: WebView?, url: String?) {
                super.onPageFinished(view, url)
                binding.progressBar.visibility = View.GONE
            }

            override fun onReceivedError(
                view: WebView?,
                request: WebResourceRequest?,
                error: WebResourceError?
            ) {
                if (request?.isForMainFrame == true) {
                    binding.progressBar.visibility = View.GONE
                    Snackbar.make(
                        binding.root,
                        "Failed to load page. Please try again.",
                        Snackbar.LENGTH_SHORT
                    ).show()
                }

                super.onReceivedError(view, request, error)
            }
        }


        binding.detailPageWebView.settings.apply {
            binding.detailPageWebView.settings.javaScriptEnabled = true
            binding.detailPageWebView.settings.domStorageEnabled = true
            binding.detailPageWebView.settings.loadsImagesAutomatically = true
            binding.detailPageWebView.settings.useWideViewPort = true
            binding.detailPageWebView.settings.loadWithOverviewMode = true
            binding.detailPageWebView.settings.javaScriptCanOpenWindowsAutomatically = true
            mixedContentMode = WebSettings.MIXED_CONTENT_ALWAYS_ALLOW
        }

        if (url != null) {
            binding.detailPageWebView.loadUrl(url)
        } else {
            binding.progressBar.visibility = View.GONE
            Toast.makeText(this, "Invalid URL", Toast.LENGTH_SHORT).show()
        }

        binding.detailPageWebView.webChromeClient = object : WebChromeClient() {
            override fun onProgressChanged(view: WebView?, newProgress: Int) {
                binding.progressBar.progress = newProgress

                if (newProgress == 100) {
                    binding.progressBar.visibility = View.GONE
                }
            }
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressedDispatcher.onBackPressed()
        return true
    }

    companion object {
        const val URL = "url"
    }
}