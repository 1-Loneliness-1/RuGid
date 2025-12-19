package com.rugid.core.ui.viewholder

import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.rugid.core.model.Article
import com.rugid.core.ui.databinding.ItemArticleBinding

class ArticleViewHolder(
    private val binding: ItemArticleBinding
) : RecyclerView.ViewHolder(binding.root) {

    fun bind(model: Article, onClick: (Article) -> Unit) {
        Glide.with(binding.root)
            .load(model.cover)
            .centerCrop()
            .into(binding.ivArticleCover)

        binding.tvArticleTitle.text = model.title

        binding.root.setOnClickListener {
            onClick(model)
        }
    }

}