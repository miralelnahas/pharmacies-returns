package com.yomicepa.ui.returnRequests

import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.paging.LoadState
import com.yomicepa.ui.R
import com.yomicepa.ui.base.BaseFragment
import com.yomicepa.ui.databinding.FragmentReturnRequestsBinding
import com.yomicepa.ui.utils.observe
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@AndroidEntryPoint
class ReturnRequestsFragment :
    BaseFragment<FragmentReturnRequestsBinding>(R.layout.fragment_return_requests) {
    override val vm: ReturnRequestsViewModel by viewModels()
    val adapter = ReturnRequestAdapter {
        navigateTo(ReturnRequestsFragmentDirections.actionReturnRequestsToItems(it.toInt()))
    }

    override fun initViews() {
        super.initViews()
        vb.rvReturnRequests.adapter = adapter
        lifecycleScope.launch(Dispatchers.Main) {
            adapter.loadStateFlow.collectLatest { loadStates ->
                if (loadStates.refresh is LoadState.Loading) {
                    vm.showLoader(true)
                } else {
                    vm.showLoader(false)
                    if (loadStates.refresh is LoadState.Error) {
                        if (adapter.itemCount < 1) {
                            vm.showEmptyView(true)
                        } else {
                            vm.showEmptyView(false)
                        }
                    }
                }
            }
        }
    }

    override fun setupObservers() {
        super.setupObservers()
        observe(vm.event) {
            when (it) {
                ReturnRequestsEvent.CreateClick -> {
                    navigateTo(ReturnRequestsFragmentDirections.actionRequestsToCreateRequest())
                }
            }
        }

        observe(vm.returnRequests) {
            adapter.submitData(viewLifecycleOwner.lifecycle, it)
        }
    }
}