plugins {
    alias(libs.plugins.quizlet.android.library)
    id("com.google.devtools.ksp")
}

android {
    namespace = "by.zm.quizlet.core.domain"
}

dependencies {
    implementation(libs.hilt.android)
    ksp(libs.hilt.compiler)

    testImplementation(projects.core.testing)
}