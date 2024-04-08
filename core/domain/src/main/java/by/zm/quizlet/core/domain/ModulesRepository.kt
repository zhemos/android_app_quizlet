package by.zm.quizlet.core.domain

import by.zm.quizlet.core.domain.model.Term
import by.zm.quizlet.core.domain.model.Module
import kotlinx.coroutines.flow.Flow

interface ModulesRepository {

    val modules: Flow<List<Module>>

    suspend fun insertModule(module: Module)

    suspend fun updateModule(module: Module)

    suspend fun deleteModule(module: Module)

    suspend fun insertTerm(term: Term)

    suspend fun updateTerm(term: Term)

    suspend fun deleteTerm(term: Term)

    fun observeTermsByModuleId(id: Int): Flow<List<Term>>
}