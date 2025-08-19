package com.example.task.ui.model

data class UserUiState(
    val name: String = "",
    val age: String = "",
    val jobTitle: String = "",
    val gender: String = "",
    val message: String? = null,
    val isLoading: Boolean = false
)