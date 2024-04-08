package by.zm.quizlet.core.domain.model

data class Term(
    val id: Int,
    val title: String,
    val translate: String,
    val imageUrl: String,
    val isFavourites: Boolean,
)