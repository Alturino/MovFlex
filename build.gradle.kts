// Top-level build file where you can add configuration options common to all sub-projects/modules.
buildscript {
    dependencies {
        classpath(Plugin.Gradle.safeArg)
        classpath(Plugin.Gradle.daggerHilt)
        classpath(Plugin.Gradle.mapsSecret)
        classpath(Plugin.Gradle.kotlin)
    }
    repositories {
        mavenCentral()
        gradlePluginPortal()
        google()
    }
}

plugins {
    id("com.android.application") version Versions.gradle apply false
    id("com.android.library") version Versions.gradle apply false
    kotlin("android") version Versions.kotlin apply false
    id("com.android.dynamic-feature") version Versions.gradle apply false
}

tasks.register("clean", Delete::class) {
    delete(rootProject.buildDir)
}
