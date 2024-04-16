package by.zm.quizlet.core.data

import by.zm.quizlet.core.cache.CacheDataStore
import by.zm.quizlet.core.data.mapper.ModuleMapper
import by.zm.quizlet.core.domain.ModulesRepository
import by.zm.quizlet.core.domain.model.Module
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class ModulesRepositoryImpl @Inject constructor(
    private val cacheDataStore: CacheDataStore,
    private val moduleMapper: ModuleMapper,
) : ModulesRepository {

    override val modules: Flow<List<Module>>
        get() = cacheDataStore.observeAllModulesWithTerms()
        .map {
            moduleMapper.mapToDomain(it)
        }

    override suspend fun insertModule(module: Module) {

    }

    override suspend fun updateModule(module: Module) {

    }

    override suspend fun deleteModule(module: Module) {

    }
}