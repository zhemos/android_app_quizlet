package by.zm.quizlet.core.cache.room.dao

import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.SmallTest
import by.zm.quizlet.core.cache.room.AppDatabase
import by.zm.quizlet.core.cache.room.model.ModuleItem
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
class ModuleDaoTest {

    @get:Rule
    var hiltRule = HiltAndroidRule(this)

    @Inject
    @Named("test_db")
    lateinit var database: AppDatabase
    private lateinit var dao: ModuleDao

    @Before
    fun setup() {
        hiltRule.inject()
        dao = database.moduleDao()
    }

    @After
    fun teardown() {
        database.close()
    }

    @Test
    fun insertModule() = runTest {
        val module = ModuleItem(id = 1, name = "Module1", description = "Desc1")
        dao.insertModule(module)

        val modules = dao.observeAllModules().first()

        assertEquals(true, modules.contains(module))
    }

    @Test
    fun deleteModule() = runTest {
        val module = ModuleItem(id = 1, name = "Module1", description = "Desc1")
        dao.insertModule(module)
        dao.deleteModule(module)

        val modules = dao.observeAllModules().first()

        assertEquals(false, modules.contains(module))
    }

    @Test
    fun updateModule() = runTest {
        val module = ModuleItem(id = 1, name = "Module1", description = "Desc1")
        dao.insertModule(module)
        val newModule = module.copy(name = "New module name")
        dao.insertModule(newModule)

        val modules = dao.observeAllModules().first()

        assertEquals(true, modules.contains(newModule))
        assertEquals(false, modules.contains(module))
        assertEquals(1, modules.size)
    }
}