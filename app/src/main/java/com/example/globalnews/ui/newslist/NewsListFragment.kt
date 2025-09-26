package com.example.globalnews.ui.newslist

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.globalnews.data.network.NewsRepository
import com.example.globalnews.databinding.FragmentNewsListBinding
import com.example.globalnews.ui.NewsViewModelFactory
import com.example.globalnews.ui.newsadapter.BottomNewaAdapter
import com.example.globalnews.ui.newsadapter.TopHeadlineNewsAdapter
import com.example.globalnews.ui.newsdetailpage.NewsDetailActivity
import com.example.globalnews.viewmodel.NewsViewModel
import com.google.android.material.snackbar.Snackbar

class NewsListFragment : Fragment() {

    private var _binding: FragmentNewsListBinding? = null
    private val binding get() = _binding!!
    private lateinit var bottomNewaAdapter: BottomNewaAdapter
    private lateinit var topHeadlineNewsAdapter: TopHeadlineNewsAdapter
    private lateinit var viewModel: NewsViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentNewsListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val newsRepository = NewsRepository()
        val factory = NewsViewModelFactory(newsRepository)
        viewModel = ViewModelProvider(this, factory)[NewsViewModel::class.java]

        topHeadlineNewsAdapter = TopHeadlineNewsAdapter { article ->
            openDetailNews(url = article.url)
        }

        bottomNewaAdapter = BottomNewaAdapter { article ->
            openDetailNews(url = article.url)
        }

        binding.recyclerVertical.apply {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = bottomNewaAdapter
        }
        binding.recyclerHorizontal.apply {
            layoutManager =
                LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
            adapter = topHeadlineNewsAdapter
        }

        binding.recyclerVertical.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)

                binding?.recyclerHorizontal?.scrollBy(dx, 0)
            }
        })

        binding.progressBar.visibility = View.VISIBLE
        observeData()
    }

    private fun observeData() {
        viewModel.topHeadlines.observe(viewLifecycleOwner) { articles ->
            binding.progressBar.visibility = View.GONE
            topHeadlineNewsAdapter.submitList(articles)
        }

        viewModel.allNews.observe(viewLifecycleOwner) { articles ->
            binding.progressBar.visibility = View.GONE
            bottomNewaAdapter.submitList(articles)
        }

        viewModel.availableSources.observe(viewLifecycleOwner) { articles ->
            binding.progressBar.visibility = View.GONE
            bottomNewaAdapter.submitList(articles)
            topHeadlineNewsAdapter.submitList(articles)
        }

        viewModel.error.observe(viewLifecycleOwner) { errorMessage ->
            binding.progressBar.visibility = View.GONE
            if (!errorMessage.isNullOrBlank()) {
                Snackbar.make(binding.root, errorMessage, Snackbar.LENGTH_SHORT).show()
            }
        }
    }

    private fun openDetailNews(url: String) {
        val intent = Intent(requireContext(), NewsDetailActivity::class.java)
        intent.putExtra(NewsDetailActivity.URL, url)
        startActivity(intent)
    }


    override fun onDestroyView() {
        super.onDestroyView()
        binding.recyclerVertical.clearOnScrollListeners()
        _binding = null
    }
}