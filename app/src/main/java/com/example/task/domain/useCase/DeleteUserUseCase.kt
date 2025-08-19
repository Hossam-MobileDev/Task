package com.example.task.domain.useCase

import com.example.task.domain.model.User
import com.example.task.domain.repository.UserRepository
import javax.inject.Inject

class DeleteUserUseCase @Inject constructor(
    private val repository: UserRepository
) {
    suspend operator fun invoke(user: User) = repository.deleteUser(user)
}