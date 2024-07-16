package com.yomicepa.ui.createRequest

sealed class CreateRequestEvent {
    object HandleServiceTypeError : CreateRequestEvent()
    class CreateRequestSuccess(val requestId: Int) : CreateRequestEvent()
}