plugins {
    id("com.google.devtools.ksp")
    alias(libs.plugins.quizlet.android.library)
    alias(libs.plugins.quizlet.android.hilt)
}

android {
    namespace = "by.zm.quizlet.core.cache"

    defaultConfig {
        testInstrumentationRunner = "by.zm.quizlet.core.testing.AppTestRunner"
    }
}

dependencies {
    implementation(projects.core.common)

    implementation(libs.kotlinx.coroutines.android)

    implementation(libs.room.runtime)
    implementation(libs.room.ktx)
    ksp(libs.room.compiler)
}