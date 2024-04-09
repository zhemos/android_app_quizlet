package by.zm.quizlet.core.cache.di

import android.content.Context
import androidx.room.Room
import by.zm.quizlet.core.cache.CacheDataStore
import by.zm.quizlet.core.cache.CacheDataStoreImpl
import by.zm.quizlet.core.cache.room.AppDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Named
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object TestCacheModule {

    @Provides
    @Singleton
    @Named("test_db")
    fun providesAppDatabase(
        @ApplicationContext context: Context,
    ): AppDatabase = Room.inMemoryDatabaseBuilder(
        context = context,
        klass = AppDatabase::class.java,
    ).allowMainThreadQueries().build()

    @Provides
    @Singleton
    @Named("test")
    fun providesCacheDataStore(
        @Named("test_db") appDatabase: AppDatabase,
    ): CacheDataStore = CacheDataStoreImpl(
        moduleDao = appDatabase.moduleDao(),
        termDao = appDatabase.termDao(),
        moduleWithTerms = appDatabase.moduleWithTerms(),
    )
}