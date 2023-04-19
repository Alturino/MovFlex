plugins {
    id("com.android.library")
    kotlin("android")
    kotlin("kapt")
    id(Plugin.safeArg)
    id(Plugin.daggerHilt)
}

android {
    namespace = "com.onirutla.movflex.tv"
    compileSdk = AppConfig.compileSdk

    defaultConfig {
        minSdk = AppConfig.minSdk
        targetSdk = AppConfig.targetSdk

        testInstrumentationRunner = AppConfig.testInstrumentationRunner
        consumerProguardFiles.add(File("consumer-rules.pro"))
    }

    buildTypes {
        release {
            isMinifyEnabled = AppConfig.isReleaseMinifyEnabled
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
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
    applyRetrofit()
    applyOkhttp()
    applyMoshi()
    implementation(project(Modules.core))
}
