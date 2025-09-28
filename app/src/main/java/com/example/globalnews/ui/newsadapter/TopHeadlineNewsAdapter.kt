package com.example.globalnews.ui.newsadapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.globalnews.domain.model.Article
import com.example.globalnews.databinding.ItemNewsHotizontalBinding
import com.example.globalnews.utils.formatWithDateUtils
import com.example.globalnews.utils.load

class TopHeadlineNewsAdapter(private val onItemClick: (Article) -> Unit) :
    RecyclerView.Adapter<TopHeadlineNewsAdapter.NewsViewHolder>() {

        private val items = mutableListOf<Article>()

    fun submitList(list: List<Article>) {
        items.clear()
        items.addAll(list)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): NewsViewHolder {
        val binding =
            ItemNewsHotizontalBinding.inflate(
                LayoutInflater.from(parent.context),
                parent, false
            )
        return NewsViewHolder(binding)
    }

    override fun onBindViewHolder(
        holder: NewsViewHolder,
        position: Int
    ) {
        holder.bind(items[position])
    }

    override fun getItemCount(): Int {
        return items.size
    }

    inner class NewsViewHolder(private val binding: ItemNewsHotizontalBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(article: Article) {
            val time = formatWithDateUtils(article.publishedAt)
            binding.tvTitle.text = article.title
            binding.imgNews.load(article.urlToImage)
            binding.tvSource.text = article.source.name
            binding.tvTime.text = time
            binding.root.setOnClickListener {onItemClick(article)}
        }
    }
}