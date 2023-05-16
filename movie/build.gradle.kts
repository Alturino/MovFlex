plugins {
    id("com.android.library")
    kotlin("android")
    kotlin("kapt")
    id(Plugin.safeArg)
    id(Plugin.daggerHilt)
}

android {
    namespace = "${AppConfig.appNamespace}.movie"
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
        compose = true
        buildConfig = true
    }

    composeOptions {
        kotlinCompilerExtensionVersion = "1.4.6"
    }

}

dependencies {
    applyShared()
    applyRetrofit()
    implementation(project(Modules.core))
}
