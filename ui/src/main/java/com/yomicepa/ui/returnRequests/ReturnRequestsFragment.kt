package com.yomicepa.ui.returnRequests

import androidx.fragment.app.viewModels
import com.yomicepa.ui.R
import com.yomicepa.ui.base.BaseFragment
import com.yomicepa.ui.databinding.FragmentReturnRequestsBinding
import com.yomicepa.ui.utils.observe
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ReturnRequestsFragment :
    BaseFragment<FragmentReturnRequestsBinding>(R.layout.fragment_return_requests) {
    override val vm: ReturnRequestsViewModel by viewModels()

    override fun initViews() {
        super.initViews()
        val adapter = ReturnRequestAdapter()
        vb.rvReturnRequests.adapter = adapter

        observe(vm.event) {
            when (it) {
                ReturnRequestsEvent.CreateClick -> {//TODO: navigate
                }
            }
        }

        observe(vm.returnRequests) {
            adapter.submitData(viewLifecycleOwner.lifecycle, it)
        }
    }
}