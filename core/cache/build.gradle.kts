plugins {
    id("com.google.devtools.ksp")
    alias(libs.plugins.quizlet.android.library)
}

android {
    namespace = "by.zm.quizlet.core.cache"

    defaultConfig {
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }
}

dependencies {
    implementation(projects.core.common)

    implementation(libs.kotlinx.coroutines.android)

    implementation(libs.room.runtime)
    implementation(libs.room.ktx)
    ksp(libs.room.compiler)
}