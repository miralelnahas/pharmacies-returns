package com.yomicepa.ui.login

import androidx.lifecycle.viewModelScope
import com.yomicepa.domain.usecases.IsUserLoggedInUseCase
import com.yomicepa.domain.usecases.LoginUseCase
import com.yomicepa.ui.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val loginUseCase: LoginUseCase,
    private val isUserLoggedInUseCase: IsUserLoggedInUseCase
) : BaseViewModel() {
    private val _event = Channel<LoginEvent>(Channel.BUFFERED)
    val event = _event.receiveAsFlow()

    val isUserLoggedIn = isUserLoggedInUseCase()
    private val _username = MutableStateFlow("")
    fun getUsername() = _username.value
    fun setUsername(username: String) {
        _username.value = username
    }

    private val _password = MutableStateFlow("")
    fun getPassword() = _password.value
    fun setPassword(password: String) {
        _password.value = password
    }

    fun onLoginClick() {
        viewModelScope.launch {
            loginUseCase(_username.value, _password.value).apply {
                onSuccess {
                    if (it)
                        sendEvent(_event, LoginEvent.LoginSuccess)
                    else {
                    }
                    //TODO: implement errors
                }
            }
        }
    }
}