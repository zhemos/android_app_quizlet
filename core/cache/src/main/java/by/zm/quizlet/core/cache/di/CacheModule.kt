package by.zm.quizlet.core.cache.di

import android.content.Context
import androidx.room.Room
import by.zm.quizlet.core.cache.CacheDataStore
import by.zm.quizlet.core.cache.CacheDataStoreImpl
import by.zm.quizlet.core.cache.room.AppDatabase
import by.zm.quizlet.core.common.Constants
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object CacheModule {

    @Provides
    @Singleton
    fun providesAppDatabase(
        @ApplicationContext context: Context,
    ): AppDatabase = Room.databaseBuilder(
        context = context,
        klass = AppDatabase::class.java,
        name = Constants.DATABASE_NAME,
    ).build()

    @Provides
    @Singleton
    fun providesCacheDataStore(
        appDatabase: AppDatabase,
    ): CacheDataStore = CacheDataStoreImpl(
        moduleDao = appDatabase.moduleDao(),
        termDao = appDatabase.termDao(),
        moduleWithTerms = appDatabase.moduleWithTerms(),
    )
}