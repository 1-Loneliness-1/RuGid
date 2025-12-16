package com.rugid.feature.main.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import com.rugid.core.model.Excursion
import com.rugid.core.ui.databinding.ItemExcursionBinding
import com.rugid.core.ui.viewholder.ExcursionViewHolder

class ExcursionAdapter(
    private val onClick: (Excursion) -> Unit,
) : ListAdapter<Excursion, ExcursionViewHolder>(diffCallback) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        ExcursionViewHolder(
            ItemExcursionBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )


    override fun onBindViewHolder(holder: ExcursionViewHolder, position: Int) {
        holder.bind(getItem(position), onClick)
    }

    companion object {
        private val diffCallback =
            object : DiffUtil.ItemCallback<Excursion>() {
                override fun areItemsTheSame(
                    oldItem: Excursion,
                    newItem: Excursion
                ) = oldItem.cover == newItem.cover

                override fun areContentsTheSame(
                    oldItem: Excursion,
                    newItem: Excursion
                ) = oldItem == newItem

            }
    }

}