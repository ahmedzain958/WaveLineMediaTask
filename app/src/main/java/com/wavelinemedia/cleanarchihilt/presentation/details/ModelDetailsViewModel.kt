package com.wavelinemedia.cleanarchihilt.presentation.details

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.wavelinemedia.cleanarchihilt.domain.Model
import com.wavelinemedia.cleanarchihilt.domain.usecases.GetModelsByIdUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class ModelDetailsViewModel @Inject constructor(
    private val savedStateHandle: SavedStateHandle,
    private val getModelsByIdUseCase: GetModelsByIdUseCase,
) : ViewModel() {
    val state = mutableStateOf<Model?>(null)

    init {
        val modelId = savedStateHandle.get<Int>("model_id") ?: 0
        getModel(modelId)
    }

    private fun getModel(id: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            val model: Model = getModelFromRemoteDB(id)
            withContext(Dispatchers.Main) {
                state.value = model
            }
        }
    }

    private suspend fun getModelFromRemoteDB(id: Int) = withContext(Dispatchers.IO) {
        getModelsByIdUseCase(id).values.first().let {
            Model(
                it.id, it.name, it.place, it.isOpen
            )
        }
    }

}