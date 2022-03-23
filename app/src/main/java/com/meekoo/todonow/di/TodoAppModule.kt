package com.meekoo.todonow.di

import android.app.Application
import androidx.room.Room
import com.meekoo.todonow.data.db.TodoDatabase
import com.meekoo.todonow.data.repositories.TodoRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

/**
 * Created by Ajay Verma on 23/03/22.
 */
@Module
@InstallIn(SingletonComponent::class)
class TodoAppModule {

    @Provides
    @Singleton
    fun provideTodoDatabase(application: Application) =
        Room.databaseBuilder(application, TodoDatabase::class.java, TodoDatabase.DATABASE_NAME)
            .build()

    @Provides
    @Singleton
    fun provideTodoRepository(database: TodoDatabase) = TodoRepositoryImpl(todoDao = database.todoDao)
}
