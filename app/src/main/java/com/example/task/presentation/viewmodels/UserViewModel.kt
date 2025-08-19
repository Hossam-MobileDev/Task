package com.example.task.presentation.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.task.domain.model.User
import com.example.task.domain.useCase.UserUseCases
import com.example.task.ui.model.UserUiState

import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class UserViewModel @Inject constructor(
    private val userUseCases: UserUseCases
) : ViewModel() {

    val allUsers = userUseCases.getAllUsers()

    private val _uiState = MutableStateFlow(UserUiState())
    val uiState: StateFlow<UserUiState> = _uiState.asStateFlow()

    fun updateName(name: String) {
        _uiState.value = _uiState.value.copy(name = name)
    }

    fun updateAge(age: String) {
        _uiState.value = _uiState.value.copy(age = age)
    }

    fun updateJobTitle(jobTitle: String) {
        _uiState.value = _uiState.value.copy(jobTitle = jobTitle)
    }

    fun updateGender(gender: String) {
        _uiState.value = _uiState.value.copy(gender = gender)
    }

    fun insertUser() {
        val state = _uiState.value

        if (state.name.isBlank() || state.age.isBlank() ||
            state.jobTitle.isBlank() || state.gender.isBlank()) {
            _uiState.value = state.copy(message = "Please fill all fields")
            return
        }

        val ageInt = state.age.toIntOrNull()
        if (ageInt == null || ageInt <= 0) {
            _uiState.value = state.copy(message = "Please enter a valid age")
            return
        }

        _uiState.value = state.copy(isLoading = true)

        viewModelScope.launch {
            try {
                val user = User(
                    name = state.name.trim(),
                    age = ageInt,
                    jobTitle = state.jobTitle.trim(),
                    gender = state.gender
                )
                userUseCases.insertUser(user)
                clearForm()
                _uiState.value = _uiState.value.copy(
                    message = "User saved successfully!",
                    isLoading = false
                )
            } catch (e: Exception) {
                _uiState.value = _uiState.value.copy(
                    message = "Error: ${e.message}",
                    isLoading = false
                )
            }
        }
    }

    fun deleteUser(user: User) {
        viewModelScope.launch {
            userUseCases.deleteUser(user)
        }
    }

    fun clearMessage() {
        _uiState.value = _uiState.value.copy(message = null)
    }

    private fun clearForm() {
        _uiState.value = _uiState.value.copy(
            name = "",
            age = "",
            jobTitle = "",
            gender = ""
        )
    }
}