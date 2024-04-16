package by.zm.quizlet.core.common.model

interface Mapper<DTO : Dto, DOMAIN> {

    fun mapToDomain(input: DTO): DOMAIN

    fun mapToDto(input: DOMAIN): DTO

    fun mapToDomain(input: List<DTO>): List<DOMAIN> {
        return input.map { mapToDomain(it) }
    }

    fun mapToDto(input: List<DOMAIN>): List<DTO> {
        return input.map { mapToDto(it) }
    }
}