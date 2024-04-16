package by.zm.quizlet.core.data.mapper

import by.zm.quizlet.core.cache.room.model.ModuleWithTerms
import by.zm.quizlet.core.common.model.Mapper
import by.zm.quizlet.core.domain.model.Module

class ModuleMapper : Mapper<ModuleWithTerms, Module> {

    override fun mapToDomain(input: ModuleWithTerms): Module {
        TODO("Not yet implemented")
    }

    override fun mapToDto(input: Module): ModuleWithTerms {
        TODO("Not yet implemented")
    }
}