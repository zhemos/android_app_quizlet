package by.zm.quizlet.core.data.mapper

import by.zm.quizlet.core.cache.room.model.ModuleItem
import by.zm.quizlet.core.cache.room.model.ModuleWithTerms
import by.zm.quizlet.core.cache.room.model.TermItem
import by.zm.quizlet.core.domain.model.Module
import by.zm.quizlet.core.domain.model.Term
import org.junit.Before
import org.junit.Test
import kotlin.test.assertEquals

class ModuleMapperTest {

    private lateinit var moduleMapper: ModuleMapper

    @Before
    fun setup() {
        moduleMapper = ModuleMapper()
    }

    @Test
    fun mapToDomain() {
        val startModules = getModules()
        val moduleDto: ModuleWithTerms = startModules.first.first()
        val module: Module = moduleMapper.mapToDomain(moduleDto)
        assertEquals(startModules.second.first(), module)
    }

    @Test
    fun mapToDto() {
        val startModules = getModules()
        val module: Module = startModules.second.first()
        val moduleDto: ModuleWithTerms = moduleMapper.mapToDto(module)
        assertEquals(startModules.first.first(), moduleDto)
    }

    @Test
    fun mapToDomainList() {
        val startModules = getModules(sizeModules = 2)
        val modulesDto: List<ModuleWithTerms> = startModules.first
        val modules: List<Module> = moduleMapper.mapToDomain(modulesDto)
        assertEquals(startModules.second, modules)
    }

    @Test
    fun mapToDtoList() {
        val startModules = getModules(sizeModules = 2)
        val modules: List<Module> = startModules.second
        val modulesDto: List<ModuleWithTerms> = moduleMapper.mapToDto(modules)
        assertEquals(startModules.first, modulesDto)
    }

    private fun getModules(sizeModules: Int = 1, sizeTerms: Int = 3): Pair<List<ModuleWithTerms>, List<Module>> {
        val modulesDto = mutableListOf<ModuleWithTerms>()
        val modules = mutableListOf<Module>()
        repeat(sizeModules) { i ->
            val moduleDto = ModuleItem(id = i, name = "name$i", description = "desc$i")
            val termsDto = mutableListOf<TermItem>()
            val terms = mutableListOf<Term>()
            repeat(sizeTerms) { j ->
                val termDto = TermItem(
                    id = j, title = "title$j", translate = "tr$j",
                    imageUrl = "url$j", isFavourites = false, moduleId = i
                )
                termsDto.add(termDto)
                val term = Term(
                    id = j, title = "title$j", translate = "tr$j",
                    imageUrl = "url$j", isFavourites = false
                )
                terms.add(term)
            }
            val module = Module(id = i, name = "name$i", description = "desc$i", terms = terms)
            val moduleWithTerms = ModuleWithTerms(module = moduleDto, terms = termsDto)
            modulesDto.add(moduleWithTerms)
            modules.add(module)
        }
        return Pair(modulesDto, modules)
    }
}