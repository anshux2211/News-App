plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
}

android {
    namespace = "com.example.newsapp"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.example.newsapp"
        minSdk = 28
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
}

dependencies {

    implementation("androidx.core:core-ktx:1.13.1")
    implementation("androidx.appcompat:appcompat:1.7.0")
    implementation("com.google.android.material:material:1.12.0")
    implementation("androidx.constraintlayout:constraintlayout:2.1.4")
    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.2.1")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.6.1")

            implementation ("com.squareup.retrofit2:retrofit:2.9.0")
            implementation ("com.squareup.retrofit2:converter-gson:2.9.0")

            implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.6.4")
            implementation("org.jetbrains.kotlinx:kotlinx-coroutines-android:1.7.3")

            implementation("androidx.lifecycle:lifecycle-livedata-ktx:2.8.6")
            implementation("androidx.lifecycle:lifecycle-viewmodel-ktx:2.8.6")

            implementation("androidx.lifecycle:lifecycle-runtime-ktx:2.8.6")
            implementation("androidx.lifecycle:lifecycle-viewmodel-savedstate:2.8.6")

            implementation ("com.github.bumptech.glide:glide:4.15.1")
            annotationProcessor ("com.github.bumptech.glide:compiler:4.15.1")

            implementation ("com.jakewharton.retrofit:retrofit2-kotlin-coroutines-adapter:0.9.2")

            implementation ("androidx.recyclerview:recyclerview:1.3.2")
}