import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    id("org.springframework.boot") version "2.7.7"
    id("io.spring.dependency-management") version "1.1.0"
    kotlin("jvm") version "1.7.22"
    kotlin("plugin.spring") version "1.7.22"
    kotlin("plugin.jpa") version "1.7.22"
}

group = "com.example"
version = "0.0.1-SNAPSHOT"
java.sourceCompatibility = JavaVersion.VERSION_17

repositories {
    mavenCentral()
}

dependencies {

    //jpa
    implementation("org.springframework.boot:spring-boot-starter-data-jpa")

    //mysql
    runtimeOnly("com.mysql:mysql-connector-j")

    //redis
    implementation("org.springframework.boot:spring-boot-starter-data-redis")

    //security
    implementation("org.springframework.boot:spring-boot-starter-security")

    //web
    implementation("org.springframework.boot:spring-boot-starter-web")

    //socket
    implementation("org.springframework.boot:spring-boot-starter-websocket")

    //validation
    implementation("org.springframework.boot:spring-boot-starter-validation")

    //jwt
    implementation("io.jsonwebtoken:jjwt:0.9.0")

    //kotlin
    implementation("com.fasterxml.jackson.module:jackson-module-kotlin")
    implementation("org.jetbrains.kotlin:kotlin-reflect")
}

tasks.withType<KotlinCompile> {
    kotlinOptions {
        freeCompilerArgs = listOf("-Xjsr305=strict")
        jvmTarget = "17"
    }
}

allOpen{
    annotation("javax.persistence.Entity")
}

noArg{
    annotation("javax.persistence.Entity")
}
