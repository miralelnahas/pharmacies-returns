package com.yomicepa.ui.returnRequests

import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.ListAdapter
import com.yomicepa.domain.models.ReturnRequest
import com.yomicepa.ui.R
import com.yomicepa.ui.base.BaseViewHolder

class ReturnRequestAdapter : PagingDataAdapter<ReturnRequest, BaseViewHolder>(ReturnRequestDiffCallback) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder =
        BaseViewHolder.from(parent, R.layout.item_return_request)

    override fun onBindViewHolder(holder: BaseViewHolder, position: Int) {
        holder.bind(getItem(position))
    }
}