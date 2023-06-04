package com.rie.mystore.ui.screen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.rie.mystore.data.VivoRepository
import com.rie.mystore.model.OrderVivo
import com.rie.mystore.ui.common.UiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class HomeVmodel (
    private val repository : VivoRepository
) : ViewModel() {
    private val _uiState : MutableStateFlow<UiState<List<OrderVivo>>> = MutableStateFlow(UiState.Loading)
    val  uiState : StateFlow<UiState<List<OrderVivo>>>
    get() = _uiState
    fun  getAllVivos(){
        viewModelScope.launch {
            repository.getAllVivos()
                .catch {
                    _uiState.value = UiState.Error(it.message.toString())
                }
                .collect{ orderVivos ->
                    _uiState.value = UiState.Success(orderVivos)
                }
        }
    }
}