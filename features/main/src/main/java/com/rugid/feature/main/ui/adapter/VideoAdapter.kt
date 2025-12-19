package com.rugid.feature.main.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import com.rugid.core.model.Video
import com.rugid.core.ui.databinding.ItemVideoBinding
import com.rugid.core.ui.viewholder.VideoViewHolder

class VideoAdapter(
    private val onClick: (Video) -> Unit,
) : ListAdapter<Video, VideoViewHolder>(diffCallback) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        VideoViewHolder(
            ItemVideoBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )


    override fun onBindViewHolder(holder: VideoViewHolder, position: Int) {
        holder.bind(getItem(position), onClick)
    }

    companion object {
        private val diffCallback =
            object : DiffUtil.ItemCallback<Video>() {
                override fun areItemsTheSame(
                    oldItem: Video,
                    newItem: Video
                ) = oldItem.cover == newItem.cover

                override fun areContentsTheSame(
                    oldItem: Video,
                    newItem: Video
                ) = oldItem == newItem

            }
    }
}