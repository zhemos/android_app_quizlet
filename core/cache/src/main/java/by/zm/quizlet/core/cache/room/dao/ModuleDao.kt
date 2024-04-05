package by.zm.quizlet.core.cache.room.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import by.zm.quizlet.core.cache.room.model.ModuleItem
import kotlinx.coroutines.flow.Flow

@Dao
interface ModuleDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertModule(module: ModuleItem)

    @Delete
    suspend fun deleteModule(moduleItem: ModuleItem)

    @Query("SELECT * FROM module_items")
    fun observeAllModules(): Flow<List<ModuleItem>>
}