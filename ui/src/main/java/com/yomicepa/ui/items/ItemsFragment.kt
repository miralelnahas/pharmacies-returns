package com.yomicepa.ui.items

import android.widget.EditText
import androidx.fragment.app.viewModels
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.yomicepa.domain.models.Item
import com.yomicepa.ui.R
import com.yomicepa.ui.base.BaseFragment
import com.yomicepa.ui.databinding.FragmentItemsBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ItemsFragment : BaseFragment<FragmentItemsBinding>(R.layout.fragment_items) {
    override val vm: ItemsViewModel by viewModels()

    override fun initViews() {
        super.initViews()
        vb.rvItems.adapter = ItemAdapter(editCallback = { item -> onEditClick(item) },
            deleteCallback = { id -> onDeleteClick(id) })
    }

    private fun onDeleteClick(id: Int) {
        showConfirmationDialog(getString(R.string.confirmation_delete),
            getString(R.string.delete),
            getString(
                R.string.cancel
            ),
            {
                vm.deleteItem(id)
            },
            {})
    }

    private fun onEditClick(item: Item) {
        val view = layoutInflater.inflate(R.layout.layout_edit_item, null)
        val editTextDescription = view.findViewById<EditText>(R.id.editText)
        editTextDescription.setText(item.description)
        MaterialAlertDialogBuilder(requireContext()).setTitle(getString(R.string.edit_item))
            .setView(view).setPositiveButton(
                getString(R.string.update)
            ) { _, _ -> vm.editItem(item, editTextDescription.text.toString()) }.setNegativeButton(
                getString(
                    R.string.cancel
                ), null
            ).show()
    }
}