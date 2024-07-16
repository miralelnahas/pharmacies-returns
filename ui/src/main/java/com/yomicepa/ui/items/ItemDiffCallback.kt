package com.yomicepa.ui.items

import androidx.recyclerview.widget.DiffUtil
import com.yomicepa.domain.models.Item

object ItemDiffCallback : DiffUtil.ItemCallback<Item>() {
    override fun areItemsTheSame(oldItem: Item, newItem: Item): Boolean =
        oldItem.id == newItem.id

    override fun areContentsTheSame(oldItem: Item, newItem: Item): Boolean =
        oldItem == newItem
}