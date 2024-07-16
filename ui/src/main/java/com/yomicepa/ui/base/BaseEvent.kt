package com.yomicepa.ui.base

sealed class BaseEvent {
    object Back : BaseEvent()
    object GeneralError : BaseEvent()
}