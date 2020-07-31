// Top-level build file where you can add configuration options common to all sub-projects/modules.

buildscript {

    repositories {
        google()
        jcenter()
        mavenCentral()
        maven { url = uri("https://oss.sonatype.org/content/repositories/snapshots") }
        maven { url = uri("https://jitpack.io") }
        maven { url = uri("http://devrepo.kakao.com:8088/nexus/content/groups/public/") }
    }

    dependencies {
        classpath("com.android.tools.build:gradle:4.0.0")
        classpath("org.jetbrains.kotlin:kotlin-gradle-plugin:1.3.41")
        classpath("androidx.navigation:navigation-safe-args-gradle-plugin:2.1.0-alpha04")
        classpath("com.jakewharton:butterknife-gradle-plugin:10.1.0")
//        classpath("com.google.gms:google-services:4.3.3")
    }
}

allprojects{
    repositories {
        google()
        jcenter()
        mavenCentral()
        maven { url = uri("https://oss.sonatype.org/content/repositories/snapshots") }
        maven { url = uri("https://jitpack.io") }
    }
}

subprojects {
    repositories {
        mavenCentral()
        maven { url = uri("http://devrepo.kakao.com:8088/nexus/content/groups/public/") }
    }
}

tasks {
    val clean by registering(Delete::class) {
        delete(buildDir)
    }
}
