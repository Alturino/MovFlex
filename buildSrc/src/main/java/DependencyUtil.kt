import org.gradle.api.artifacts.dsl.DependencyHandler

private fun DependencyHandler.implementation(dependency: String) {
    add("implementation", dependency)
}

private fun DependencyHandler.testImplementation(dependency: String) {
    add("testImplementation", dependency)
}

private fun DependencyHandler.androidTestImplementation(dependency: String) {
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

private fun DependencyHandler.debugImplementation(dependency: String) {
    add("debugImplementation", dependency)
}

fun DependencyHandler.applyRoom() {
    implementation(Dependency.Room.ktx)
    api(Dependency.Room.runtime)
    implementation(Dependency.Room.paging)
    kapt(Dependency.Room.compiler)
    testImplementation(Dependency.Room.testing)
}

fun DependencyHandler.applyHilt() {
    implementation(Dependency.Hilt.android)
    kapt(Dependency.Hilt.compiler)
    kapt(Dependency.Hilt.hiltCompiler)
    kaptAndroidTest(Dependency.Hilt.hiltCompiler)
}

fun DependencyHandler.applyPaging() {
    implementation(Dependency.Paging.runtime)
    testImplementation(Dependency.Paging.common)
}

fun DependencyHandler.applyNavigation() {
    implementation(Dependency.Navigation.ui)
    implementation(Dependency.Navigation.fragmentKtx)
    implementation(Dependency.Navigation.dynamicFeature)
}

fun DependencyHandler.applyTest() {
    testImplementation(Dependency.Test.junit)
    androidTestImplementation(Dependency.Test.androidxJunit)
    androidTestImplementation(Dependency.Test.espresso)
    debugImplementation(Dependency.Test.leakCanary)
}

fun DependencyHandler.applyAndroid() {
    implementation(Dependency.Android.appcompat)
    implementation(Dependency.Android.coreKtx)
    implementation(Dependency.Android.constraintLayout)
    implementation(Dependency.Android.recyclerView)
    implementation(Dependency.Android.material)
}

fun DependencyHandler.applyRetrofit() {
    implementation(Dependency.Retrofit.retrofit)
    implementation(Dependency.Retrofit.converterMoshi)
}

fun DependencyHandler.applyOkhttp() {
    implementation(Dependency.Okhttp.bom)
    implementation(Dependency.Okhttp.loggingInterceptor)
}

fun DependencyHandler.applyMoshi() {
    implementation(Dependency.Moshi.moshi)
    implementation(Dependency.Moshi.kotlin)
    kapt(Dependency.Moshi.codegen)
}

fun DependencyHandler.applyLifecycle() {
    implementation(Dependency.Lifecycle.livedata)
    implementation(Dependency.Lifecycle.viewmodel)
    implementation(Dependency.Lifecycle.runtime)
}

fun DependencyHandler.applyShared() {
    applyAndroid()
    applyNavigation()
    applyPaging()
    applyHilt()
    applyLifecycle()
    applyTest()

    implementation(Dependency.glide)
    implementation(Dependency.timber)
}
