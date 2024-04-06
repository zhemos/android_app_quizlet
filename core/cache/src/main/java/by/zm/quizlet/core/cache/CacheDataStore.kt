package by.zm.quizlet.core.cache

import by.zm.quizlet.core.cache.room.dao.ModuleDao
import by.zm.quizlet.core.cache.room.dao.ModuleWithTermsDao
import by.zm.quizlet.core.cache.room.dao.TermDao
import by.zm.quizlet.core.cache.room.model.ModuleItem
import by.zm.quizlet.core.cache.room.model.ModuleWithTerms
import by.zm.quizlet.core.cache.room.model.TermItem
import kotlinx.coroutines.flow.Flow

interface CacheDataStore {

    suspend fun insertModule(module: ModuleItem)

    suspend fun deleteModule(moduleItem: ModuleItem)

    fun observeAllModules(): Flow<List<ModuleItem>>

    suspend fun insertTerm(term: TermItem)

    suspend fun insertTerms(terms: List<TermItem>)

    suspend fun deleteTerm(term: TermItem)

    fun observeAllTermsByModuleId(id: Int): Flow<List<TermItem>>

    fun observeAllModulesWithTerms(): Flow<List<ModuleWithTerms>>
}

class CacheDataStoreImpl(
    private val moduleDao: ModuleDao,
    private val termDao: TermDao,
    private val moduleWithTerms: ModuleWithTermsDao,
) : CacheDataStore {

    override suspend fun insertModule(module: ModuleItem) {
        moduleDao.insertModule(module)
    }

    override suspend fun deleteModule(moduleItem: ModuleItem) {
        moduleDao.deleteModule(moduleItem)
    }

    override fun observeAllModules(): Flow<List<ModuleItem>> {
        return moduleDao.observeAllModules()
    }

    override suspend fun insertTerm(term: TermItem) {
        termDao.insertTerm(term)
    }

    override suspend fun insertTerms(terms: List<TermItem>) {
        termDao.insertTerms(terms)
    }

    override suspend fun deleteTerm(term: TermItem) {
        termDao.deleteTerm(term)
    }

    override fun observeAllTermsByModuleId(id: Int): Flow<List<TermItem>> {
        return termDao.observeAllTermsByModuleId(id)
    }

    override fun observeAllModulesWithTerms(): Flow<List<ModuleWithTerms>> {
        return moduleWithTerms.observeAllModulesWithTerms()
    }
}