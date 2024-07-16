package com.yomicepa.ui.createRequest

import com.yomicepa.domain.models.ServiceType
import com.yomicepa.domain.usecases.CreateRequestUseCase
import com.yomicepa.ui.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import javax.inject.Inject

@HiltViewModel
class CreateRequestViewModel @Inject constructor(private val createRequestUseCase: CreateRequestUseCase) :
    BaseViewModel() {
    private val _event = Channel<CreateRequestEvent>(Channel.BUFFERED)
    val event = _event.receiveAsFlow()


    private val _serviceType = MutableStateFlow(ServiceType.EXPRESS_SERVICE)
    fun setServiceType(serviceType: String) {
        _serviceType.value = ServiceType.fromTitle(serviceType)
    }

    fun onCreateClick() {
        launchRequest({
            //TODO: handle wholesale
            createRequestUseCase(_serviceType.value, 4)
        }, {
            sendEvent(_event, CreateRequestEvent.CreateRequestSuccess(it))
            //TODO: Handle failure and empty service type field
        })
    }
}