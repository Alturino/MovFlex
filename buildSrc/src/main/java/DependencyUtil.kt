import org.gradle.api.artifacts.Dependency
import org.gradle.api.artifacts.dsl.DependencyHandler

private fun DependencyHandler.implementation(dependency: String) {
    add("implementation", dependency)
}

private fun DependencyHandler.implementation(dependency: Dependency) {
    add("implementation", dependency)
}

private fun DependencyHandler.testImplementation(dependency: String) {
    add("testImplementation", dependency)
}

private fun DependencyHandler.androidTestImplementation(dependency: String) {
    add("androidTestImplementation", dependency)
}

private fun DependencyHandler.androidTestImplementation(dependency: Dependency) {
    add("androidTestImplementation", dependency)
}

private fun DependencyHandler.api(dependency: String) {
    add("api", dependency)
}

private fun DependencyHandler.kapt(dependency: String) {
    add("kapt", dependency)
}


private fun DependencyHandler.kaptAndroidTest(dependency: String) {
    add("kaptAndroidTest", dependency)
}

private fun DependencyHandler.kaptTest(dependency: String) {
    add("kaptTest", dependency)
}

private fun DependencyHandler.debugImplementation(dependency: String) {
    add("debugImplementation", dependency)
}

fun DependencyHandler.applyRoom() {
    implementation(Deps.Room.ktx)
    api(Deps.Room.runtime)
    implementation(Deps.Room.paging)
    kapt(Deps.Room.compiler)
    testImplementation(Deps.Room.testing)
}

fun DependencyHandler.applyHilt() {
    implementation(Deps.Hilt.android)
    kapt(Deps.Hilt.compiler)

    androidTestImplementation(Deps.Hilt.hiltTest)
    kaptAndroidTest(Deps.Hilt.compiler)

    testImplementation(Deps.Hilt.hiltTest)
    kaptTest(Deps.Hilt.compiler)

    kapt(Deps.Hilt.androidxCompiler)
    kaptAndroidTest(Deps.Hilt.androidxCompiler)
}

fun DependencyHandler.applyPaging() {
    implementation(Deps.Paging.runtime)
    testImplementation(Deps.Paging.common)
}

fun DependencyHandler.applyNavigation() {
    implementation(Deps.Navigation.ui)
    implementation(Deps.Navigation.fragmentKtx)
    implementation(Deps.Navigation.dynamicFeature)
}

fun DependencyHandler.applyTest() {
    testImplementation(Deps.Test.junit)
    androidTestImplementation(Deps.Test.androidxJunit)
    androidTestImplementation(Deps.Test.espresso)
    debugImplementation(Deps.Test.leakCanary)
}

fun DependencyHandler.applyAndroid() {
    implementation(Deps.Android.appcompat)
    implementation(Deps.Android.coreKtx)
    implementation(Deps.Android.constraintLayout)
    implementation(Deps.Android.recyclerView)
    implementation(Deps.Android.material)
}

fun DependencyHandler.applyRetrofit() {
    implementation(Deps.Retrofit.retrofit)
    implementation(Deps.Retrofit.converterMoshi)
}

fun DependencyHandler.applyOkhttp() {
    implementation(Deps.Okhttp.bom)
    implementation(Deps.Okhttp.loggingInterceptor)
}

fun DependencyHandler.applyMoshi() {
    implementation(Deps.Moshi.moshi)
    implementation(Deps.Moshi.kotlin)
    kapt(Deps.Moshi.codegen)
}

fun DependencyHandler.applyLifecycle() {
    implementation(Deps.Lifecycle.livedata)
    implementation(Deps.Lifecycle.viewmodel)
    implementation(Deps.Lifecycle.runtime)
}

fun DependencyHandler.applyCompose() {
    val composeBom = platform(Deps.Compose.bom)
    implementation(composeBom)
    androidTestImplementation(composeBom)

    // UI
    implementation(Deps.Compose.material3)
    implementation(Deps.Compose.materialIconsExtended)
    implementation(Deps.Compose.foundation)
    implementation(Deps.Compose.ui)
    implementation(Deps.Compose.windowSize)

    // Preview
    debugImplementation(Deps.Compose.tooling)
    implementation(Deps.Compose.preview)

    // UI Test
    androidTestImplementation(Deps.Compose.uiTestJunit)
    debugImplementation(Deps.Compose.manifest)

    implementation(Deps.Compose.activity)
    implementation(Deps.Compose.viewModel)
    implementation(Deps.Compose.liveData)
    implementation(Deps.Compose.hilt)
}

fun DependencyHandler.applyCoil() {
    implementation(Deps.Coil.coil)
    implementation(Deps.Coil.compose)
}

fun DependencyHandler.applyShared() {
    applyAndroid()
    applyNavigation()
    applyPaging()
    applyHilt()
    applyLifecycle()
    applyTest()
    applyCompose()
    applyCoil()

    implementation(Deps.timber)
}
