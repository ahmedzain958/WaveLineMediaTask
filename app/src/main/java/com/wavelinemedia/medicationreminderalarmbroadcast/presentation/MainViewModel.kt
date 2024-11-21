package com.wavelinemedia.medicationreminderalarmbroadcast.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.wavelinemedia.medicationreminderalarmbroadcast.domain.model.Reminder
import com.wavelinemedia.medicationreminderalarmbroadcast.domain.use_cases.DeleteUseCase
import com.wavelinemedia.medicationreminderalarmbroadcast.domain.use_cases.GetAllReminderUseCase
import com.wavelinemedia.medicationreminderalarmbroadcast.domain.use_cases.InsertUseCase
import com.wavelinemedia.medicationreminderalarmbroadcast.domain.use_cases.UpdateUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val insertUseCase: InsertUseCase,
    private val deleteUseCase: DeleteUseCase,
    private val updateUseCase: UpdateUseCase,
    private val getAllReminderUseCase: GetAllReminderUseCase,
) : ViewModel() {

    val uiState = getAllReminderUseCase().map {
        UiState(it)
    }.stateIn(
        viewModelScope,
        kotlinx.coroutines.flow.SharingStarted.Eagerly,
        UiState()/*initial value*/
    )

    fun insert(reminder: Reminder) = viewModelScope.launch {
        insertUseCase.invoke(reminder)
    }

    fun update(reminder: Reminder) = viewModelScope.launch {
        updateUseCase.invoke(reminder)
    }

    fun delete(reminder: Reminder) = viewModelScope.launch {
        deleteUseCase.invoke(reminder)
    }
}

data class UiState(
    val data: List<Reminder> = emptyList(),
)
