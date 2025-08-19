package com.example.task.di

import android.app.Application
import androidx.room.Room
import com.example.task.data.local.database.UserDatabase
import com.example.task.data.repository.UserRepositoryImpl
import com.example.task.domain.repository.UserRepository
import com.example.task.domain.useCase.DeleteUserUseCase
import com.example.task.domain.useCase.GetAllUsersUseCase
import com.example.task.domain.useCase.InsertUserUseCase
import com.example.task.domain.useCase.UserUseCases
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Provides
    @Singleton
    fun provideUserDatabase(app: Application): UserDatabase {
        return Room.databaseBuilder(
            app,
            UserDatabase::class.java,
            UserDatabase.DATABASE_NAME
        ).build()
    }

    @Provides
    @Singleton
    fun provideUserRepository(db: UserDatabase): UserRepository {
        return UserRepositoryImpl(db.userDao)
    }

    @Provides
    @Singleton
    fun provideUserUseCases(repository: UserRepository): UserUseCases {
        return UserUseCases(
            getAllUsers = GetAllUsersUseCase(repository),
            insertUser = InsertUserUseCase(repository),
            deleteUser = DeleteUserUseCase(repository)
        )
    }
}