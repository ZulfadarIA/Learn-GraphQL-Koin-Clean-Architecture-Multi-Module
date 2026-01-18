plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.apollo)
    alias(libs.plugins.kotlin.compose)
}

android {
    namespace = "com.zulfadar.core"
    compileSdk {
        version = release(36)
    }

    defaultConfig {
        applicationId = "com.zulfadar.learngraphqlwithcleanarchitectureandkoin"
        minSdk = 30
        targetSdk = 36
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
    kotlinOptions {
        jvmTarget = "11"
    }
    buildFeatures {
        compose = true
    }
}

dependencies {
    implementation(project(":core-data"))
    implementation(project(":core-domain"))
    implementation(project(":core-network"))
    implementation(project(":core-storage"))
    implementation(project(":core-ui"))
    implementation(project(":feature-launch"))
    implementation(project(":feature-launchdetail"))
    implementation(project(":feature-login"))

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(libs.androidx.activity.compose)
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.compose.ui)
    implementation(libs.androidx.compose.ui.graphics)
    implementation(libs.androidx.compose.ui.tooling.preview)
    implementation(libs.androidx.compose.material3)
    implementation(libs.androidx.navigation.compose)
    implementation(libs.androidx.security.crypto)
    implementation(libs.coil.compose)
    implementation(libs.androidx.lifecycle.viewmodel.compose)

    // Koin for Android
    implementation(libs.koin.android)
    // Koin for Jetpack Compose
    implementation(libs.koin.androidx.compose)

    //apollo-runtime
    implementation(libs.apollo.runtime)
    implementation(libs.okhttp.logging.interceptor)
    implementation(libs.navigation.compose)
    implementation(libs.androidx.material3)

    //Paging3
    implementation(libs.androidx.paging.runtime)
    implementation(libs.androidx.paging.compose)

    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(platform(libs.androidx.compose.bom))
    androidTestImplementation(libs.androidx.compose.ui.test.junit4)
    debugImplementation(libs.androidx.compose.ui.tooling)
    debugImplementation(libs.androidx.compose.ui.test.manifest)
}