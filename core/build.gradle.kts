plugins {
    id("com.android.library")
    kotlin("android")
    kotlin("kapt")
    id(Plugin.mapsSecret)
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
    namespace = "${AppConfig.appNamespace}.core"
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
    applyRoom()
    applyRetrofit()
    applyOkhttp()
    applyMoshi()

    implementation(Deps.sqlite)
    implementation(Deps.sqlcipher)
}
