import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    kotlin("jvm") version "1.6.21"
    application
}

group = "jp.classmethod.sample"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    // 1 add spring-boot-starter-web
    implementation("org.springframework.boot:spring-boot-starter-web:2.7.0")

    // 3. add spring-security-resource-server
    implementation("org.springframework.security:spring-security-oauth2-resource-server:5.7.1")
    implementation("org.springframework.boot:spring-boot-starter-security:2.7.0")

    // これがないと JwtDecoderが見つからない
    implementation("org.springframework.security:spring-security-oauth2-jose:5.7.1")


    testImplementation(kotlin("test"))
}

tasks.test {
    useJUnitPlatform()
}

tasks.withType<KotlinCompile> {
    kotlinOptions {
        freeCompilerArgs = listOf("-Xjsr305=strict")
        jvmTarget = "11"
    }
}

application {
    mainClass.set("MainKt")
}