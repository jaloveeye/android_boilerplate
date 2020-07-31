import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    id("com.android.application")
    kotlin("android")
    kotlin("android.extensions")
    kotlin("kapt")
    id("androidx.navigation.safeargs")
    id("com.jakewharton.butterknife")
//    id("com.google.gms.google-services")
    id("kotlin-android")
}

android {

    compileSdkVersion(29)
    buildToolsVersion = "29.0.2"

    defaultConfig {
        applicationId = "com.herace.android_boilerplate"
        minSdkVersion(23)
        targetSdkVersion(29)
        versionCode = 1
        versionName = "0.0.1"
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables.useSupportLibrary = true
        multiDexEnabled = true
    }

    buildTypes {
        getByName("debug") {
            splits.density.isEnable = false
            aaptOptions.cruncherEnabled = false
            (this as ExtensionAware).extra["alwaysUpdateBuildId"] = false
        }

        getByName("release") {
            aaptOptions {
                cruncherEnabled = false
            }

//            isShrinkResources = true
            isMinifyEnabled = false
            proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
        }
    }

    kapt {
        correctErrorTypes = true
    }

    sourceSets {
        getByName("main").jniLibs.srcDirs("libs")
    }

    packagingOptions {
        exclude("LICENSE.txt")
    }

    compileOptions {
        setSourceCompatibility(JavaVersion.VERSION_1_8)
        setTargetCompatibility(JavaVersion.VERSION_1_8)
    }

    kotlinOptions.jvmTarget = JavaVersion.VERSION_1_8.toString()

    lintOptions {
        isAbortOnError = true
        disable("GoogleAppIndexingWarning")
    }

    testOptions {
        unitTests.isReturnDefaultValues = true
        unitTests.isIncludeAndroidResources = true
    }

    dataBinding {
        isEnabled = true
    }

    androidExtensions {
        isExperimental = true
    }

    useLibrary("android.test.runner")
    useLibrary("android.test.base")
    useLibrary("android.test.mock")
}

dependencies {

    /**
     * default
     */
    implementation(fileTree(mapOf("dir" to "libs", "include" to listOf("*.jar"))))
    implementation("androidx.appcompat:appcompat:_")
    implementation("androidx.core:core-ktx:_")
    implementation("androidx.constraintlayout:constraintlayout:_")
    implementation("androidx.viewpager:viewpager:1.0.0")
    androidTestImplementation("androidx.test.ext:junit:_")
    implementation("com.google.android.material:material:_")
    implementation("androidx.legacy:legacy-support-v4:_")


    /**
     * Support Libraries
     */
    implementation("androidx.recyclerview:recyclerview:_")
    implementation("androidx.legacy:legacy-support-v4:_")
    implementation("com.amitshekhar.android:rx2-android-networking:_")


    /**
     * Architecture components
     */
    implementation("androidx.lifecycle:lifecycle-runtime:_")
    implementation("androidx.lifecycle:lifecycle-extensions:_")
    implementation("androidx.room:room-runtime:_")
    implementation("androidx.room:room-rxjava2:_")
    implementation("androidx.paging:paging-runtime-ktx:_")


    /**
     * Compiler
     */
    implementation("androidx.appcompat:appcompat:_")
    kapt("androidx.lifecycle:lifecycle-compiler:_")
    kapt("androidx.room:room-compiler:_")


    /**
     * RxJava
     */
    implementation("io.reactivex.rxjava2:rxjava:_")
    implementation("io.reactivex.rxjava2:rxandroid:_")


    /**
     * Kotlin
     */
    implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8:1.3.41")
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-android:_")
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:_")


    /**
     * Koin
     */
    implementation("org.koin:koin-core:_")
    implementation("org.koin:koin-android:_")
    implementation("org.koin:koin-androidx-scope:_")
    implementation("org.koin:koin-androidx-viewmodel:_")
    implementation("org.koin:koin-androidx-fragment:_")
    implementation("org.koin:koin-test:_")


    /**
     * Testing
     */
    androidTestImplementation("androidx.test.espresso:espresso-core:_")
    debugImplementation("androidx.fragment:fragment-testing:_")
    testImplementation("junit:junit:_")
    androidTestImplementation("org.mockito:mockito-android:_")
    testImplementation("org.mockito:mockito-inline:_")
    testImplementation("androidx.test:core:_")
    testImplementation("org.mockito:mockito-core:_")
    androidTestImplementation("androidx.test:runner:_")
    androidTestImplementation("androidx.test:rules:_")
    androidTestImplementation("androidx.test:core:_")


    /**
     * Retrofit
     */
    implementation("com.squareup.retrofit2:retrofit:_")
    implementation("com.squareup.retrofit2:adapter-rxjava2:_")
    implementation("com.squareup.retrofit2:converter-gson:_")
    implementation("com.squareup.retrofit2:retrofit-mock:_")


    /**
     * OkHttp
     */
    implementation("com.squareup.okhttp3:logging-interceptor:_")
    implementation("com.squareup.okhttp3:okhttp:_")


    /**
     * fragment
     */
    implementation("androidx.fragment:fragment-ktx:_")


    /**
     * lifecycle
     */
    implementation("androidx.lifecycle:lifecycle-extensions:_")
    implementation("androidx.lifecycle:lifecycle-livedata-ktx:_")
    implementation("androidx.lifecycle:lifecycle-viewmodel-ktx:_")


    /**
     * navigation
     */
    implementation("androidx.navigation:navigation-fragment-ktx:_")
    implementation("androidx.navigation:navigation-ui-ktx:_")


    /**
     * Timber
     */
    implementation("com.jakewharton.timber:timber:_")


    /**
     * Picasso
     */
    implementation("com.squareup.picasso:picasso:_")


    /**
     * Airbnb Epoxy
     */
    implementation("com.airbnb.android:epoxy:_")
    implementation("com.airbnb.android:epoxy-databinding:_")
    implementation("com.airbnb.android:epoxy-paging:_")
    kapt("com.airbnb.android:epoxy-processor:_")


    /**
     * Proteus
     */
    implementation("com.github.flipkart-incubator.proteus:proteus-core:_")
    implementation("com.github.flipkart-incubator.proteus:gson-adapter:_")
    implementation("com.github.flipkart-incubator.proteus:cardview-v7:_")
    implementation("com.github.flipkart-incubator.proteus:design:_")
    implementation("com.github.flipkart-incubator.proteus:recyclerview-v7:_")
    implementation("com.github.flipkart-incubator.proteus:support-v4:_")


    /**
     * Glide
     */
    implementation("com.github.bumptech.glide:glide:_")
    kapt("android.arch.lifecycle:compiler:_")
    kapt("com.github.bumptech.glide:compiler:_")


    /**
     * Matisse
     */
    implementation("com.zhihu.android:matisse:_")
    implementation("com.github.tbruyelle:rxpermissions:_")


    /**
     * View Pager 2.0
     */
    implementation("androidx.viewpager2:viewpager2:_")


    /**
     * ETC
     */
    implementation("com.kyleduo.switchbutton:library:_")


    /**
     * FireBase
     */
//    implementation("com.google.firebase:firebase-core:_")
//    implementation("com.google.firebase:firebase-config:_")
//    implementation("com.google.firebase:firebase-firebase:_")
//    implementation("com.google.firebase:firebase-dynamic-links:_")
//    implementation("com.google.firebase:firebase-analytics:_")

    /**
     * Splitties
     */
    implementation("com.louiscad.splitties:splitties-activities:_")
    implementation("com.louiscad.splitties:splitties-toast:_")


    /**
     * Naver SDK
     */
    implementation(fileTree(mapOf("dir" to "libs", "include" to listOf("*.aar"))))
    implementation("com.android.support:appcompat-v7:28.0.0")
    implementation("com.android.support:support-core-utils:28.0.0")
    implementation("com.android.support:customtabs:28.0.0")
    implementation("com.android.support:support-v4:28.0.0")

    /**
     * Kakao
     */
    implementation("com.kakao.sdk:usermgmt:1.23.0")
    implementation("com.kakao.sdk:plusfriend:_")


    /**
     * Facebook
     */
    implementation("com.facebook.android:facebook-android-sdk:[4,5)")


    /**
     * Switch Button : https://github.com/zcweng/SwitchButton
     */
    implementation("com.github.zcweng:switch-button:0.0.3@aar")


    /**
     * Circle ImageView
     * https://github.com/hdodenhof/CircleImageView
     */
    implementation("de.hdodenhof:circleimageview:_")


    /**
     * Image View
     * https://github.com/stfalcon-studio/StfalconImageViewer
     */
    implementation("com.github.stfalcon:stfalcon-imageviewer:_")
    implementation("com.github.stfalcon:frescoimageviewer:_")
    implementation("com.facebook.fresco:fresco:_")

    implementation("com.klinkerapps:link_builder:_")
}