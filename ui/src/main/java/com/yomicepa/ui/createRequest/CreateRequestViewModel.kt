package com.yomicepa.ui.createRequest

import com.yomicepa.domain.models.ServiceType
import com.yomicepa.domain.usecases.CreateRequestUseCase
import com.yomicepa.domain.usecases.IsNetworkConnectedUseCase
import com.yomicepa.ui.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import javax.inject.Inject

@HiltViewModel
class CreateRequestViewModel @Inject constructor(
    private val createRequestUseCase: CreateRequestUseCase,
    isNetworkConnectedUseCase: IsNetworkConnectedUseCase
) : BaseViewModel(isNetworkConnectedUseCase) {
    private val _event = Channel<CreateRequestEvent>(Channel.BUFFERED)
    val event = _event.receiveAsFlow()


    private val _serviceType = MutableStateFlow(ServiceType.NONE)
    val serviceType: StateFlow<ServiceType> = _serviceType.asStateFlow()
    fun setServiceType(serviceType: String) {
        _serviceType.value = ServiceType.fromTitle(serviceType)
    }

    fun onCreateClick() {
        if (_serviceType.value == ServiceType.NONE)
            sendEvent(_event, CreateRequestEvent.HandleServiceTypeError)
        else
            launchRequest({
                //TODO: handle wholesale
                createRequestUseCase(_serviceType.value, 4)
            }, {
                sendEvent(_event, CreateRequestEvent.CreateRequestSuccess(it))
            })
    }
}