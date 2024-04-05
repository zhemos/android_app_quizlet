package by.zm.quizlet.core.cache.room.dao

import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.SmallTest
import by.zm.quizlet.core.cache.room.AppDatabase
import by.zm.quizlet.core.cache.room.model.ModuleItem
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.runTest
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import kotlin.test.assertEquals

@RunWith(AndroidJUnit4::class)
@SmallTest
class ModuleDaoTest {

    private lateinit var database: AppDatabase
    private lateinit var dao: ModuleDao

    @Before
    fun setup() {
        database = Room.inMemoryDatabaseBuilder(
            context = ApplicationProvider.getApplicationContext(),
            klass = AppDatabase::class.java,
        ).allowMainThreadQueries().build()
        dao = database.moduleDao()
    }

    @After
    fun teardown() {
        database.close()
    }

    @Test
    fun insertModule() = runTest {
        val moduleItem = ModuleItem(id = 1, name = "Module1", description = "Desc1")
        dao.insertModule(moduleItem)

        val modules = dao.observeAllModules().first()

        assertEquals(true, modules.contains(moduleItem))
    }

    @Test
    fun deleteModule() = runTest {
        val moduleItem = ModuleItem(id = 1, name = "Module1", description = "Desc1")
        dao.insertModule(moduleItem)
        dao.deleteModule(moduleItem)

        val modules = dao.observeAllModules().first()

        assertEquals(false, modules.contains(moduleItem))
    }

    @Test
    fun updateModule() = runTest {
        val moduleItem = ModuleItem(id = 1, name = "Module1", description = "Desc1")
        dao.insertModule(moduleItem)
        val newModuleItem = moduleItem.copy(name = "New module name")
        dao.insertModule(newModuleItem)

        val modules = dao.observeAllModules().first()

        assertEquals(true, modules.contains(newModuleItem))
        assertEquals(false, modules.contains(moduleItem))
        assertEquals(1, modules.size)
    }
}