package com.yomicepa.ui.items

import androidx.lifecycle.SavedStateHandle
import com.yomicepa.domain.models.Item
import com.yomicepa.domain.usecases.DeleteItemUseCase
import com.yomicepa.domain.usecases.EditItemUseCase
import com.yomicepa.domain.usecases.GetItemsUseCase
import com.yomicepa.ui.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

@HiltViewModel
class ItemsViewModel @Inject constructor(
    private val savedStateHandle: SavedStateHandle,
    private val getItemsUseCase: GetItemsUseCase,
    private val deleteItemUseCase: DeleteItemUseCase,
    private val editItemUseCase: EditItemUseCase
) :
    BaseViewModel() {

    private val requestId = savedStateHandle[ARG_ID] ?: 0

    private val _items = MutableStateFlow(listOf<Item>())
    val items = _items.asStateFlow()

    init {
        getItems()
    }

    private fun getItems() {
        launchRequest({
            getItemsUseCase(requestId)
        }, {
            //TODO: handle empty views
            _items.value = it
        })
    }

    fun deleteItem(id: Int) {
        launchRequest({
            deleteItemUseCase(requestId, id)
        }, {
            //todo: on deleting last item, code 204 returns in onFailure
            //TODO: handle errors
            getItems()
        })
    }

    fun editItem(item: Item, newDescription: String) {
        launchRequest({
            editItemUseCase(requestId, item, newDescription)
        }, {
            getItems()
        }
            //TODO: handle on failure
        )
    }

    companion object {
        private const val ARG_ID = "id"
    }
}