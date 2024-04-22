package by.zm.quizlet.core.common.model

import by.zm.quizlet.core.model.Domain

interface Mapper<DTO : Dto, DOMAIN : Domain> {

    fun mapToDomain(input: DTO): DOMAIN

    fun mapToDto(input: DOMAIN): DTO

    fun mapToDomain(input: List<DTO>): List<DOMAIN> {
        return input.map { mapToDomain(it) }
    }

    fun mapToDto(input: List<DOMAIN>): List<DTO> {
        return input.map { mapToDto(it) }
    }
}