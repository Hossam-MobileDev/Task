package com.example.task.data.mapper

import com.example.task.data.local.entities.UserEntity
import com.example.task.domain.model.User

fun UserEntity.toDomain(): User {
    return User(
        id = id,
        name = name,
        age = age,
        jobTitle = jobTitle,
        gender = gender
    )
}

fun User.toEntity(): UserEntity {
    return UserEntity(
        id = id,
        name = name,
        age = age,
        jobTitle = jobTitle,
        gender = gender
    )
}

fun List<UserEntity>.toDomain(): List<User> {
    return map { it.toDomain() }
}