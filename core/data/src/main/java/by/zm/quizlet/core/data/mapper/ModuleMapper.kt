package by.zm.quizlet.core.data.mapper

import by.zm.quizlet.core.cache.room.model.ModuleItem
import by.zm.quizlet.core.common.model.Mapper
import by.zm.quizlet.core.domain.model.Module
import javax.inject.Inject

class ModuleMapper @Inject constructor() : Mapper<ModuleItem, Module> {

    override fun mapToDomain(input: ModuleItem): Module = with(input) {
        return@with Module(
            id = id ?: 0,
            name = name,
            description = description,
            terms = emptyList()
        )
    }

    override fun mapToDto(input: Module): ModuleItem = with(input) {
        return@with ModuleItem(
            id = id,
            name = name,
            description = description,
        )
    }
}