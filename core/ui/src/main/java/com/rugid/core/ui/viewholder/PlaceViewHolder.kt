package com.rugid.core.ui.viewholder

import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.rugid.core.model.Place
import com.rugid.core.ui.databinding.ItemPlaceBinding

class PlaceViewHolder(
    private val binding: ItemPlaceBinding
) : RecyclerView.ViewHolder(binding.root) {

    fun bind(model: Place, onClick: (Place) -> Unit) {
        Glide.with(binding.root)
            .load(model.cover)
            .centerCrop()
            .into(binding.ivPlaceCover)

        binding.tvPlaceTitle.text = model.title
        binding.tvPlaceCategory.text = model.category

        binding.root.setOnClickListener {
            onClick(model)
        }
    }

}