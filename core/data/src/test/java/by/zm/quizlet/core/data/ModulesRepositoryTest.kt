package by.zm.quizlet.core.data

import by.zm.quizlet.core.cache.CacheDataStore
import by.zm.quizlet.core.cache.CacheDataStoreImpl
import by.zm.quizlet.core.data.mapper.ModuleMapper
import by.zm.quizlet.core.data.mapper.ModuleWithTermsMapper
import by.zm.quizlet.core.data.tests.TestModuleDao
import by.zm.quizlet.core.data.tests.TestModuleWithTermsDao
import by.zm.quizlet.core.data.tests.TestTermDao
import by.zm.quizlet.core.domain.ModulesRepository
import by.zm.quizlet.core.model.Module
import by.zm.quizlet.core.model.Term
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test

class ModulesRepositoryTest {

    private lateinit var modulesRepository: ModulesRepository
    private lateinit var cacheDataStore: CacheDataStore
    private lateinit var moduleMapper: ModuleMapper
    private lateinit var moduleWithTermsMapper: ModuleWithTermsMapper

    @Before
    fun setup() {
        cacheDataStore = CacheDataStoreImpl(
            moduleDao = TestModuleDao(),
            termDao = TestTermDao(),
            moduleWithTerms = TestModuleWithTermsDao(),
        )
        moduleWithTermsMapper = ModuleWithTermsMapper()
        moduleMapper = ModuleMapper()
        modulesRepository = ModulesRepositoryImpl(
            cacheDataStore = cacheDataStore,
            moduleMapper = moduleMapper,
            moduleWithTermsMapper = moduleWithTermsMapper,
        )
    }

    @Test
    fun insertModule() = runTest {
        val terms = listOf(
            by.zm.quizlet.core.model.Term(
                id = 1,
                title = "title1",
                translate = "tr1",
                imageUrl = "",
                isFavourites = false
            ),
            by.zm.quizlet.core.model.Term(
                id = 2,
                title = "title2",
                translate = "tr2",
                imageUrl = "",
                isFavourites = false
            ),
            by.zm.quizlet.core.model.Term(
                id = 3,
                title = "title3",
                translate = "tr3",
                imageUrl = "",
                isFavourites = false
            ),
        )
        val module = by.zm.quizlet.core.model.Module(
            id = 1,
            name = "module1",
            description = "desc1",
            terms = terms
        )

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
            by.zm.quizlet.core.model.Term(
                id = 1,
                title = "title1",
                translate = "tr1",
                imageUrl = "",
                isFavourites = false
            ),
            by.zm.quizlet.core.model.Term(
                id = 2,
                title = "title2",
                translate = "tr2",
                imageUrl = "",
                isFavourites = false
            ),
            by.zm.quizlet.core.model.Term(
                id = 3,
                title = "title3",
                translate = "tr3",
                imageUrl = "",
                isFavourites = false
            ),
        )
        val module = by.zm.quizlet.core.model.Module(
            id = 1,
            name = "module1",
            description = "desc1",
            terms = terms
        )

        modulesRepository.insertModule(module)
        val newTerms = listOf(
            by.zm.quizlet.core.model.Term(
                id = 1,
                title = "new title",
                translate = "tr1",
                imageUrl = "",
                isFavourites = false
            ),
        )
        val newModule = by.zm.quizlet.core.model.Module(
            id = 1,
            name = "new module",
            description = "desc1",
            terms = newTerms
        )
        modulesRepository.updateModule(newModule)

        val modules = modulesRepository.modules.first()
        assertEquals(1, modules.size)
        assertEquals(newModule, modules.first())
        assertEquals(newTerms.size, modules.first().terms.size)
        assertEquals(newTerms.first(), modules.first().terms.first())
        println(modules.first().terms)
    }

    @Test
    fun deleteModule() = runTest {
        val terms = listOf(
            by.zm.quizlet.core.model.Term(
                id = 1,
                title = "title1",
                translate = "tr1",
                imageUrl = "",
                isFavourites = false
            ),
            by.zm.quizlet.core.model.Term(
                id = 2,
                title = "title2",
                translate = "tr2",
                imageUrl = "",
                isFavourites = false
            ),
            by.zm.quizlet.core.model.Term(
                id = 3,
                title = "title3",
                translate = "tr3",
                imageUrl = "",
                isFavourites = false
            ),
        )
        val module = by.zm.quizlet.core.model.Module(
            id = 1,
            name = "module1",
            description = "desc1",
            terms = terms
        )

        modulesRepository.insertModule(module)
        modulesRepository.deleteModule(module)

        val modules = modulesRepository.modules.first()
        val actualTerms = modulesRepository.observeAllTermsByModuleId(id = 1).first()
        assertEquals(0, modules.size)
        assertEquals(0, actualTerms.size)
    }
}