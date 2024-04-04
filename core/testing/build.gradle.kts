plugins {
    alias(libs.plugins.quizlet.android.library)
}

android {
    namespace = "by.zm.quizlet.core.testing"
}

dependencies {
    api(libs.junit)
    api(libs.androidx.junit)
    api(libs.androidx.espresso.core)

    implementation(projects.core.common)
    implementation(projects.core.data)
}