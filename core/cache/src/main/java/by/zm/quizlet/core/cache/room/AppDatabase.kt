package by.zm.quizlet.core.cache.room

import androidx.room.Database
import androidx.room.RoomDatabase
import by.zm.quizlet.core.cache.room.dao.ModuleDao
import by.zm.quizlet.core.cache.room.dao.ModuleWithTermsDao
import by.zm.quizlet.core.cache.room.dao.TermDao
import by.zm.quizlet.core.cache.room.model.ModuleItem
import by.zm.quizlet.core.cache.room.model.TermItem

@Database(
    entities = [
        ModuleItem::class,
        TermItem::class,
    ],
    version = 1,
)
abstract class AppDatabase : RoomDatabase() {

    abstract fun moduleDao(): ModuleDao

    abstract fun termDao(): TermDao

    abstract fun moduleWithTerms(): ModuleWithTermsDao
}