import by.zm.quizlet.libs
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.dependencies

class AndroidDaggerConventionPlugin : Plugin<Project> {

    override fun apply(target: Project) = with(target) {
        with(pluginManager) {
            apply("com.google.devtools.ksp")
        }
        dependencies {
            "implementation"(libs.findLibrary("dagger").get())
            "ksp"(libs.findLibrary("dagger.compiler").get())
            "kspAndroidTest"(libs.findLibrary("dagger.compiler").get())
            "kspTest"(libs.findLibrary("dagger.compiler").get())
        }
    }
}