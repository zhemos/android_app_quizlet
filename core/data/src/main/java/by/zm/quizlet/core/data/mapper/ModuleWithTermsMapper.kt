package by.zm.quizlet.core.data.mapper

import by.zm.quizlet.core.cache.room.model.ModuleItem
import by.zm.quizlet.core.cache.room.model.ModuleWithTerms
import by.zm.quizlet.core.common.model.Mapper
import by.zm.quizlet.core.model.Module
import javax.inject.Inject

class ModuleWithTermsMapper @Inject constructor() : Mapper<ModuleWithTerms, Module> {

    override fun mapToDomain(input: ModuleWithTerms): Module = with(input) {
        val moduleId = module.id ?: 0
        val termMapper = TermMapper(moduleId = moduleId)
        return Module(
            id = moduleId,
            name = module.name,
            description = module.description,
            terms = termMapper.mapToDomain(terms)
        )
    }

    override fun mapToDto(input: Module): ModuleWithTerms = with(input) {
        val module = ModuleItem(id = id, name = name, description = description)
        val termMapper = TermMapper(moduleId = id)
        return ModuleWithTerms(
            module = module,
            terms = termMapper.mapToDto(terms)
        )
    }
}