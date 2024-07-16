package com.yomicepa.ui.createRequest

sealed class CreateRequestEvent {
    class CreateRequestSuccess(requestId: Int) : CreateRequestEvent()
}