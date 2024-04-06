plugins {
    alias(libs.plugins.quizlet.android.library)
    alias(libs.plugins.quizlet.android.hilt)
}

android {
    namespace = "by.zm.quizlet.core.testing"
}

dependencies {
    api(libs.junit)
    api(libs.androidx.junit)
    api(libs.androidx.espresso.core)
    api(libs.kotlinx.coroutines.test)
    api(libs.hilt.android.testing)

    implementation(projects.core.common)
    implementation(projects.core.data)
}