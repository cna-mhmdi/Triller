plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
    id("kotlin-kapt")
//    id("com.google.gms.google-services")
    id("kotlin-parcelize")
}

android {
    namespace = "com.cna.parde"
    compileSdk = 33

    defaultConfig {
        applicationId = "com.cna.parde"
        minSdk = 23
        targetSdk = 33
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    viewBinding {
        enable = true
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

    implementation("androidx.core:core-ktx:1.9.0")
    implementation("androidx.appcompat:appcompat:1.6.1")
    implementation("com.google.android.material:material:1.9.0")
    implementation("androidx.constraintlayout:constraintlayout:2.1.4")
    implementation("androidx.recyclerview:recyclerview:1.3.1")
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.8.1-Beta")
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-android:1.8.1-Beta")
    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.1.5")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")
    //retrofit
    implementation("com.squareup.retrofit2:retrofit:2.9.0")
    implementation("com.squareup.retrofit2:converter-moshi:2.9.0")
    //Coil
    implementation("io.coil-kt:coil:2.4.0")
    //Glide
    implementation("com.github.bumptech.glide:glide:4.15.1")
    //recyclerView
    implementation("androidx.recyclerview:recyclerview:1.3.1")
    //material design
    implementation("com.google.android.material:material:1.9.0")
    //coroutines
//    //Room
//    implementation("androidx.room:room-runtime:2.5.2")
//    kapt("androidx.room:room-compiler:2.5.2")
    //viewModel
    implementation("androidx.lifecycle:lifecycle-viewmodel-ktx:2.6.1")
    //liveData
    implementation("androidx.lifecycle:lifecycle-livedata-ktx:2.6.1")
//    //FireBase
//    implementation(platform("com.google.firebase:firebase-bom:32.2.2"))
//    implementation("com.google.firebase:firebase-analytics-ktx")
//    implementation("com.google.firebase:firebase-messaging")
    //Navigation View
    implementation("androidx.navigation:navigation-fragment-ktx:2.3.2")
    implementation("androidx.navigation:navigation-ui-ktx:2.3.2")

}