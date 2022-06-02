import org.jetbrains.kotlin.gradle.tasks.KotlinCompile
import org.jetbrains.kotlin.scripting.definitions.StandardScriptDefinition.platform

plugins {
    java
    idea
    id("org.springframework.boot") version "2.7.0"
    kotlin("jvm") version "1.6.21"
    id("com.google.devtools.ksp") version "1.6.21-1.0.5"
    id("org.jetbrains.kotlin.plugin.spring") version "1.6.21"
}

val komapperVersion: String by project

apply(plugin = "org.jetbrains.kotlin.jvm")
apply(plugin = "io.spring.dependency-management")

group = "jp.classmethod.sample"
version = "1.0-SNAPSHOT"

repositories {
    mavenLocal()
    mavenCentral()
    maven { url = uri("https://repo.spring.io/release") }
    maven { url = uri("https://repo.spring.io/milestone") }
}

dependencies {

    platform("org.komapper:komapper-platform:$komapperVersion").let {
        implementation(it)
        ksp(it)
    }

    // 1 add spring-boot-starter-web
    implementation("org.springframework.boot:spring-boot-starter-web:2.7.0")

    // 3. add spring-security-resource-server
    implementation("org.springframework.security:spring-security-oauth2-resource-server:5.7.1")
    implementation("org.springframework.boot:spring-boot-starter-security:2.7.0")

    // これがないと JwtDecoderが見つからない
    implementation("org.springframework.security:spring-security-oauth2-jose:5.7.1")

    // komapper
    implementation("com.fasterxml.jackson.module:jackson-module-kotlin")
    implementation("org.komapper:komapper-spring-boot-starter-jdbc")
    implementation("org.komapper:komapper-dialect-postgresql-jdbc")
    ksp("org.komapper:komapper-processor")


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

springBoot {
    mainClass.set("jp.classmethod.sample.spring.ApplicationKt")
}