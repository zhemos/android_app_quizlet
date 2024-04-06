package by.zm.quizlet.core.cache.di

import android.content.Context
import androidx.room.Room
import by.zm.quizlet.core.cache.room.AppDatabase
import by.zm.quizlet.core.common.Constants
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Named
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object TestAppModule {

    @Provides
    @Singleton
    @Named("test_db")
    fun providesAppDatabase(
        @ApplicationContext context: Context,
    ): AppDatabase = Room.inMemoryDatabaseBuilder(
        context = context,
        klass = AppDatabase::class.java,
    ).allowMainThreadQueries().build()
}