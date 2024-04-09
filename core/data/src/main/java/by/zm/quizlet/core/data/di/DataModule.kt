package by.zm.quizlet.core.data.di

import by.zm.quizlet.core.data.ModulesRepositoryImpl
import by.zm.quizlet.core.domain.ModulesRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
interface DataModule {

    @Binds
    fun bindsModulesRepository(
        modulesRepository: ModulesRepositoryImpl
    ): ModulesRepository
}