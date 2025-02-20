import java.util.Properties

plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    id("kotlin-kapt")
    id("com.google.dagger.hilt.android")
}

val properties = Properties()
properties.load(File(rootProject.rootDir, "local.properties").inputStream())
val TASTEBUDS_BASE_URL: String = properties.getProperty("TASTEBUDS_URL", "")
val INGREDIENTS_BASE_URL: String = properties.getProperty("INGREDIENTS_URL", "")

android {
    namespace = "com.example.tastebuds"
    compileSdk = 35

    defaultConfig {
        applicationId = "com.example.tastebuds"
        minSdk = 24
        targetSdk = 35
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        testInstrumentationRunner = "com.example.tastebuds.utils.CustomTestRunner"

    }

    buildTypes {
        debug {
            buildConfigField("String", "TASTEBUDS_BASE_URL", "\"$TASTEBUDS_BASE_URL\"")
            buildConfigField("String", "INGREDIENTS_BASE_URL", "\"$INGREDIENTS_BASE_URL\"")
        }
        release {
            buildConfigField("String", "TASTEBUDS_BASE_URL", "\"$TASTEBUDS_BASE_URL\"")
            buildConfigField("String", "INGREDIENTS_BASE_URL", "\"$INGREDIENTS_BASE_URL\"")
            isMinifyEnabled = true
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
            isShrinkResources = true
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
        buildConfig = true
        viewBinding = true
    }
}

dependencies {

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)
    implementation(libs.androidx.activity)
    implementation(libs.androidx.constraintlayout)

    // junit
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.runner)
    androidTestImplementation(libs.androidx.rules)

    // mockito espresso
    testImplementation(libs.androidx.core)
    testImplementation(libs.androidx.mockito.kotlin)
    testImplementation(libs.androidx.mockito.inline)
    testImplementation(libs.androidx.coroutines.test)
    testImplementation(libs.androidx.mockito.core)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(libs.androidx.espresso.contrib)
    androidTestImplementation(libs.androidx.espresso.idling)

    // Dagger Hilt
    implementation(libs.hilt.android)
    kapt(libs.hilt.compiler)

    // Viewmodel livedata
    implementation(libs.androidx.lifecycle.livedata.ktx)
    implementation(libs.androidx.lifecycle.viewmodel.ktx)
    implementation(libs.androidx.lifecycle.runtime.ktx)

    // Retrofit
    implementation(libs.androidx.retrofit2)
    implementation(libs.androidx.converter.gson)
    implementation(libs.androidx.logging.interceptor)

    // Glide for image loading
    implementation(libs.androidx.glide)
    annotationProcessor(libs.androidx.glide.compiler)

}
