plugins {
    alias(libs.plugins.quizlet.android.library)
}

android {
    namespace = "by.zm.quizlet.core.ui"

    buildFeatures {
        viewBinding = true
    }
}

dependencies {
    implementation(projects.core.common)
    implementation(projects.core.designsystem)

    api(libs.androidx.core.ktx)
    api(libs.androidx.appcompat)
    api(libs.androidx.fragment.ktx)
    api(libs.material)
}