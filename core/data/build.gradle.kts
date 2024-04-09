plugins {
    alias(libs.plugins.quizlet.android.library)
    alias(libs.plugins.quizlet.android.hilt)
}

android {
    namespace = "by.zm.quizlet.core.data"

    defaultConfig {
        testInstrumentationRunner = "by.zm.quizlet.core.testing.AppTestRunner"
    }
}

dependencies {
    implementation(projects.core.common)
    implementation(projects.core.cache)
    implementation(projects.core.domain)
}