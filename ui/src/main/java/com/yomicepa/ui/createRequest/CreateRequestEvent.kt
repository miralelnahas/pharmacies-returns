package com.yomicepa.ui.createRequest

sealed class CreateRequestEvent {
    class CreateRequestSuccess(val requestId: Int) : CreateRequestEvent()
}