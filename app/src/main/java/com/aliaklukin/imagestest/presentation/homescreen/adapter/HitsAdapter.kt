package com.aliaklukin.imagestest.presentation.homescreen.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.aliaklukin.imagestest.databinding.ItemHitBinding
import com.aliaklukin.imagestest.presentation.model.HitLocal

class HitAdapter(
    private val onItemClicked: (HitLocal) -> Unit
) : RecyclerView.Adapter<HitViewHolder>() {

    private val items = mutableListOf<HitLocal>()
    private var currentPage = 1
    private var isLoading = false
    var onPageRequest: (Int) -> Unit = {}

    fun addItems(newItems: List<HitLocal>) {
        val startPosition = items.size
        val filteredItems = newItems.filter { it !in items }
        items.addAll(filteredItems)
        notifyItemRangeInserted(startPosition, filteredItems.size)
        isLoading = false
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HitViewHolder {
        val binding = ItemHitBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return HitViewHolder(binding, onItemClicked)
    }

    override fun onBindViewHolder(holder: HitViewHolder, position: Int) {
        holder.bind(items[position])

        if (!isLoading && position >= items.size - 10) {
            isLoading = true
            currentPage++
            onPageRequest(currentPage)
        }
    }

    override fun getItemCount(): Int = items.size
}