package by.zm.quizlet.ui

import androidx.appcompat.app.AppCompatActivity
import by.zm.quizlet.R
import by.zm.quizlet.core.domain.ModulesRepository
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class AppActivity : AppCompatActivity(R.layout.activity_app) {
    @Inject
    lateinit var repo: ModulesRepository
}