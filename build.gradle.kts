
val lwjglVersion = "3.3.3"
val lwjglNatives = "natives-linux"

plugins {
    id("java")
    `java-library`
    id("org.openjfx.javafxplugin") version "0.1.0"
    id("org.jetbrains.kotlin.jvm") version "1.9.21"
}

group = "main"
version = "1.0-SNAPSHOT"

repositories{
    mavenCentral()
}

java{
    sourceCompatibility = JavaVersion.VERSION_17
    targetCompatibility = JavaVersion.VERSION_17
}

/*
javafx{
    version = "17.0.1"
    modules = listOf("javafx.controls", "javafx.swing", "javafx.graphics", "javafx.base", "javafx.media")
}

 */


dependencies{
    implementation("org.jetbrains:annotations:24.0.0")
    implementation("org.joml:joml:1.10.0")
    implementation("javazoom:jlayer:1.0.1")

    implementation("org.openjfx:javafx-controls:17.0.1")
    implementation("org.openjfx:javafx-swing:17.0.1")
    implementation("org.openjfx:javafx-graphics:17.0.1")
    implementation("org.openjfx:javafx-base:17.0.1")
    implementation("org.openjfx:javafx-media:17.0.1")
}

tasks.test {
    useJUnitPlatform()
}