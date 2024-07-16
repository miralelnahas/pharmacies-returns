package com.yomicepa.ui.returnRequests

sealed class ReturnRequestsEvent {
    object CreateClick : ReturnRequestsEvent()
}