import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    kotlin("jvm") version "1.5.30"
    `maven-publish`
}

group = "com.github.m5rian"
val id = "Kommand-handler"
version = "development_test"

repositories {
    mavenCentral()
    maven(url = "https://m2.dv8tion.net/releases")
}
dependencies {
    api("net.dv8tion:JDA:4.3.0_315")
    implementation(kotlin("reflect"))
    implementation("org.reflections:reflections:0.9.11")
    val kotlinxGroup = "org.jetbrains.kotlinx"
    val kotlinxVersion = "1.5.2"
    implementation("$kotlinxGroup:kotlinx-coroutines-core:$kotlinxVersion")
    implementation("$kotlinxGroup:kotlinx-coroutines-jdk8:$kotlinxVersion")
}

/* publishing */
val sourcesJar by tasks.registering(Jar::class) {
    archiveClassifier.set("sources")
    from(sourceSets["main"].allSource)
}

publishing {
    repositories {
        maven {
            name = "jfrog"
            url = uri("https://m5rian.jfrog.io/artifactory/java")
            credentials {
                username = System.getenv("JFROG_USERNAME")
                password = System.getenv("JFROG_PASSWORD")
            }
        }
    }

    publications {
        create<MavenPublication>("jfrog") {
            from(components["java"])

            group = project.group as String
            version = project.version as String
            artifactId = id

            artifact(sourcesJar)
        }
    }
}


tasks.withType<KotlinCompile> {
    kotlinOptions.jvmTarget = "13"
}