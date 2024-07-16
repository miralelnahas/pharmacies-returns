package com.yomicepa.ui.createRequest

import androidx.core.widget.doOnTextChanged
import androidx.fragment.app.viewModels
import com.yomicepa.ui.R
import com.yomicepa.ui.base.BaseFragment
import com.yomicepa.ui.databinding.FragmentCreateRequestBinding
import com.yomicepa.ui.utils.observe
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CreateRequestFragment :
    BaseFragment<FragmentCreateRequestBinding>(R.layout.fragment_create_request) {
    override val vm: CreateRequestViewModel by viewModels()
    //TODO: update wholesaler

    override fun initViews() {
        super.initViews()
        vb.tvServiceType.doOnTextChanged { text, _, _, _ -> vm.setServiceType(text.toString()) }
    }

    override fun setupObservers() {
        super.setupObservers()
        observe(vm.event) {
            when(it) {
                is CreateRequestEvent.CreateRequestSuccess -> {
                    navigateTo(CreateRequestFragmentDirections.actionCreateRequestToAddItem(it.requestId))
                }

            }
        }
    }
}