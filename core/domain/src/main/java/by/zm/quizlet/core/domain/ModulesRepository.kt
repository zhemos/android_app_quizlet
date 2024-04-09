package by.zm.quizlet.core.domain

import by.zm.quizlet.core.domain.model.Module
import kotlinx.coroutines.flow.Flow

interface ModulesRepository {

    val test: String get() = "test"

    val modules: Flow<List<Module>>

    suspend fun insertModule(module: Module)

    suspend fun updateModule(module: Module)

    suspend fun deleteModule(module: Module)
}