package by.zm.quizlet.core.cache

import by.zm.quizlet.core.cache.room.dao.ModuleDao
import by.zm.quizlet.core.cache.room.dao.ModuleWithTermsDao
import by.zm.quizlet.core.cache.room.dao.TermDao

interface CacheDataStore

class CacheDataStoreImpl(
    private val moduleDao: ModuleDao,
    private val termDao: TermDao,
    private val moduleWithTerms: ModuleWithTermsDao,
) : CacheDataStore