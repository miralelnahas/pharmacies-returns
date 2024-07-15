package com.yomicepa.ui.login

sealed class LoginEvent {
    data object LoginSuccess : LoginEvent()
}