plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)

    id("com.google.devtools.ksp")
    id("com.google.dagger.hilt.android")
    id("androidx.navigation.safeargs.kotlin")
    id("kotlin-kapt")
    id("kotlin-parcelize")
}

android {
    namespace = "com.git.populargitrepos"
    compileSdk = 35

    defaultConfig {
        applicationId = "com.git.populargitrepos"
        minSdk = 24
        targetSdk = 35
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
            signingConfig = signingConfigs.getByName("debug")
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
    kotlinOptions {
        jvmTarget = "17"
    }

    //todo enable data binding and view binding
    buildFeatures.apply {
        this.dataBinding = true
        this.viewBinding = true
        this.buildConfig = true
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

    //todo add room database
    implementation(libs.androidx.room.runtime)
    implementation(libs.room.ktx)
    ksp(libs.androidx.room.compiler)

    //todo network clint by retrofit
    implementation(libs.retrofit)
    implementation(libs.converter.gson)
    implementation(libs.logging.interceptor)

    //todo image loading library
    implementation(libs.coil)

    //todo dependency injection by dagger hilt
    implementation(libs.hilt.android)
    ksp(libs.hilt.android.compiler)

    //todo paging 3 android dependency
    implementation(libs.androidx.paging.runtime)

    //todo navigation
    implementation(libs.androidx.navigation.fragment.ktx)
    implementation(libs.androidx.navigation.ui.ktx)

    //todo flexbox for recylerview
    implementation (libs.flexbox)




    //todo testing -----------------------------------------------------------------------
    //todo testing core-testing
    testImplementation(libs.androidx.core.testing)
    androidTestImplementation(libs.androidx.core.testing)

    //todo coroutines test
    testImplementation (libs.kotlinx.coroutines.test)
    androidTestImplementation (libs.kotlinx.coroutines.test)

    //todo hilt testing
    testImplementation(libs.hilt.android.testing)
    kspAndroidTest(libs.hilt.android.compiler)
    kspAndroidTest(libs.dagger.hilt.compiler.v255)
    kspAndroidTest(libs.dagger.hilt.compiler.v255)

    testImplementation(libs.hilt.android.testing.v250)
    kspTest(libs.com.google.dagger.hilt.compiler2)

    //todo test runner
    androidTestImplementation(libs.androidx.runner)
    testImplementation(libs.androidx.runner)

    //todo test core
    androidTestImplementation(libs.core.ktx)
}