package by.zm.quizlet.core.data

import by.zm.quizlet.core.cache.CacheDataStore
import by.zm.quizlet.core.domain.ModulesRepository
import by.zm.quizlet.core.domain.model.Module
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class ModulesRepositoryImpl @Inject constructor(
    private val cacheDataStore: CacheDataStore
) : ModulesRepository {

    override val modules: Flow<List<Module>>
        get() = TODO("Not yet implemented")

    override suspend fun insertModule(module: Module) {
        TODO("Not yet implemented")
    }

    override suspend fun updateModule(module: Module) {
        TODO("Not yet implemented")
    }

    override suspend fun deleteModule(module: Module) {
        TODO("Not yet implemented")
    }
}