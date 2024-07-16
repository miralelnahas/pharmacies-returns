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
    //TODO: ADD date picker

    override fun setupObservers() {
        super.setupObservers()
        observe(vm.event) {
            when(it) {
                AddItemEvent.ItemAddedSuccessfully -> {
                    showSnackBar("Item Added Successfully")
                }
                is AddItemEvent.ItemsClicked -> {
                    //navigate
                }
            }
        }
    }
}