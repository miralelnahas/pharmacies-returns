package com.yomicepa.ui.base

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.yomicepa.common.exceptions.Exceptions
import com.yomicepa.domain.usecases.IsNetworkConnectedUseCase
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

abstract class BaseViewModel(private val isNetworkConnectedUseCase: IsNetworkConnectedUseCase) :
    ViewModel() {

    private val _baseEvent = Channel<BaseEvent>(Channel.BUFFERED)
    val baseEvent = _baseEvent.receiveAsFlow()

    private val _showLoader = MutableStateFlow(false)
    val showLoader = _showLoader.asStateFlow()

    private val _showEmptyView = MutableStateFlow(false)
    val showEmptyView = _showEmptyView.asStateFlow()

    private val _showErrorView = MutableStateFlow(false)
    val showErrorView = _showErrorView.asStateFlow()

    private val failedRequests = mutableListOf<() -> Unit>()

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
        if (!isNetworkConnectedUseCase()) {
            showErrorView(true)
            enqueueRequest { launchRequest(query, onSuccess, customFailureCallback) }
            return
        }
        showErrorView(false)
        showEmptyView(false)
        showLoader(true)
        viewModelScope.launch {
            query().apply {
                onSuccess {
                    showLoader(false)
                    onSuccess(it)
                }
                onFailure {
                    //TODO: handle failure
                    showLoader(false)
                    if (it is Exceptions.EmptyResponse)
                        showEmptyView(true)
                    else
                        sendEvent(_baseEvent, BaseEvent.GeneralError)
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

    fun showErrorView(show: Boolean) {
        _showErrorView.value = show
    }

    protected fun enqueueRequest(request: () -> Unit) {
        failedRequests.add(request)
    }

    private fun processFailedRequests() {
        failedRequests.forEach { request ->
            try {
                request()
                failedRequests.remove(request)
            } catch (e: Exception) {
                // Leave the request in the queue if it fails again
            }
        }
    }

    fun onRetryClick() {
        processFailedRequests()
    }
}