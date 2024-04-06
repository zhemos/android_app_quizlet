plugins {
    alias(libs.plugins.quizlet.android.library)
    alias(libs.plugins.quizlet.android.hilt)
}

android {
    namespace = "by.zm.quizlet.core.data"
}

dependencies {
    implementation(projects.core.common)
    implementation(projects.core.cache)
    implementation(projects.core.domain)
}