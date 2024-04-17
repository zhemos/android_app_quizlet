package by.zm.quizlet.core.data.tests

import by.zm.quizlet.core.cache.room.dao.ModuleWithTermsDao
import by.zm.quizlet.core.cache.room.model.ModuleWithTerms
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class TestModuleWithTermsDao : ModuleWithTermsDao {

    override fun observeAllModulesWithTerms(): Flow<List<ModuleWithTerms>> {
        return TestDatabase.modulesStateFlow.map { modules ->
            modules.map { module ->
                val terms = TestDatabase.termsStateFlow.value.filter { term ->
                    term.moduleId == module.id
                }
                ModuleWithTerms(module = module, terms = terms)
            }
        }
    }
}