package by.zm.quizlet.core.domain

import by.zm.quizlet.core.domain.model.Module
import by.zm.quizlet.core.domain.model.Term
import kotlinx.coroutines.flow.Flow

interface ModulesRepository {

    val modules: Flow<List<Module>>

    fun observeAllTermsByModuleId(id: Int): Flow<List<Term>>

    suspend fun insertModule(module: Module)

    suspend fun updateModule(module: Module)

    suspend fun deleteModule(module: Module)
}