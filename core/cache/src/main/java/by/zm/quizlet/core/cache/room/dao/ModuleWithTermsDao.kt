package by.zm.quizlet.core.cache.room.dao

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Transaction
import by.zm.quizlet.core.cache.room.model.ModuleWithTerms
import kotlinx.coroutines.flow.Flow

@Dao
interface ModuleWithTermsDao {

    @Transaction
    @Query("SELECT * FROM module_items")
    fun observeAllModulesWithTerms(): Flow<List<ModuleWithTerms>>
}