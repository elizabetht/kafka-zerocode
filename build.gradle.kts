/*
 * This file was generated by the Gradle 'init' task.
 *
 * This generated file contains a sample Kotlin application project to get you started.
 */

plugins {
    // Apply the Kotlin JVM plugin to add support for Kotlin.
    id("org.jetbrains.kotlin.jvm") version "1.3.61"

    // Apply the application plugin to add support for building a CLI application.
    application

    id("com.avast.gradle.docker-compose") version "0.10.0"
}

dockerCompose.isRequiredBy(tasks.test.get())
dockerCompose {
    useComposeFiles = listOf("docker-compose.yml")
}

repositories {
    // Use jcenter for resolving dependencies.
    // You can declare any Maven/Ivy/file repository here.
    jcenter()
}

dependencies {
    // Align versions of all Kotlin components
    implementation(platform("org.jetbrains.kotlin:kotlin-bom"))

    // Use the Kotlin JDK 8 standard library.
    implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8")

    implementation("org.apache.kafka:kafka-clients:2.5.0")

    val kotestVersion: String by extra { "4.0.4" }
    testApi("io.kotest:kotest-runner-junit5-jvm:$kotestVersion")
    testApi("io.kotest:kotest-assertions-core-jvm:$kotestVersion")
    testApi("io.kotest:kotest-property-jvm:$kotestVersion")

    testApi("io.mockk:mockk:1.10.0")

    val junitVersion: String by extra { "5.6.2" }
    testApi("org.junit.jupiter:junit-jupiter-api:$junitVersion")
    testApi("org.junit.jupiter:junit-jupiter-engine:$junitVersion")
    testApi("org.junit.jupiter:junit-jupiter-params:$junitVersion")

    testApi("org.jsmart:zerocode-tdd:1.3.18")
    testApi("org.jsmart:zerocode-tdd-jupiter:1.3.18")
}

tasks.create<org.gradle.api.tasks.Delete>("cleanTargetDirectory") {
    delete = setOf("target")
}

tasks {
    test {
        useJUnitPlatform()
    }

    clean {
        dependsOn("cleanTargetDirectory")
    }
}

application {
    // Define the main class for the application.
    mainClassName = "kafka.AppKt"
}
