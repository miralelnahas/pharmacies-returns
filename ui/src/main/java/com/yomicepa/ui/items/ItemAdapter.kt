package com.yomicepa.ui.items

import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import com.yomicepa.domain.models.Item
import com.yomicepa.ui.R
import com.yomicepa.ui.base.BaseViewHolder
import com.yomicepa.ui.databinding.ItemRequestItemBinding

class ItemAdapter(
    private val editCallback: (Item) -> Unit,
    private val deleteCallback: (Int) -> Unit
) :
    ListAdapter<Item, BaseViewHolder>(ItemDiffCallback) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder =
        BaseViewHolder.from(parent, R.layout.item_request_item)

    override fun onBindViewHolder(holder: BaseViewHolder, position: Int) {
        getItem(position)?.let { item ->
            (holder.bind(item) as ItemRequestItemBinding).apply {
                buttonEdit.setOnClickListener { editCallback(item) }
                buttonDelete.setOnClickListener { deleteCallback(item.id) }
            }
        }

    }

}