package com.yomicepa.ui.addItem

sealed class AddItemEvent {
    object ItemAddedSuccessfully : AddItemEvent()
    class ItemsClicked(val requestId: Int) : AddItemEvent()
}