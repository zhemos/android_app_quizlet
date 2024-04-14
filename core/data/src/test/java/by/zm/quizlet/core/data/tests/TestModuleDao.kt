package by.zm.quizlet.core.data.tests

import by.zm.quizlet.core.cache.room.dao.ModuleDao
import by.zm.quizlet.core.cache.room.model.ModuleItem
import kotlinx.coroutines.flow.Flow

class TestModuleDao : ModuleDao {

    override suspend fun insertModule(module: ModuleItem) {
        TODO("Not yet implemented")
    }

    override suspend fun deleteModule(moduleItem: ModuleItem) {
        TODO("Not yet implemented")
    }

    override fun observeAllModules(): Flow<List<ModuleItem>> {
        TODO("Not yet implemented")
    }
}