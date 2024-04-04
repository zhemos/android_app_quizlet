package by.zm.quizlet.ui

import androidx.appcompat.app.AppCompatActivity
import by.zm.quizlet.R
import by.zm.quizlet.core.common.CommonTest
import by.zm.quizlet.core.data.DataTest

class AppActivity : AppCompatActivity(R.layout.activity_app) {
    val commonThread = CommonTest()
    val dataTest = DataTest()
}