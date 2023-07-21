plugins {
    id("com.android.application")
    kotlin("android")
    kotlin("kapt")
    id(Plugin.safeArg)
    id(Plugin.daggerHilt)
    id(Plugin.mapsSecret)
}

hilt {
    enableAggregatingTask = true
}

secrets {
    // Change the properties file from the default "local.properties" in your root project
    // to another properties file in your root project.
    propertiesFileName = "secrets.properties"

    // A properties file containing default secret values. This file can be checked in version
    // control.
    defaultPropertiesFileName = "secrets.defaults.properties"

    // Configure which keys should be ignored by the plugin by providing regular expressions.
    // "sdk.dir" is ignored by default.
    ignoreList.add("keyToIgnore") // Ignore the key "keyToIgnore"
    ignoreList.add("sdk.*")       // Ignore all keys matching the regexp "sdk.*"
}

android {
    namespace = AppConfig.appNamespace
    compileSdk = AppConfig.compileSdk

    defaultConfig {
        applicationId = AppConfig.appNamespace
        minSdk = AppConfig.minSdk
        targetSdk = AppConfig.targetSdk
        versionCode = AppConfig.versionCode
        versionName = AppConfig.versionName

        testInstrumentationRunner = AppConfig.testInstrumentationRunner
    }

    buildTypes {
        release {
            isMinifyEnabled = AppConfig.isReleaseMinifyEnabled
        }

        debug {
            isMinifyEnabled = AppConfig.isDebugMinifyEnabled
        }
    }

    buildFeatures {
        viewBinding = true
        compose = true
        buildConfig = true
    }

    composeOptions {
        kotlinCompilerExtensionVersion = "1.4.6"
    }

    compileOptions {
        sourceCompatibility = AppConfig.sourceCompatibility
        targetCompatibility = AppConfig.targetCompatibility
    }

    kotlinOptions {
        jvmTarget = AppConfig.kotlinJvmTarget
    }

}

dependencies {
    applyShared()
    implementation(project(Modules.core))
    implementation(project(Modules.movie))
    implementation(project(Modules.favorite))
    implementation(project(Modules.tv))
}
