import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    kotlin("jvm") version "1.6.20" apply false
}

allprojects {
    group = "io.github.nickacpt"
    version = "1.0-SNAPSHOT"

    repositories {
        mavenCentral()
    }
}

subprojects {

    apply(plugin = "org.jetbrains.kotlin.jvm")

    tasks.withType<KotlinCompile> {
        kotlinOptions.jvmTarget = "18"
    }
}