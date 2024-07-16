package com.yomicepa.ui.base

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

abstract class BaseViewModel : ViewModel() {

    private val _baseEvent = Channel<BaseEvent>(Channel.BUFFERED)
    val baseEvent = _baseEvent.receiveAsFlow()

    protected val _showLoader = MutableStateFlow(false)
    val showLoader = _showLoader.asStateFlow()

    protected val _showEmptyView = MutableStateFlow(false)
    val showEmptyView = _showEmptyView.asStateFlow()

    fun <T> Flow<T>.toStateFlow(initValue: T): StateFlow<T> =
        this.stateIn(viewModelScope, SharingStarted.WhileSubscribed(), initValue)

    open fun onBackClick() = sendEvent(_baseEvent, BaseEvent.Back)

    fun <T> sendEvent(channel: Channel<T>, event: T) {
        viewModelScope.launch {
            channel.send(event)
        }
    }

    fun <T> launchRequest(
        query: suspend () -> Result<T>,
        onSuccess: (T) -> Unit,
        customFailureCallback: ((Throwable) -> Unit)? = null
    ) {
//        if (!isNetworkConnectedUseCase()) {
//            showErrorView()
//            return
//        }
//        hideErrorView()
        showEmptyView(false)
        showLoader(true)
        viewModelScope.launch {
            query().apply {
                onSuccess {
                    showLoader(false)
                    onSuccess(it)
//                    hideErrorView()
                }
                onFailure {
                    //TODO: handle failure
                    showLoader(false)
//                    if(customFailureCallback == null) {
//                        if (it is UserNotLoggedInException)
//                            showEmptyView()
//                        else
//                            showErrorView()
//                    } else {
//                        customFailureCallback(it)
//                    }
                }
            }
        }
    }

    fun showEmptyView(show: Boolean) {
        _showEmptyView.value = show
    }
    fun showLoader(show: Boolean) {
        _showLoader.value = show
    }
}