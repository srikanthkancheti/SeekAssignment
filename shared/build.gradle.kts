plugins {
    kotlin("multiplatform")
    kotlin("plugin.serialization")
    id("com.android.library")
}

android {
    namespace = "com.seek.assignment"
    compileSdk = Versions.compile_sdk
    sourceSets["main"].manifest.srcFile("src/androidMain/AndroidManifest.xml")
    defaultConfig {
        minSdk = Versions.min_sdk
        targetSdk = Versions.compile_sdk
    }
}

kotlin {
    android {
        compilations.all {
            kotlinOptions {
                jvmTarget = "1.8"
            }
        }
    }

//    listOf(
//        iosX64(),
//        iosArm64(),
//        iosSimulatorArm64()
//    ).forEach {
//        it.binaries.framework {
//            baseName = "shared"
//        }
//    }

    sourceSets {
        @Suppress("UNUSED_VARIABLE") val commonMain by getting {
            dependencies {
                implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:${Versions.coroutines}")
                implementation("org.jetbrains.kotlinx:kotlinx-serialization-json:1.3.3")
                implementation("org.jetbrains.kotlinx:kotlinx-datetime:0.4.0")
                implementation("io.ktor:ktor-client-core:${Versions.ktor}")
                implementation("io.ktor:ktor-client-json:${Versions.ktor}")
                implementation("io.ktor:ktor-client-logging:${Versions.ktor}")
                implementation("io.ktor:ktor-serialization-kotlinx-json:${Versions.ktor}")
                implementation("io.ktor:ktor-client-content-negotiation:${Versions.ktor}")
                implementation("io.ktor:ktor-client-websockets:${Versions.ktor}")
                implementation("io.insert-koin:koin-core:${Versions.koin}")
                implementation("com.russhwolf:multiplatform-settings:${Versions.russhwolf}")
            }
        }
        @Suppress("UNUSED_VARIABLE") val commonTest by getting {
            dependencies {
                implementation(kotlin("test"))
            }
        }
        @Suppress("UNUSED_VARIABLE") val androidMain by getting {
            dependencies{
                implementation(project.dependencies.platform("androidx.compose:compose-bom:${Versions.composeBillOfMaterials}"))
                implementation("io.ktor:ktor-client-android:${Versions.ktor}")
                implementation("io.ktor:ktor-network-tls:${Versions.ktor}")
                implementation("com.squareup.okhttp3:okhttp:4.10.0")
                implementation("androidx.core:core:1.9.0")
                implementation("androidx.preference:preference:1.2.0")
                implementation("androidx.compose.ui:ui")
            }
        }
        @Suppress("UNUSED_VARIABLE") val androidUnitTest by getting

//        @Suppress("UNUSED_VARIABLE") val iosX64Main by getting
//        @Suppress("UNUSED_VARIABLE") val iosArm64Main by getting
//        @Suppress("UNUSED_VARIABLE") val iosSimulatorArm64Main by getting
//        @Suppress("UNUSED_VARIABLE") val iosMain by creating {
//            dependsOn(commonMain)
//            iosX64Main.dependsOn(this)
//            iosArm64Main.dependsOn(this)
//            iosSimulatorArm64Main.dependsOn(this)
//            dependencies {
//                implementation("io.ktor:ktor-client-darwin:${Versions.ktor}")
//            }
//        }
    }
}
