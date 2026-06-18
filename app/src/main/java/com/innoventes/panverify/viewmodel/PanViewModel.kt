package com.innoventes.panverify.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.innoventes.panverify.model.UiState
import com.innoventes.panverify.utils.Validator
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class PanViewModel : ViewModel() {
    private val _uiState = MutableLiveData(UiState())

    val uiState: LiveData<UiState> = _uiState

    fun updatePan(value: String) {

        val current = _uiState.value ?: UiState()

        _uiState.value = current.copy(pan = value.uppercase())

        validate()
    }

    fun updateDay(value: String) {

        val current = _uiState.value ?: UiState()

        _uiState.value = current.copy(day = value)

        validate()
    }

    fun updateMonth(value: String) {

        val current = _uiState.value ?: UiState()

        _uiState.value = current.copy(month = value)

        validate()
    }

    fun updateYear(value: String) {

        val current = _uiState.value ?: UiState()

        _uiState.value = current.copy(year = value)

        validate()
    }

    private fun validate() {

        viewModelScope.launch(Dispatchers.Default) {

            val state = _uiState.value ?: return@launch

            val panValid = Validator.isValidPAN(state.pan)

            val dateValid = Validator.isValidDate(
                    state.day,
                    state.month,
                    state.year
                )

            _uiState.postValue(state.copy(isFormValid = panValid && dateValid))
        }
    }
}
