package com.rugid.feature.main.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import com.rugid.core.model.Article
import com.rugid.core.ui.databinding.ItemArticleBinding
import com.rugid.core.ui.viewholder.ArticleViewHolder

class ArticleAdapter(
    private val onClick: (Article) -> Unit
) : ListAdapter<Article, ArticleViewHolder>(diffCallback) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        ArticleViewHolder(
            ItemArticleBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )

    override fun onBindViewHolder(holder: ArticleViewHolder, position: Int) {
        holder.bind(getItem(position), onClick)
    }

    companion object {
        private val diffCallback =
            object : DiffUtil.ItemCallback<Article>() {
                override fun areItemsTheSame(
                    oldItem: Article,
                    newItem: Article
                ) = oldItem.cover == newItem.cover

                override fun areContentsTheSame(
                    oldItem: Article,
                    newItem: Article
                ) = oldItem == newItem

            }
    }

}