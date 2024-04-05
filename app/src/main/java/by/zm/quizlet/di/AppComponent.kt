package by.zm.quizlet.di

import android.content.Context
import by.zm.quizlet.di.module.AppModule
import by.zm.quizlet.di.module.CacheModule
import by.zm.quizlet.ui.AppActivity
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [
    AppModule::class,
    CacheModule::class,
])
interface AppComponent {

    @Component.Factory
    interface Factory {
        fun create(@BindsInstance applicationContext: Context): AppComponent
    }

    fun inject(appActivity: AppActivity)
}