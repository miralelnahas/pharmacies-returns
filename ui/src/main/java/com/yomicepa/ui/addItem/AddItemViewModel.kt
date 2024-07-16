package com.yomicepa.ui.addItem

import androidx.lifecycle.SavedStateHandle
import com.yomicepa.domain.usecases.AddItemUseCase
import com.yomicepa.domain.usecases.IsNetworkConnectedUseCase
import com.yomicepa.ui.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import javax.inject.Inject

@HiltViewModel
class AddItemViewModel @Inject constructor(
    private val savedStateHandle: SavedStateHandle,
    private val addItemUseCase: AddItemUseCase,
    isNetworkConnectedUseCase : IsNetworkConnectedUseCase
) : BaseViewModel(isNetworkConnectedUseCase) {
    private val requestId = savedStateHandle[ARG_ID] ?: 0

    private val _event = Channel<AddItemEvent>(Channel.BUFFERED)
    val event = _event.receiveAsFlow()

    private val _ndc = MutableStateFlow("")
    fun getNdc() = _ndc
    fun setNdc(ndc: String) {
        _ndc.value = ndc
    }

    private val _description = MutableStateFlow("")
    fun getDescription() = _description
    fun setDescription(description: String) {
        _description.value = description
    }

    private val _manufacturer = MutableStateFlow("")
    fun getManufacturer() = _manufacturer
    fun setManufacturer(manufacturer: String) {
        _manufacturer.value = manufacturer
    }

    private val _fullQuantity = MutableStateFlow("")
    fun getFullQuantity() = _fullQuantity
    fun setFullQuantity(fullQuantity: String) {
        _fullQuantity.value = fullQuantity
    }

    private val _partialQuantity = MutableStateFlow("")
    fun getPartialQuantity() = _partialQuantity
    fun setPartialQuantity(partialQuantity: String) {
        _partialQuantity.value = partialQuantity
    }

    private val _expirationDate = MutableStateFlow("")
    fun getExpirationDate() = _expirationDate
    fun setExpirationDate(expirationDate: String) {
        _expirationDate.value = expirationDate
    }

    private val _lotNumber = MutableStateFlow("")
    fun getLotNumber() = _lotNumber
    fun setLotNumber(lotNumber: String) {
        _lotNumber.value = lotNumber
    }


    fun onAddItemClicked() {
        launchRequest({
            addItemUseCase(
                requestId,
                _ndc.value,
                _description.value,
                _manufacturer.value,
                _fullQuantity.value,
                _partialQuantity.value,
                _expirationDate.value,
                _lotNumber.value
            )
        }, {
            clearFields()
            sendEvent(_event, AddItemEvent.ItemAddedSuccessfully)
            //TODO: handle errors and empty fields
        })
    }

    private fun clearFields() {
        //TODO: clear errors
        _description.value = ""
        _manufacturer.value = ""
        _fullQuantity.value = ""
        _partialQuantity.value = ""
        _expirationDate.value = ""
        _lotNumber.value = ""
    }

    fun onItemsClicked() {
        sendEvent(_event, AddItemEvent.ItemsClicked(requestId))
    }

    companion object {
        private const val ARG_ID = "id"
    }
}