package com.example.task.data.repository

import com.example.task.data.local.dao.UserDao
import com.example.task.data.mapper.toDomain
import com.example.task.data.mapper.toEntity
import com.example.task.domain.model.User
import com.example.task.domain.repository.UserRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class UserRepositoryImpl @Inject constructor(
    private val dao: UserDao
) : UserRepository {

    override fun getAllUsers(): Flow<List<User>> {
        return dao.getAllUsers().map { it.toDomain() }
    }

    override suspend fun insertUser(user: User) {
        dao.insertUser(user.toEntity())
    }

    override suspend fun deleteUser(user: User) {
        dao.deleteUser(user.toEntity())
    }

    override suspend fun deleteAllUsers() {
        dao.deleteAllUsers()
    }
}