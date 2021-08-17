package com.astriex.justdoit.di

import android.app.Application
import android.content.Context
import androidx.room.Room
import com.astriex.justdoit.data.TaskDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob
import javax.inject.Qualifier
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object AppModule {

    @Singleton
    @Provides
    fun provideTaskDatabase(
        @ApplicationContext app: Context,
        callback: TaskDatabase.Callback
    ): TaskDatabase = Room.databaseBuilder(app, TaskDatabase::class.java, "task_database")
        .fallbackToDestructiveMigration()
        .addCallback(callback)
        .build()

    @Singleton
    @Provides
    fun provideTaskDao(db: TaskDatabase) = db.taskDao()

    @ApplicationScope
    @Provides
    @Singleton
    // supervisor job added to prevent cancellation of all children if one task fails
    fun provideApplicationScope() = CoroutineScope(SupervisorJob())

}

@Qualifier
@Retention(AnnotationRetention.RUNTIME)
annotation class ApplicationScope