package by.zm.quizlet.core.data.mapper

import by.zm.quizlet.core.cache.room.model.ModuleItem
import by.zm.quizlet.core.model.Module
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
        val moduleDto: ModuleItem = startModules.first.first()
        val module: by.zm.quizlet.core.model.Module = moduleMapper.mapToDomain(moduleDto)
        assertEquals(startModules.second.first(), module)
    }

    @Test
    fun mapToDto() {
        val startModules = getModules()
        val module: by.zm.quizlet.core.model.Module = startModules.second.first()
        val moduleDto: ModuleItem = moduleMapper.mapToDto(module)
        assertEquals(startModules.first.first(), moduleDto)
    }

    @Test
    fun mapToDomainList() {
        val startModules = getModules(sizeModules = 2)
        val modulesDto: List<ModuleItem> = startModules.first
        val modules: List<by.zm.quizlet.core.model.Module> = moduleMapper.mapToDomain(modulesDto)
        assertEquals(startModules.second, modules)
    }

    @Test
    fun mapToDtoList() {
        val startModules = getModules(sizeModules = 2)
        val modules: List<by.zm.quizlet.core.model.Module> = startModules.second
        val modulesDto: List<ModuleItem> = moduleMapper.mapToDto(modules)
        assertEquals(startModules.first, modulesDto)
    }

    private fun getModules(sizeModules: Int = 1): Pair<List<ModuleItem>, List<by.zm.quizlet.core.model.Module>> {
        val modulesDto = mutableListOf<ModuleItem>()
        val modules = mutableListOf<by.zm.quizlet.core.model.Module>()
        repeat(sizeModules) { i ->
            val moduleDto = ModuleItem(id = i, name = "name$i", description = "desc$i")
            val module = by.zm.quizlet.core.model.Module(
                id = i,
                name = "name$i",
                description = "desc$i",
                terms = emptyList()
            )
            modulesDto.add(moduleDto)
            modules.add(module)
        }
        return Pair(modulesDto, modules)
    }
}