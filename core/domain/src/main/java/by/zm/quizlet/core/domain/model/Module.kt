package by.zm.quizlet.core.domain.model

data class Module(
    val id: Int,
    val name: String,
    val description: String,
    val terms: List<Term>,
)