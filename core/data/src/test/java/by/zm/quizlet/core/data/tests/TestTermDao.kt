package by.zm.quizlet.core.data.tests

import by.zm.quizlet.core.cache.room.dao.TermDao
import by.zm.quizlet.core.cache.room.model.TermItem
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.update

class TestTermDao : TermDao {

    override suspend fun insertTerm(term: TermItem) {
        TestDatabase.termsStateFlow.update { oldValues ->
            var isUpdate = false
            val updated = oldValues.map {
                if (it.id == term.id) {
                    isUpdate = true
                    term
                } else {
                    it
                }
            }
            if (isUpdate) updated else oldValues + listOf(term)
        }
    }

    override suspend fun insertTerms(terms: List<TermItem>) {
        TestDatabase.termsStateFlow.update { oldValues ->
            oldValues + terms
        }
    }

    override suspend fun deleteTerm(termItem: TermItem) {
        TestDatabase.termsStateFlow.update { terms ->
            terms.filterNot { it == termItem }
        }
    }

    override fun observeAllTermsByModuleId(id: Int): Flow<List<TermItem>> {
        val terms = TestDatabase.termsStateFlow.value.filter { it.moduleId == id }
        return flowOf(terms)
    }
}