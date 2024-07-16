package com.yomicepa.ui.returnRequests

import androidx.recyclerview.widget.DiffUtil
import com.yomicepa.domain.models.ReturnRequest

object ReturnRequestDiffCallback : DiffUtil.ItemCallback<ReturnRequest>() {
    override fun areItemsTheSame(oldItem: ReturnRequest, newItem: ReturnRequest): Boolean =
        oldItem.id == newItem.id

    override fun areContentsTheSame(oldItem: ReturnRequest, newItem: ReturnRequest): Boolean =
        oldItem == newItem
}