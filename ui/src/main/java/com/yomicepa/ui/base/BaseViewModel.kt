package com.yomicepa.ui.base

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

abstract class BaseViewModel : ViewModel() {

    private val _baseEvent = Channel<BaseEvent>(Channel.BUFFERED)
    val baseEvent = _baseEvent.receiveAsFlow()


    fun <T> Flow<T>.toStateFlow(initValue: T): StateFlow<T> =
        this.stateIn(viewModelScope, SharingStarted.WhileSubscribed(), initValue)

    open fun onBackClick() = sendEvent(_baseEvent, BaseEvent.Back)

    fun <T> sendEvent(channel: Channel<T>, event: T) {
        viewModelScope.launch {
            channel.send(event)
        }
    }
}