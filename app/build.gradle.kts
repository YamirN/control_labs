plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
}

android {
    namespace = "com.example.control"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.example.control"
        minSdk = 26
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
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
    kotlinOptions {
        jvmTarget = "11"
    }
}

dependencies {
    // Firebase Core (necesario para cualquier servicio de Firebase)
    implementation(platform("com.google.firebase:firebase-bom:33.6.0"))

    implementation ("com.google.firebase:firebase-analytics")

    // Firebase Firestore
    implementation ("com.google.firebase:firebase-firestore")
    implementation(libs.androidx.core.ktx)
    implementation ("androidx.lifecycle:lifecycle-runtime-ktx:2.6.2")
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)
    implementation(libs.androidx.activity)
    implementation(libs.androidx.constraintlayout)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
}