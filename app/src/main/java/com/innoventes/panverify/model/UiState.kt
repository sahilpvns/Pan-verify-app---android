package com.innoventes.panverify.model

data class UiState(
    val pan: String = "",
    val day: String = "",
    val month: String = "",
    val year: String = "",
    val isFormValid: Boolean = false
)
