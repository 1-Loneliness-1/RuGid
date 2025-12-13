package com.rugid.core.ui.viewholder

import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.rugid.core.model.Video
import com.rugid.core.ui.databinding.ItemVideoBinding

class VideoViewHolder(
    private val binding: ItemVideoBinding
) : RecyclerView.ViewHolder(binding.root) {

    fun bind(model: Video) {
        Glide.with(binding)
    }

}