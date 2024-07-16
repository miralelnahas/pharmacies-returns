package com.yomicepa.ui.returnRequests

import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.yomicepa.domain.models.ReturnRequest
import com.yomicepa.domain.usecases.GetReturnRequestsUseCase
import com.yomicepa.domain.usecases.IsNetworkConnectedUseCase
import com.yomicepa.domain.usecases.LogoutUseCase
import com.yomicepa.ui.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ReturnRequestsViewModel @Inject constructor(
    private val getReturnRequestsUseCase: GetReturnRequestsUseCase,
    private val logoutUseCase: LogoutUseCase,
    isNetworkConnectedUseCase: IsNetworkConnectedUseCase
) : BaseViewModel(isNetworkConnectedUseCase) {

    private val _event = Channel<ReturnRequestsEvent>(Channel.BUFFERED)
    val event = _event.receiveAsFlow()

    var returnRequests: Flow<PagingData<ReturnRequest>> = flowOf()

    init {
        val request: () -> Unit = {
            showErrorView(false)
            viewModelScope.launch {
                returnRequests = getReturnRequestsUseCase().cachedIn(viewModelScope)
            }
        }
        if (isNetworkConnectedUseCase()) {
            request()
        } else {
            enqueueRequest(request)
            showErrorView(true)
        }
    }

    fun onCreateReturnRequestsClick() {
        sendEvent(_event, ReturnRequestsEvent.CreateClick)
    }

    fun onLogoutClick() {
        launchRequest({ logoutUseCase() }, { sendEvent(_event, ReturnRequestsEvent.LogoutSuccess) })
    }

}