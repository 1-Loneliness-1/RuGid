package com.rugid.core.ui.viewholder

import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.GranularRoundedCorners
import com.rugid.core.model.Place
import com.rugid.core.ui.databinding.ItemPlaceBinding
import com.rugid.core.ui.extensions.dp

class PlaceViewHolder(
    private val binding: ItemPlaceBinding
) : RecyclerView.ViewHolder(binding.root) {

    fun bind(model: Place) {
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
            .into(binding.ivPlaceCover)

        binding.tvPlaceTitle.text = model.title
        binding.tvPlaceCategory.text = model.category
    }

}