package by.zm.quizlet.core.data

import by.zm.quizlet.core.domain.ModulesRepository
import by.zm.quizlet.core.domain.model.Module
import by.zm.quizlet.core.domain.model.Term
import kotlinx.coroutines.flow.Flow

class ModulesRepositoryImpl : ModulesRepository {
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

    override suspend fun insertTerm(term: Term) {
        TODO("Not yet implemented")
    }

    override suspend fun updateTerm(term: Term) {
        TODO("Not yet implemented")
    }

    override suspend fun deleteTerm(term: Term) {
        TODO("Not yet implemented")
    }

    override fun observeTermsByModuleId(id: Int): Flow<List<Term>> {
        TODO("Not yet implemented")
    }
}