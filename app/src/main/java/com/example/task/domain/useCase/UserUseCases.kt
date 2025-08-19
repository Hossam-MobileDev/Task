package com.example.task.domain.useCase

data class UserUseCases(
    val getAllUsers: GetAllUsersUseCase,
    val insertUser: InsertUserUseCase,
    val deleteUser: DeleteUserUseCase
)