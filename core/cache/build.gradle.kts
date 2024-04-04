plugins {
    id("kotlin-kapt")
    alias(libs.plugins.quizlet.android.library)
}

android {
    namespace = "by.zm.quizlet.core.cache"
}

dependencies {
    implementation(libs.room.runtime)
    kapt(libs.room.compiler)
}