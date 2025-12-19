package com.rugid.feature.main.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import com.rugid.core.model.Place
import com.rugid.core.ui.databinding.ItemPlaceBinding
import com.rugid.core.ui.viewholder.PlaceViewHolder

class PlaceAdapter(
    private val onClick: (Place) -> Unit
) : ListAdapter<Place, PlaceViewHolder>(diffCallback) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = PlaceViewHolder(
        ItemPlaceBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
    )

    override fun onBindViewHolder(holder: PlaceViewHolder, position: Int) {
        holder.bind(getItem(position), onClick)
    }

    companion object {
        private val diffCallback =
            object : DiffUtil.ItemCallback<Place>() {
                override fun areItemsTheSame(oldItem: Place, newItem: Place) =
                    oldItem.cover == newItem.cover

                override fun areContentsTheSame(oldItem: Place, newItem: Place) = oldItem == newItem
            }
    }

}