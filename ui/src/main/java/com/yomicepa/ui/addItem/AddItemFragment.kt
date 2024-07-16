package com.yomicepa.ui.addItem

import androidx.fragment.app.viewModels
import com.yomicepa.ui.R
import com.yomicepa.ui.base.BaseFragment
import com.yomicepa.ui.databinding.FragmentAddItemBinding
import com.yomicepa.ui.utils.observe
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class AddItemFragment : BaseFragment<FragmentAddItemBinding>(R.layout.fragment_add_item) {
    override val vm: AddItemViewModel by viewModels()
    override fun setupObservers() {
        super.setupObservers()
        observe(vm.event) {
            when (it) {
                AddItemEvent.ItemAddedSuccessfully -> {
                    showSnackBar(getString(R.string.add_item_success))
                }

                is AddItemEvent.ItemsClicked -> {
                    navigateTo(AddItemFragmentDirections.actionAddItemToItems(it.requestId))
                }

                AddItemEvent.HandleEmptyErrors -> {
                    if (vm.getExpirationMonth().value.isEmpty())
                        vb.ilExpirationMonth.error = getString(R.string.error_empty_expiration_date)
                    if (vm.getExpirationYear().value.isEmpty())
                        vb.ilExpirationYear.error = getString(R.string.error_empty_expiration_date)
                }

                AddItemEvent.ClearErrors -> {
                    vb.ilExpirationMonth.error = null
                    vb.ilExpirationYear.error = null
                }
            }
        }
    }
}