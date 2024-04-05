package by.zm.quizlet

import android.app.Application
import by.zm.quizlet.di.AppComponent
import by.zm.quizlet.di.DaggerAppComponent

class QuizletApplication : Application() {

    private var _appComponent: AppComponent? = null
    val appComponent: AppComponent get() = _appComponent ?: error("app component error")

    override fun onCreate() {
        super.onCreate()
        _appComponent = DaggerAppComponent.factory().create(applicationContext)
    }
}