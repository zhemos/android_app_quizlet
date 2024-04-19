package by.zm.quizlet.core.domain.usecases

import by.zm.quizlet.core.domain.ModulesRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import by.zm.quizlet.core.domain.model.Module
import kotlinx.coroutines.flow.flowOf

class GetModulesUseCase @Inject constructor(
    private val modulesRepository: ModulesRepository
) {

    operator fun invoke(): Flow<List<Module>> {
        return flowOf()
    }
}