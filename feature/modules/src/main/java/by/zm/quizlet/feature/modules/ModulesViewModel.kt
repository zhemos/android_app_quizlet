package by.zm.quizlet.feature.modules

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import by.zm.quizlet.core.domain.usecases.GetModulesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

interface IModulesViewModel

@HiltViewModel
class ModulesViewModel @Inject constructor(
    private val getModules: GetModulesUseCase,
    private val savedStateHandle: SavedStateHandle,
) : ViewModel() {
    fun test() = "$"
}