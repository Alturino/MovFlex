plugins {
    id("com.android.dynamic-feature")
    kotlin("android")
    kotlin("kapt")
    id(Plugin.safeArg)
}

android {
    compileSdk = AppConfig.compileSdk

    defaultConfig {
        minSdk = AppConfig.minSdk

        testInstrumentationRunner = AppConfig.testInstrumentationRunner
    }

    buildTypes {
        release {

        }
    }

    compileOptions {
        sourceCompatibility = AppConfig.sourceCompatibility
        targetCompatibility = AppConfig.targetCompatibility
    }

    kotlinOptions {
        jvmTarget = AppConfig.kotlinJvmTarget
    }

    buildFeatures {
        viewBinding = true
    }
}

dependencies {
    applyShared()
    implementation(project(Modules.core))
    implementation(project(Modules.app))
}
