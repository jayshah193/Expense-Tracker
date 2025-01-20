buildscript {
    repositories {
        google()
        mavenCentral()
        jcenter() // Sometimes required for legacy dependencies
    }

    dependencies {
        // Update the classpath to the latest version
        classpath("com.android.tools.build:gradle:8.0.0")
        classpath("org.jetbrains.kotlin:kotlin-gradle-plugin:1.8.21") // Ensure Kotlin version is also up-to-date
        classpath("com.google.dagger:hilt-android-gradle-plugin:2.48")
    }
}
