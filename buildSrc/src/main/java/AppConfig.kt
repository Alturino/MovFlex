import org.gradle.api.JavaVersion

object AppConfig {
    const val compileSdk = 33
    const val targetSdk = 33
    const val appNamespace = "com.onirutla.movflex"
    const val minSdk = 21
    const val versionCode = 1
    const val versionName = "1.0.0"
    const val testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    const val isReleaseMinifyEnabled = true
    const val isDebugMinifyEnabled = false
    val sourceCompatibility = JavaVersion.VERSION_17
    val targetCompatibility = sourceCompatibility
    val kotlinJvmTarget = "$sourceCompatibility"
}
