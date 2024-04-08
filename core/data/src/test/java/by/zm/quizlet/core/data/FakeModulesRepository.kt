package by.zm.quizlet.core.data

import by.zm.quizlet.core.domain.ModulesRepository
import by.zm.quizlet.core.domain.model.Module
import by.zm.quizlet.core.domain.model.Term
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow

class FakeModulesRepository : ModulesRepository {

    private val fakeModules = mutableListOf<Module>()

    private val observableModules = MutableStateFlow<List<Module>>(fakeModules)

    private suspend fun refresh() {
        observableModules.emit(fakeModules)
    }

    override val modules: Flow<List<Module>> get() = observableModules

    override suspend fun insertModule(module: Module) {
        fakeModules.add(module)
        refresh()
    }

    //???
    override suspend fun updateModule(module: Module) {
        val index = fakeModules.indexOf(module)
        if (index == -1) return
        fakeModules.removeAt(index)
        fakeModules.add(index, module)
        refresh()
    }

    override suspend fun deleteModule(module: Module) {
        fakeModules.remove(module)
        refresh()
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