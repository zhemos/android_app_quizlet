import com.android.build.gradle.LibraryExtension
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.configure
import org.gradle.kotlin.dsl.dependencies
import org.gradle.kotlin.dsl.kotlin

class AndroidFeatureConventionPlugin : Plugin<Project> {
    override fun apply(target: Project) = with(target) {
        pluginManager.apply {
            apply("quizlet.android.library")
            apply("quizlet.android.hilt")
        }
        extensions.configure<LibraryExtension> {
            defaultConfig {
                testInstrumentationRunner = "by.zm.quizlet.core.testing.AppTestRunner"
            }
            buildFeatures {
                viewBinding = true
            }
        }
        dependencies {
            add("implementation", project(":core:common"))
            add("implementation", project(":core:designsystem"))
            add("implementation", project(":core:ui"))
            add("implementation", project(":core:data"))
            add("implementation", project(":core:domain"))
            add("implementation", project(":core:model"))

            add("testImplementation", kotlin("test"))
            add("testImplementation", project(":core:testing"))
            add("androidTestImplementation", kotlin("test"))
            add("androidTestImplementation", project(":core:testing"))
        }
    }
}