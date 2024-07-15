package com.yomicepa.ui.base

import androidx.annotation.PluralsRes
import androidx.annotation.StringRes

sealed class BaseEvent {
    object Back : BaseEvent()
}