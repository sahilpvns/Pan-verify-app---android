package com.innoventes.panverify.viewmodel

data class PanUiState(
    val panNumber: String = "",
    val day: String = "",
    val month: String = "",
    val year: String = "",
    val isNextEnabled: Boolean = false
)
