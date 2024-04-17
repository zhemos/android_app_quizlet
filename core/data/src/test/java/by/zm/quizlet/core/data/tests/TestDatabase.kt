package by.zm.quizlet.core.data.tests

import by.zm.quizlet.core.cache.room.model.ModuleItem
import by.zm.quizlet.core.cache.room.model.TermItem
import kotlinx.coroutines.flow.MutableStateFlow

object TestDatabase {
    val modulesStateFlow = MutableStateFlow(emptyList<ModuleItem>())
    val termsStateFlow = MutableStateFlow(emptyList<TermItem>())
}