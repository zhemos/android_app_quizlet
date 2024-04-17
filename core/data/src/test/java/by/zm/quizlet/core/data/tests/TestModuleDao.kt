package by.zm.quizlet.core.data.tests

import by.zm.quizlet.core.cache.room.dao.ModuleDao
import by.zm.quizlet.core.cache.room.model.ModuleItem
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.update

class TestModuleDao : ModuleDao {

    override suspend fun insertModule(module: ModuleItem) {
        TestDatabase.modulesStateFlow.update { oldValues ->
            var isUpdate = false
            val updated = oldValues.map {
                if (it.id == module.id) {
                    isUpdate = true
                    module
                } else {
                    it
                }
            }
            if (isUpdate) updated else oldValues + listOf(module)
        }
    }

    override suspend fun deleteModule(moduleItem: ModuleItem) {
        TestDatabase.modulesStateFlow.update { modules ->
            modules.filterNot { it == moduleItem }
        }
    }

    override fun observeAllModules(): Flow<List<ModuleItem>> {
        return TestDatabase.modulesStateFlow
    }
}