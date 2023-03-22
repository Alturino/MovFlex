object Plugin {
    // To be used in Project build.gradle.kts
    object Gradle {
        const val safeArg = "androidx.navigation.safeargs.kotlin:androidx.navigation.safeargs.kotlin.gradle.plugin:${Versions.nav}"
        const val mapsSecret = "com.google.android.libraries.mapsplatform.secrets-gradle-plugin:secrets-gradle-plugin:${Versions.mapsplaform_secrets}"
        const val daggerHilt = "com.google.dagger:hilt-android-gradle-plugin:${Versions.hilt}"
        const val kotlin = "org.jetbrains.kotlin:kotlin-gradle-plugin:${Versions.kotlin}"
    }
    // To be used in Module build.gradle.kts
    const val daggerHilt = "dagger.hilt.android.plugin"
    const val safeArg = "androidx.navigation.safeargs.kotlin"
    const val mapsSecret = "com.google.android.libraries.mapsplatform.secrets-gradle-plugin"
}
