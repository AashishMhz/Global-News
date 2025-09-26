package com.example.globalnews.ui.newsadapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.globalnews.data.model.Article
import com.example.globalnews.databinding.ItemNewsVerticalBinding
import com.example.globalnews.utils.formatWithDateUtils
import com.example.globalnews.utils.load

class BottomNewaAdapter(private val onItemClick: (Article) -> Unit) : RecyclerView.Adapter<BottomNewaAdapter.NewsViewHolder>() {

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
        val binding = ItemNewsVerticalBinding.inflate(
            LayoutInflater.from(parent.context),
            parent, false
        )
        return NewsViewHolder(binding)
    }

    override fun onBindViewHolder(holder: NewsViewHolder, position: Int) {
        holder.bind(items[position])
    }

    override fun getItemCount(): Int {
        return items.size
    }

    inner class NewsViewHolder(private val binding: ItemNewsVerticalBinding) :
            RecyclerView.ViewHolder(binding.root) {
                fun bind(article: Article) {
                    val time = formatWithDateUtils(article.publishedAt)
                    binding.tvSource.text = article.source.name
                    binding.tvTitle.text = article.title
                    binding.tvTime.text = time
                    binding.imgNews.load(article.urlToImage)
                    binding.root.setOnClickListener {onItemClick(article)}
                }
            }
}