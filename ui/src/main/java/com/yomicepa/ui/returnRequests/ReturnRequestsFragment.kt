package com.yomicepa.ui.returnRequests

import androidx.fragment.app.viewModels
import com.yomicepa.ui.R
import com.yomicepa.ui.base.BaseFragment
import com.yomicepa.ui.databinding.FragmentReturnRequestsBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ReturnRequestsFragment : BaseFragment<FragmentReturnRequestsBinding>(R.layout.fragment_return_requests){
    override val vm: ReturnRequestsViewModel by viewModels()
}