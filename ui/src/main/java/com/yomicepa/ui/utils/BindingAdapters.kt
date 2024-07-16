package com.yomicepa.ui.utils

import android.view.View
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView

object BindingAdapters {
    @JvmStatic
    @BindingAdapter("app:items")
    fun RecyclerView.setItems(items: List<Nothing>?) {
        setItems(adapter, items)
    }

    fun <VH : RecyclerView.ViewHolder> setItems(
        adapter: RecyclerView.Adapter<VH>?,
        items: List<Nothing>?
    ) {
        if (adapter is ListAdapter<*, *>) {
            (adapter as? ListAdapter<*, *>)?.submitList(items)
        } else {
            adapter?.notifyDataSetChanged()
        }
    }

    @JvmStatic
    @BindingAdapter("app:goneUnless")
    fun View.goneUnless(show: Boolean) {
        visibility = if (show) View.VISIBLE else View.GONE
    }
}