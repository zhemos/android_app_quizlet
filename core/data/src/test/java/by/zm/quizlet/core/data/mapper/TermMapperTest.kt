package by.zm.quizlet.core.data.mapper

import by.zm.quizlet.core.cache.room.model.TermItem
import by.zm.quizlet.core.domain.model.Term
import org.junit.Before
import org.junit.Test
import kotlin.test.assertEquals

class TermMapperTest {

    private val moduleId = 1
    private lateinit var termMapper: TermMapper

    @Before
    fun setup() {
        termMapper = TermMapper(moduleId = moduleId)
    }

    @Test
    fun mapToDomain() {
        val startTerms = getTerms()
        val termsDto: List<TermItem> = startTerms.first
        val terms: List<Term> = termMapper.mapToDomain(termsDto)
        assertEquals(startTerms.second, terms)
    }

    @Test
    fun mapToDto() {
        val startTerms = getTerms()
        val terms: List<Term> = startTerms.second
        val termsDto: List<TermItem> = termMapper.mapToDto(terms)
        assertEquals(startTerms.first, termsDto)
    }

    private fun getTerms(size: Int = 3): Pair<List<TermItem>, List<Term>> {
        val termsDto = mutableListOf<TermItem>()
        val terms = mutableListOf<Term>()
        repeat(size) { i ->
            val termDto = TermItem(
                id = i, title = "title$i", translate = "tr$i",
                imageUrl = "url$i", isFavourites = false, moduleId = moduleId
            )
            termsDto.add(termDto)
            val term = Term(
                id = i, title = "title$i", translate = "tr$i",
                imageUrl = "url$i", isFavourites = false
            )
            terms.add(term)
        }
        return Pair(termsDto, terms)
    }
}