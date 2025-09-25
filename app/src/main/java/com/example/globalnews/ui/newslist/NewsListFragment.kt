package com.example.globalnews.ui.newslist

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
import com.example.globalnews.viewmodel.NewsViewModel

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

        }

        bottomNewaAdapter = BottomNewaAdapter { article ->

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

                binding.recyclerHorizontal.scrollBy(dx, 0)
            }
        })


        observeData()
    }

    private fun observeData() {
        viewModel.topHeadlines.observe(viewLifecycleOwner) { articles ->
            topHeadlineNewsAdapter.submitList(articles)
        }

        viewModel.allNews.observe(viewLifecycleOwner) { articles ->
            bottomNewaAdapter.submitList(articles)
        }

        viewModel.availableSources.observe(viewLifecycleOwner) { articles ->
            bottomNewaAdapter.submitList(articles)
            topHeadlineNewsAdapter.submitList(articles)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}