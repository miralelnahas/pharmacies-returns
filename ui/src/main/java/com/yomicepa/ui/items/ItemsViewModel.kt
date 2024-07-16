package com.yomicepa.ui.items

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.viewModelScope
import com.yomicepa.domain.models.Item
import com.yomicepa.domain.usecases.DeleteItemUseCase
import com.yomicepa.domain.usecases.EditItemUseCase
import com.yomicepa.domain.usecases.GetItemsUseCase
import com.yomicepa.ui.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
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
        viewModelScope.launch {
            getItemsUseCase(requestId).apply {
                //TODO: handle empty views
                onSuccess {
                    _items.value = it
                }
            }
        }
    }
    fun deleteItem(id: Int) {
        viewModelScope.launch {
            deleteItemUseCase(requestId, id).apply {
                onSuccess {
                    //todo: on deleting last item, code 204 returns in onFailure
                    //TODO: handle errors
                    getItems()
                }
            }
        }
    }

    fun editItem(item: Item, newDescription:String) {
        viewModelScope.launch {
            editItemUseCase(requestId, item, newDescription).apply {
                onSuccess {
                    getItems()
                }
                //TODO: handle on failure
            }
        }
    }

    companion object {
        private const val ARG_ID = "id"
    }
}