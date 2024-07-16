package com.yomicepa.ui.addItem

sealed class AddItemEvent {
    object HandleEmptyErrors : AddItemEvent()
    object ClearErrors : AddItemEvent()
    object ItemAddedSuccessfully : AddItemEvent()
    class ItemsClicked(val requestId: Int) : AddItemEvent()
}