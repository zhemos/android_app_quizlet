package by.zm.quizlet.core.data.tests

import by.zm.quizlet.core.cache.room.dao.ModuleWithTermsDao
import by.zm.quizlet.core.cache.room.model.ModuleWithTerms
import kotlinx.coroutines.flow.Flow

class TestModuleWithTermsDao : TestDatabase(), ModuleWithTermsDao {

    override fun observeAllModulesWithTerms(): Flow<List<ModuleWithTerms>> {
        TODO("Not yet implemented")
    }
}