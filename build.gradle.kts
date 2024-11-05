// Top-level build file where you can add configuration options common to all sub-projects/modules.
plugins {

    alias(libs.plugins.compose.compiler)
    //agregadas
    alias(libs.plugins.android.application) apply false
    //segun https://www.jetbrains.com/help/kotlin-multiplatform-dev/compose-compiler.html#managing-the-compose-compiler-with-gradle-plugins

}