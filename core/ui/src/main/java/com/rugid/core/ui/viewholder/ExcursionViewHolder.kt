package com.rugid.core.ui.viewholder

import androidx.core.view.isVisible
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.GranularRoundedCorners
import com.rugid.core.model.Excursion
import com.rugid.core.ui.databinding.ItemExcursionBinding
import com.rugid.core.ui.extensions.dp
import java.util.Locale

class ExcursionViewHolder(
    private val binding: ItemExcursionBinding
) : RecyclerView.ViewHolder(binding.root) {

    fun bind(model: Excursion) {
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
            isVisible = model.sale != 0
            text = String.format(Locale.getDefault(), "- %d%", model.sale)
        }
    }

}