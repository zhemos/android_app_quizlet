package by.zm.quizlet.core.cache.room.model

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.Relation
import by.zm.quizlet.core.common.model.Dto

@Entity(tableName = "module_items")
data class ModuleItem(
    @PrimaryKey(autoGenerate = true)
    val id: Int? = null,
    val name: String,
    val description: String,
) : Dto

@Entity(tableName = "term_items")
data class TermItem(
    @PrimaryKey(autoGenerate = true)
    val id: Int? = null,
    val title: String,
    val translate: String,
    val imageUrl: String,
    val isFavourites: Boolean,
    val moduleId: Int,
) : Dto

data class ModuleWithTerms(
    @Embedded val module: ModuleItem,
    @Relation(
        parentColumn = "id",
        entityColumn = "moduleId",
    )
    val terms: List<TermItem>,
) : Dto