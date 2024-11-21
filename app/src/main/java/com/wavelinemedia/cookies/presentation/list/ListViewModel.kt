package com.wavelinemedia.cookies.presentation.list

import androidx.compose.runtime.State
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.wavelinemedia.cookies.domain.usecases.GetInitialModelsUseCase
import com.wavelinemedia.cookies.domain.usecases.ToggleFavouriteStateUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ListViewModel @Inject constructor(
    private val getInitialListUseCase: GetInitialModelsUseCase,
    private val toggleFavouriteStateUseCase: ToggleFavouriteStateUseCase,
    private val dispatcher: CoroutineDispatcher,
) : ViewModel() {
    private var _state by mutableStateOf(ModelScreenState(emptyList(), true))
    val state: State<ModelScreenState>
        get() = derivedStateOf { _state }


    private var coroutineExceptionHandler = CoroutineExceptionHandler { _, throwable ->
        throwable.printStackTrace()
        _state = _state.copy(error = throwable.message, isLoading = false)
    }

    init {
        getGyms()
    }

    private fun getGyms() {
        viewModelScope.launch(coroutineExceptionHandler + dispatcher) {
            val receivedGyms = getInitialListUseCase()
            _state = _state.copy(modelsList = receivedGyms, isLoading = false)
        }
    }


    fun toggleFavState(id: Int, oldValue: Boolean) {
        viewModelScope.launch(coroutineExceptionHandler + dispatcher) {
            val updatedGymsList = toggleFavouriteStateUseCase(id, oldValue)
            _state = _state.copy(modelsList = updatedGymsList)
        }
    }
}