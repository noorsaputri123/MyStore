package com.rie.mystore.ui.screen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.rie.mystore.data.VivoRepository
import com.rie.mystore.model.OrderVivo
import com.rie.mystore.ui.common.UiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class DetailVmodel (
    private val repository : VivoRepository
) : ViewModel(){
    private val _uiState: MutableStateFlow<UiState<OrderVivo>> =
        MutableStateFlow(UiState.Loading)
    val uiState : StateFlow<UiState<OrderVivo>>
    get() = _uiState

    fun getVivoById(vivoId: Long){
        viewModelScope.launch {
            _uiState.value = UiState.Loading
            _uiState.value = UiState.Success(repository.getVivoById(vivoId))
        }
    }
}