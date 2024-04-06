package by.zm.quizlet.core.cache

import by.zm.quizlet.core.cache.room.dao.ModuleDao
import by.zm.quizlet.core.cache.room.dao.ModuleWithTermsDao
import by.zm.quizlet.core.cache.room.dao.TermDao
import by.zm.quizlet.core.cache.room.model.ModuleItem
import by.zm.quizlet.core.cache.room.model.TermItem
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner
import org.mockito.kotlin.atLeastOnce
import org.mockito.kotlin.verify

@RunWith(MockitoJUnitRunner::class)
class CacheDataStoreTest {

    @Mock
    private lateinit var moduleDao: ModuleDao
    @Mock
    private lateinit var termDao: TermDao
    @Mock
    private lateinit var moduleWithTerms: ModuleWithTermsDao
    private lateinit var cacheDataStore: CacheDataStore

    @Before
    fun setup() {
        cacheDataStore = CacheDataStoreImpl(
            moduleDao = moduleDao,
            termDao = termDao,
            moduleWithTerms = moduleWithTerms,
        )
    }

    @Test
    fun insertModuleVerifyModuleDao() = runTest {
        val module = ModuleItem(id = 1, name = "Module1", description = "Desc1")
        cacheDataStore.insertModule(module)
        verify(moduleDao, atLeastOnce()).insertModule(module)
    }

    @Test
    fun deleteModuleVerifyModuleDao() = runTest {
        val module = ModuleItem(id = 1, name = "Module1", description = "Desc1")
        cacheDataStore.deleteModule(module)
        verify(moduleDao, atLeastOnce()).deleteModule(module)
    }

    @Test
    fun observeAllModulesVerifyModuleDao() {
        cacheDataStore.observeAllModules()
        verify(moduleDao, atLeastOnce()).observeAllModules()
    }

    @Test
    fun insertTermVerifyTermDao() = runTest {
        val term = TermItem(id = 1, title = "Term", translate = "Translate", moduleId = 1)
        cacheDataStore.insertTerm(term)
        verify(termDao, atLeastOnce()).insertTerm(term)
    }

    @Test
    fun insertTermsVerifyTermDao() = runTest {
        val terms = listOf(
            TermItem(id = 1, title = "Term1", translate = "Translate1", moduleId = 1),
            TermItem(id = 2, title = "Term2", translate = "Translate2", moduleId = 1),
            TermItem(id = 3, title = "Term3", translate = "Translate3", moduleId = 1),
        )
        cacheDataStore.insertTerms(terms)
        verify(termDao, atLeastOnce()).insertTerms(terms)
    }

    @Test
    fun deleteTermVerifyTermDao() = runTest {
        val term = TermItem(id = 1, title = "Term", translate = "Translate", moduleId = 1)
        cacheDataStore.deleteTerm(term)
        verify(termDao, atLeastOnce()).deleteTerm(term)
    }

    @Test
    fun observeAllTermsVerifyTermDao() {
        cacheDataStore.observeAllTermsByModuleId(1)
        verify(termDao, atLeastOnce()).observeAllTermsByModuleId(1)
    }

    @Test
    fun observeAllModulesWithTermsVerifyModuleWithTermsDao() {
        cacheDataStore.observeAllModulesWithTerms()
        verify(moduleWithTerms, atLeastOnce()).observeAllModulesWithTerms()
    }
}