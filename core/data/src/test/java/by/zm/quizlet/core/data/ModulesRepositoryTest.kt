package by.zm.quizlet.core.data

import by.zm.quizlet.core.domain.ModulesRepository
import by.zm.quizlet.core.domain.model.Module
import by.zm.quizlet.core.domain.model.Term
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test
import kotlin.test.assertEquals

class ModulesRepositoryTest {

    private lateinit var modulesRepository: ModulesRepository

    @Before
    fun setup() {
        modulesRepository = FakeModulesRepository()
    }

    @Test
    fun insertModule() = runTest {
        val terms = listOf(
            Term(id = 1, title = "title1", translate = "tr1"),
            Term(id = 2, title = "title2", translate = "tr2"),
            Term(id = 3, title = "title3", translate = "tr3"),
        )
        val module = Module(id = 1, name = "module1", description = "desc1", terms = terms)

        modulesRepository.insertModule(module)

        val modules = modulesRepository.modules.first()
        assertEquals(1, modules.size)
        assertEquals(module, modules.first())
        assertEquals(terms.size, modules.first().terms.size)
        assertEquals(terms.first(), modules.first().terms.first())
    }

    @Test
    fun updateModule() = runTest {
        val terms = listOf(
            Term(id = 1, title = "title1", translate = "tr1"),
            Term(id = 2, title = "title2", translate = "tr2"),
            Term(id = 3, title = "title3", translate = "tr3"),
        )
        val module = Module(id = 1, name = "module1", description = "desc1", terms = terms)

        modulesRepository.insertModule(module)
        val newTerms = listOf(
            Term(id = 1, title = "new title", translate = "tr1"),
        )
        val newModule = Module(id = 1, name = "new module", description = "desc1", terms = newTerms)
        modulesRepository.updateModule(newModule)

        val modules = modulesRepository.modules.first()
        assertEquals(1, modules.size)
        assertEquals(newModule, modules.first())
        assertEquals(newTerms.size, modules.first().terms.size)
        assertEquals(newTerms.first(), modules.first().terms.first())
    }

    @Test
    fun deleteModule() = runTest {
        val terms = listOf(
            Term(id = 1, title = "title1", translate = "tr1"),
            Term(id = 2, title = "title2", translate = "tr2"),
            Term(id = 3, title = "title3", translate = "tr3"),
        )
        val module = Module(id = 1, name = "module1", description = "desc1", terms = terms)

        modulesRepository.insertModule(module)
        modulesRepository.deleteModule(module)

        val modules = modulesRepository.modules.first()
        assertEquals(0, modules.size)
    }
}