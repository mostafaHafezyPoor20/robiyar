plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
}

android {
    namespace = "mostafa.hafezypoor.robiyar"
    compileSdk {
        version = release(36)
    }

    defaultConfig {
        applicationId = "mostafa.hafezypoor.robiyar"
        minSdk = 24
        targetSdk = 36
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        val marketApplicationId = "ir.mservices.market"
        val marketBindAddress = "ir.mservices.market.InAppBillingService.BIND"
        manifestPlaceholders.apply {
            this["marketApplicationId"] = marketApplicationId
            this["marketBindAddress"] = marketBindAddress
            this["marketPermission"] = "${marketApplicationId}.BILLING"
        }
        android.buildFeatures.buildConfig = true
        buildConfigField(
            "String",
            "IAB_PUBLIC_KEY",
            "\"MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQC4zODgGr0A3ILzigskOljmxAgQLMZ8jlFJc8BVWlMSf8Ctb7mApId/tDIBGAv1jwTrpHsFlMiP6a8HlObm07YII5+nUx1aS56fBq6MbVdoj4JZCRFWPpfh1tpII73F4HY1gq2k8Q8Kb2uK/4reIwryCflPHHJJ7/Kyb1i1HKzxVQIDAQAB\""
        )
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
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)
    implementation(libs.androidx.activity)
    implementation(libs.androidx.constraintlayout)
    implementation(libs.lottie)
    implementation(libs.retrofit)
    implementation(libs.converter.gson)
    implementation(libs.androidx.lifecycle.viewmodel.ktx)
    implementation(libs.androidx.fragment.ktx)
    implementation(libs.curved.bottom.navigation)
    implementation(libs.myket.billing.client)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
}