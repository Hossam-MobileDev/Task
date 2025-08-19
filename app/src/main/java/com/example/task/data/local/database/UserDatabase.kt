package com.example.task.data.local.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.task.data.local.dao.UserDao
import com.example.task.data.local.entities.UserEntity

@Database(
    entities = [UserEntity::class],
    version = 1,
    exportSchema = false
)
abstract class UserDatabase : RoomDatabase() {
    abstract val userDao: UserDao

    companion object {
        const val DATABASE_NAME = "user_db"
    }
}