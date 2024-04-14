package by.zm.quizlet.core.data.tests

import by.zm.quizlet.core.cache.room.dao.TermDao
import by.zm.quizlet.core.cache.room.model.TermItem
import kotlinx.coroutines.flow.Flow

class TestTermDao : TermDao {
    override suspend fun insertTerm(term: TermItem) {
        TODO("Not yet implemented")
    }

    override suspend fun insertTerms(terms: List<TermItem>) {
        TODO("Not yet implemented")
    }

    override suspend fun deleteTerm(termItem: TermItem) {
        TODO("Not yet implemented")
    }

    override fun observeAllTermsByModuleId(id: Int): Flow<List<TermItem>> {
        TODO("Not yet implemented")
    }
}