package by.zm.quizlet.core.data.mapper

import by.zm.quizlet.core.cache.room.model.TermItem
import by.zm.quizlet.core.common.model.Mapper
import by.zm.quizlet.core.domain.model.Term

class TermMapper(
    private val moduleId: Int,
) : Mapper<TermItem, Term> {

    override fun mapToDomain(input: TermItem): Term = with(input) {
        return@with Term(
            id = id ?: 0,
            title = title,
            translate = translate,
            imageUrl = imageUrl,
            isFavourites = isFavourites,
        )
    }

    override fun mapToDto(input: Term): TermItem = with(input) {
        return@with TermItem(
            id = id,
            title = title,
            translate = translate,
            imageUrl = imageUrl,
            isFavourites = isFavourites,
            moduleId = moduleId,
        )
    }
}