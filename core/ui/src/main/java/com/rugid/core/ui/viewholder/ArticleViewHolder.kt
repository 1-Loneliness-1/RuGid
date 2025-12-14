package com.rugid.core.ui.viewholder

import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.GranularRoundedCorners
import com.rugid.core.model.Article
import com.rugid.core.ui.databinding.ItemArticleBinding
import com.rugid.core.ui.extensions.dp

class ArticleViewHolder(
    private val binding: ItemArticleBinding
) : RecyclerView.ViewHolder(binding.root) {

    fun bind(model: Article) {
        Glide.with(binding.root)
            .load(model.cover)
            .centerCrop()
            .transform(
                GranularRoundedCorners(
                    10.dp.toFloat(),
                    10.dp.toFloat(),
                    0f,
                    0f
                )
            )
            .into(binding.ivArticleCover)

        binding.tvArticleTitle.text = model.title
    }

}