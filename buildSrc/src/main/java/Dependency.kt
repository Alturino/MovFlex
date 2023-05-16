object Dependency {
    object Android {
        const val appcompat = "androidx.appcompat:appcompat:${Versions.appcompat}"
        const val coreKtx = "androidx.core:core-ktx:${Versions.coreKtx}"
        const val constraintLayout = "androidx.constraintlayout:constraintlayout:${Versions.constraintLayout}"
        const val recyclerView = "androidx.recyclerview:recyclerview:${Versions.recyclerView}"
        const val material = "com.google.android.material:material:${Versions.material}"
    }

    object Room {
        private const val base = "androidx.room:room"
        const val runtime = "$base-runtime:${Versions.room}"
        const val ktx = "$base-ktx:${Versions.room}"
        const val paging = "$base-paging:${Versions.room}"
        const val testing = "$base-testing:${Versions.room}"
        const val compiler = "$base-compiler:${Versions.room}"
    }

    object Retrofit {
        private const val base = "com.squareup.retrofit2"
        const val retrofit = "$base:retrofit:${Versions.retrofit}"
        const val converterMoshi = "$base:converter-moshi:${Versions.retrofit}"
    }

    object Okhttp {
        private const val base = "com.squareup.okhttp3"
        const val bom = "$base:okhttp-bom:${Versions.okhttp}"
        const val loggingInterceptor = "$base:logging-interceptor:${Versions.okhttp}"
    }

    object Moshi {
        private const val base = "com.squareup.moshi"
        const val moshi = "$base:moshi:${Versions.moshi}"
        const val kotlin = "$base:moshi-kotlin:${Versions.moshi}"
        const val codegen = "$base:moshi-kotlin-codegen:${Versions.moshi}"
    }

    object Navigation {
        private const val base = "androidx.navigation:navigation"
        const val fragmentKtx = "$base-fragment-ktx:${Versions.nav}"
        const val ui = "$base-ui-ktx:${Versions.nav}"
        const val dynamicFeature = "$base-dynamic-features-fragment:${Versions.nav}"
    }

    object Lifecycle {
        private const val base = "androidx.lifecycle:lifecycle"
        const val livedata = "$base-livedata-ktx:${Versions.lifecycle}"
        const val viewmodel = "$base-viewmodel-ktx:${Versions.lifecycle}"
        const val runtime = "$base-runtime-ktx:${Versions.lifecycle}"
    }

    object Hilt {
        private const val base = "com.google.dagger:hilt-android"
        const val android = "$base:${Versions.hilt}"
        const val compiler = "$base-compiler:${Versions.hilt}"
        const val hiltCompiler = "androidx.hilt:hilt-compiler:${Versions.hiltJetpack}"
    }

    object Paging {
        private const val base = "androidx.paging:paging"
        const val runtime = "$base-runtime:${Versions.paging}"
        const val common = "$base-common:${Versions.paging}"
    }

    object Test {
        const val junit = "junit:junit:${Versions.junit}"
        const val androidxJunit = "androidx.test.ext:junit:${Versions.androidxJunit}"
        const val espresso = "androidx.test.espresso:espresso-core:${Versions.espresso}"
        const val leakCanary = "com.squareup.leakcanary:leakcanary-android:${Versions.leakCanary}"
    }

    const val sqlcipher = "net.zetetic:android-database-sqlcipher:${Versions.zeteticSqlCipher}"
    const val sqlite = "androidx.sqlite:sqlite-ktx:${Versions.sqlite}"

    const val timber = "com.jakewharton.timber:timber:${Versions.timber}"
    const val coil = "io.coil-kt:coil:${Versions.coil}"
}
