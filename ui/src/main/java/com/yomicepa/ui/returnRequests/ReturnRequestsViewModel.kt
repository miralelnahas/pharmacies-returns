package com.yomicepa.ui.returnRequests

import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.yomicepa.domain.models.ReturnRequest
import com.yomicepa.domain.usecases.GetReturnRequestsUseCase
import com.yomicepa.ui.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ReturnRequestsViewModel @Inject constructor(private val getReturnRequestsUseCase: GetReturnRequestsUseCase) :
    BaseViewModel() {

    private val _event = Channel<ReturnRequestsEvent>(Channel.BUFFERED)
    val event = _event.receiveAsFlow()

    lateinit var returnRequests: Flow<PagingData<ReturnRequest>>

    init {
        viewModelScope.launch {
            returnRequests = getReturnRequestsUseCase().cachedIn(viewModelScope)
        }
    }

    fun onCreateReturnRequestsClick() {
        sendEvent(_event, ReturnRequestsEvent.CreateClick)
    }

}