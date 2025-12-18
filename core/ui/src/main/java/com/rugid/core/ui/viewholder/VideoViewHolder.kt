package com.rugid.core.ui.viewholder

import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.rugid.core.model.Video
import com.rugid.core.ui.databinding.ItemVideoBinding

class VideoViewHolder(
    private val binding: ItemVideoBinding
) : RecyclerView.ViewHolder(binding.root) {

    fun bind(model: Video, onClick: (Video) -> Unit) {
        Glide.with(binding.root)
            .load(model.cover)
            .centerCrop()
            .into(binding.ivVideoPreview)

        binding.tvVideoTitle.text = model.title

        binding.root.setOnClickListener {
            onClick(model)
        }
    }

}