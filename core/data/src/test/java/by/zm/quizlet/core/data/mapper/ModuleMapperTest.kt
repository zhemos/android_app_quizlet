package by.zm.quizlet.core.data.mapper

import by.zm.quizlet.core.cache.room.model.ModuleItem
import by.zm.quizlet.core.cache.room.model.ModuleWithTerms
import by.zm.quizlet.core.cache.room.model.TermItem
import by.zm.quizlet.core.domain.model.Module
import org.junit.Before
import org.junit.Test

class ModuleMapperTest {

    private lateinit var moduleMapper: ModuleMapper

    @Before
    fun setup() {
        moduleMapper = ModuleMapper()
    }

    @Test
    fun mapToDomain() {
        val module = getModules().first()
        val a = moduleMapper.mapToDomain(module)
    }

    @Test
    fun mapToDto() {

    }

    @Test
    fun mapToDomainList() {

    }

    @Test
    fun mapToDtoList() {

    }

    private fun getModules(sizeModules: Int = 1, sizeTerms: Int = 3): List<ModuleWithTerms> {
        val modulesDto = mutableListOf<ModuleWithTerms>()
        val modules = mutableListOf<Module>()
        repeat(sizeModules) { i ->
            val moduleDto = ModuleItem(id = i, name = "name$i", description = "desc$i")
            val terms = mutableListOf<TermItem>()
            repeat(sizeTerms) { j ->
                val term = TermItem(
                    id = j, title = "title$j", translate = "tr$j",
                    imageUrl = "", isFavourites = false, moduleId = i
                )
                terms.add(term)
            }
            val moduleWithTerms = ModuleWithTerms(module = moduleDto, terms = terms)
            modulesDto.add(moduleWithTerms)
        }
        return modulesDto
    }
}