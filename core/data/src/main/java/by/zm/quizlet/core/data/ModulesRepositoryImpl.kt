package by.zm.quizlet.core.data

import by.zm.quizlet.core.cache.CacheDataStore
import by.zm.quizlet.core.data.mapper.ModuleMapper
import by.zm.quizlet.core.data.mapper.ModuleWithTermsMapper
import by.zm.quizlet.core.data.mapper.TermMapper
import by.zm.quizlet.core.domain.ModulesRepository
import by.zm.quizlet.core.model.Module
import by.zm.quizlet.core.model.Term
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class ModulesRepositoryImpl @Inject constructor(
    private val cacheDataStore: CacheDataStore,
    private val moduleMapper: ModuleMapper,
    private val moduleWithTermsMapper: ModuleWithTermsMapper,
) : ModulesRepository {

    override val modules: Flow<List<Module>>
        get() = cacheDataStore.observeAllModulesWithTerms()
            .map {
                moduleWithTermsMapper.mapToDomain(it)
            }

    override fun observeAllTermsByModuleId(id: Int): Flow<List<Term>> {
        val termMapper = TermMapper(id)
        return cacheDataStore.observeAllTermsByModuleId(id).map {
            termMapper.mapToDomain(it)
        }
    }

    override suspend fun insertModule(module: Module) {
        val moduleDto = moduleMapper.mapToDto(module)
        cacheDataStore.insertModule(moduleDto)
        val termsMapper = TermMapper(moduleId = module.id)
        val termsDto = termsMapper.mapToDto(module.terms)
        cacheDataStore.insertTerms(termsDto)
    }

    override suspend fun updateModule(module: Module) {
        val termMapper = TermMapper(moduleId = module.id)
        val deleteTerms = observeAllTermsByModuleId(id = module.id).first().filterNot {
            module.terms.contains(it)
        }
        deleteTerms.forEach {
            val term = termMapper.mapToDto(it)
            cacheDataStore.deleteTerm(term)
        }
        insertModule(module)
    }

    override suspend fun deleteModule(module: Module) {
        val moduleDto = moduleMapper.mapToDto(module)
        cacheDataStore.deleteModule(moduleDto)
        val termsMapper = TermMapper(moduleId = module.id)
        module.terms.forEach {
            val term = termsMapper.mapToDto(it)
            cacheDataStore.deleteTerm(term)
        }
    }
}