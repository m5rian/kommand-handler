import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    kotlin("jvm") version "1.5.10"
}

group = "com.github.m5rian.kommandHandler"
version = "development"

repositories {
    mavenCentral()
    maven(url = "https://m2.dv8tion.net/releases")
}
dependencies {
    api("net.dv8tion:JDA:4.3.0_315")
    implementation(kotlin("reflect"))
    implementation("org.reflections:reflections:0.9.11")
}


tasks.withType<KotlinCompile>() {
    kotlinOptions.jvmTarget = "13"
}