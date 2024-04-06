package by.zm.quizlet.core.cache.room.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import by.zm.quizlet.core.cache.room.model.TermItem
import kotlinx.coroutines.flow.Flow

@Dao
interface TermDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertTerm(term: TermItem)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertTerms(terms: List<TermItem>)

    @Delete
    suspend fun deleteTerm(termItem: TermItem)

    @Query("SELECT * FROM term_items WHERE moduleId = :id")
    fun observeAllTermsByModuleId(id: Int): Flow<List<TermItem>>
}