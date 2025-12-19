package com.rugid.core.ui.viewholder

import androidx.core.view.isInvisible
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.rugid.core.model.Excursion
import com.rugid.core.ui.databinding.ItemExcursionBinding
import java.util.Locale

class ExcursionViewHolder(
    private val binding: ItemExcursionBinding
) : RecyclerView.ViewHolder(binding.root) {

    fun bind(model: Excursion, onClick: (Excursion) -> Unit) {
        Glide.with(binding.root)
            .load(model.cover)
            .centerCrop()
            .into(binding.ivExcursionCover)

        binding.tvExcursionTitle.text = model.title
        binding.tvExcursionCategory.text = model.category
        binding.tvExcursionType.text = model.type
        binding.tvExcursionDuration.text =
            String.format(Locale.getDefault(), "%dч", model.duration)
        binding.tvExcursionNumOfPeople.text =
            String.format(Locale.getDefault(), "До %d", model.numberOfPeople)

        binding.tvExcursionPrice.text =
            String.format(Locale.getDefault(), "от %d ₽", model.price)

        with(binding.tvExcursionSale) {
            isInvisible = model.sale == 0
            text = String.format(Locale.getDefault(), "- %d%%", model.sale)
        }

        binding.root.setOnClickListener {
            onClick(model)
        }
    }

}