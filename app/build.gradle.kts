plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.jetbrains.kotlin.android)
    alias(libs.plugins.hilt)
    alias(libs.plugins.ksp)
    id("kotlin-parcelize")
   // id("dagger.hilt.android.plugin")
}

android {
    namespace = "com.example.moviesapp"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.example.moviesapp"
        minSdk = 24
        targetSdk = 34
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
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
    buildFeatures {
        viewBinding = true
    }
}

dependencies {

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)
    implementation(libs.androidx.activity)
    implementation(libs.androidx.constraintlayout)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)

    // coroutines and permissions
    implementation(libs.androidx.fragment.ktx)
    implementation(libs.androidx.activity.ktx)
    implementation(libs.androidx.lifecycle.viewmodel.ktx)

    // retrofit
    implementation(libs.retrofit)
    implementation(libs.converter.gson)

    // Picasso
    implementation(libs.picasso)

    // DataStore
    implementation(libs.androidx.datastore.preferences)

    // Glide
    implementation(libs.glide)
    annotationProcessor(libs.compiler)

    // library from play services
    implementation(libs.play.services.location)

    //DaggerHilt
    implementation(libs.hilt.android)
    ksp(libs.hilt.compiler)
    implementation(kotlin("script-runtime"))
}