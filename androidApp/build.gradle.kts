plugins {
    id("com.android.application")
    kotlin("android")
    id("kotlin-android")
}

android {
    namespace = "com.seek.assignment.android"
    compileSdk = Versions.compile_sdk
    defaultConfig {
        applicationId = "com.seek.assignment.android"
        minSdk = Versions.min_sdk
        targetSdk = Versions.compile_sdk

        versionCode = Versions.commitNumber
        versionName = Versions.marketingVersion
    }
    buildFeatures {
        viewBinding = true
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = Versions.compose_compiler
    }
    compileOptions {
        isCoreLibraryDesugaringEnabled = true
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    packagingOptions {
        // Multiple dependency bring these files in. Exclude them to enable
        // our test APK to build (has no effect on our AARs)
        resources.excludes += "/META-INF/AL2.0"
        resources.excludes += "/META-INF/LGPL2.1"
    }
    buildTypes {
        getByName("release") {
            isMinifyEnabled = true
            isShrinkResources = true
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }

        getByName("debug"){
            applicationIdSuffix = ".enterprise"
            isDebuggable = true
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
    lint{
        abortOnError = false
    }
}

repositories {
    maven { url = uri( "https://jitpack.io") }
    maven { url = uri("https://oss.sonatype.org/content/repositories/snapshots/") }
    mavenCentral()
}

dependencies {
    implementation(project(":shared"))
    coreLibraryDesugaring("com.android.tools:desugar_jdk_libs:2.0.2")

    // Core
    implementation("com.google.android.material:material:1.8.0")
    implementation("androidx.appcompat:appcompat:1.6.1")
    implementation("androidx.activity:activity-compose:1.6.1")
    implementation("androidx.core:core-ktx:1.9.0")
    implementation("org.jetbrains.kotlinx:kotlinx-datetime:0.4.0")
    implementation("com.google.android.gms:play-services-location:21.0.1")

    // Compose
    implementation(platform("androidx.compose:compose-bom:${Versions.composeBillOfMaterials}"))
    implementation("androidx.compose.foundation:foundation:1.3.1")
    implementation("androidx.compose.ui:ui")
    implementation("androidx.compose.ui:ui-tooling")
    implementation("androidx.compose.ui:ui-tooling-preview")
    implementation("androidx.compose.ui:ui-util")
    implementation("androidx.compose.foundation:foundation")
    implementation("androidx.compose.material:material")
    implementation("androidx.constraintlayout:constraintlayout:${Versions.constraintLayout}")
    implementation("androidx.constraintlayout:constraintlayout-compose:${Versions.constraintLayoutCompose}")

    // Navigation
    implementation("androidx.navigation:navigation-compose:2.5.3")
    implementation("com.google.accompanist:accompanist-navigation-animation:${Versions.accompanist}")

    // KTOR
    implementation("io.ktor:ktor-client-android:${Versions.ktor}")

    // LiveData
    implementation("androidx.lifecycle:lifecycle-livedata-ktx:2.5.1")

    // LifeCycle
    implementation("androidx.lifecycle:lifecycle-runtime-ktx:2.5.1")
    implementation("androidx.lifecycle:lifecycle-viewmodel-compose:2.5.1")

    // DI
    implementation("io.insert-koin:koin-core:${Versions.koin}")
    implementation("io.insert-koin:koin-android:${Versions.koin}")
    implementation("io.insert-koin:koin-androidx-compose:${Versions.koin}")

    // UI Components
    implementation("com.google.accompanist:accompanist-pager:0.18.0") // ToDo: DL 2021-11-10 There is 0.20.2 but it cannot be updated since pager needs count parameter. Devs should look into it.
    implementation("com.google.accompanist:accompanist-pager-indicators:0.18.0") // ToDo: DL 2021-11-10 There is 0.20.2 but it cannot be updated since pager needs count parameter. Devs should look into it.
    implementation("com.google.accompanist:accompanist-insets:${Versions.accompanist}")
    implementation("com.google.accompanist:accompanist-insets-ui:${Versions.accompanist}")
    implementation("com.google.accompanist:accompanist-systemuicontroller:${Versions.accompanist}")
    implementation("com.google.accompanist:accompanist-permissions:${Versions.accompanist}")
    implementation("com.google.accompanist:accompanist-swiperefresh:${Versions.accompanist}")
    implementation ("androidx.core:core-splashscreen:1.0.0")
    implementation ("com.airbnb.android:lottie-compose:${Versions.lottie}")
    implementation("org.burnoutcrew.composereorderable:reorderable:0.9.6")
    implementation("com.google.android.play:review:2.0.1")
    implementation("com.google.android.play:review-ktx:2.0.1")
}