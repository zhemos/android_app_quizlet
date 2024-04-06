package by.zm.quizlet.core.cache.room.dao

import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.SmallTest
import by.zm.quizlet.core.cache.room.AppDatabase
import by.zm.quizlet.core.cache.room.model.ModuleItem
import by.zm.quizlet.core.cache.room.model.TermItem
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.runTest
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import javax.inject.Inject
import javax.inject.Named
import kotlin.test.assertEquals

@RunWith(AndroidJUnit4::class)
@SmallTest
@HiltAndroidTest
class ModuleWithTermsDaoTest {

    @get:Rule
    var hiltRule = HiltAndroidRule(this)

    @Inject
    @Named("test_db")
    lateinit var database: AppDatabase
    private lateinit var moduleDao: ModuleDao
    private lateinit var termDao: TermDao
    private lateinit var dao: ModuleWithTermsDao

    @Before
    fun setup() {
        hiltRule.inject()
        moduleDao = database.moduleDao()
        termDao = database.termDao()
        dao = database.moduleWithTerms()
    }

    @After
    fun teardown() {
        database.close()
    }

    @Test
    fun insertModuleItemWithoutTerms() = runTest {
        val moduleItem = ModuleItem(id = 1, name = "Module1", description = "Desc1")
        moduleDao.insertModule(moduleItem)

        val modules = dao.observeAllModulesWithTerms().first()

        assertEquals(1, modules.size)
        assertEquals(moduleItem, modules.first().module)
        assertEquals(0, modules.first().terms.size)
    }

    @Test
    fun insertModuleWithTerms() = runTest {
        val moduleItem = ModuleItem(id = 1, name = "Module1", description = "Desc1")
        val termItem = TermItem(id = 1, term = "Term", translate = "Translate", moduleId = 1)
        moduleDao.insertModule(moduleItem)
        termDao.insertTerm(termItem)

        val modules = dao.observeAllModulesWithTerms().first()

        assertEquals(1, modules.size)
        assertEquals(moduleItem, modules.first().module)
        assertEquals(1, modules.first().terms.size)
        assertEquals(termItem, modules.first().terms.first())
    }

    @Test
    fun deleteModuleWithTerms() = runTest {
        val moduleItem = ModuleItem(id = 1, name = "Module1", description = "Desc1")
        val termItem = TermItem(id = 1, term = "Term", translate = "Translate", moduleId = 1)
        moduleDao.insertModule(moduleItem)
        termDao.insertTerm(termItem)
        moduleDao.deleteModule(moduleItem)
        termDao.deleteTerm(termItem)

        val modules = dao.observeAllModulesWithTerms().first()

        assertEquals(0, modules.size)
    }
}