package com.yomicepa.ui.login

sealed class LoginEvent {
    data object LoginSuccess : LoginEvent()
    data object LoginFailed : LoginEvent()
    data object HandleErrors : LoginEvent()

    data object ClearErrors : LoginEvent()
}