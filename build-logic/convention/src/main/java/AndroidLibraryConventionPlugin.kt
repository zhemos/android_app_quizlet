import by.zm.quizlet.configureKotlinAndroid
import by.zm.quizlet.libs
import com.android.build.gradle.LibraryExtension
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.configure
import org.gradle.kotlin.dsl.dependencies
import org.gradle.kotlin.dsl.kotlin

class AndroidLibraryConventionPlugin : Plugin<Project> {

    override fun apply(target: Project) = with(target) {
        with(pluginManager) {
            apply("com.android.library")
            apply("org.jetbrains.kotlin.android")
        }

        extensions.configure<LibraryExtension> {
            configureKotlinAndroid(this)
            defaultConfig.targetSdk = 34
        }
        dependencies {
            add("implementation", libs.findLibrary("kotlinx.coroutines.core").get())
            add("testImplementation", kotlin("test"))
            add("testImplementation", project(":core:testing"))
            add("androidTestImplementation", kotlin("test"))
            add("androidTestImplementation", project(":core:testing"))
            add("testImplementation", libs.findLibrary("mockito.core").get())
            add("testImplementation", libs.findLibrary("mockito.kotlin").get())
            add("androidTestImplementation", libs.findLibrary("mockito.core").get())
        }
    }
}