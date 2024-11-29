package com.aliaklukin.imagestest.presentation.homescreen.adapter

import androidx.recyclerview.widget.RecyclerView
import com.aliaklukin.imagestest.databinding.ItemHitBinding
import com.aliaklukin.imagestest.presentation.model.HitLocal
import com.aliaklukin.imagestest.presentation.utils.loadImage

class HitViewHolder(
    private val binding: ItemHitBinding,
    private val onItemClicked: (HitLocal) -> Unit
) : RecyclerView.ViewHolder(binding.root) {

    fun bind(hit: HitLocal) {
        binding.run {
            val displayMetrics = root.context.resources.displayMetrics
            val halfScreenHeight = (displayMetrics.heightPixels / 2)
            root.layoutParams = root.layoutParams.apply {
                height = halfScreenHeight
            }

            thumbnail.loadImage(hit.largeImageURL)
            userTV.text = hit.user
            root.setOnClickListener {
                onItemClicked(hit)
            }
        }
    }
}