import org.jetbrains.kotlin.gradle.tasks.KotlinCompile
import org.springframework.boot.gradle.tasks.bundling.BootJar
import java.util.Properties
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

val phaseVersion = "4.26.1.00"

buildscript {
    repositories {
        jcenter()
    }
    dependencies {
        classpath("gradle.plugin.com.ewerk.gradle.plugins:querydsl-plugin:1.0.10")
    }
}


plugins {
    kotlin("jvm") version "1.3.61"
    kotlin("kapt") version "1.3.61"
    kotlin("plugin.spring") version "1.3.61"
    kotlin("plugin.jpa") version "1.3.61"
    id("org.springframework.boot") version "2.1.8.RELEASE"
    id("io.spring.dependency-management") version "1.0.8.RELEASE"
    id("com.gorylenko.gradle-git-properties") version "1.5.1"
}

//val ktlint: Configuration by configurations.creating
val developmentOnly: Configuration? by configurations.creating
configurations {
    runtimeClasspath {
        extendsFrom(developmentOnly)
    }
    compileOnly {
        extendsFrom(configurations.annotationProcessor.get())
    }
}

configurations.forEach {
    it.exclude("org.springframework.boot", "spring-boot-starter-tomcat")
}


repositories {
    jcenter()
}


dependencies {
    implementation(kotlin("stdlib-jdk8"))
    implementation("com.querydsl:querydsl-jpa:4.2.1")
    implementation("com.querydsl:querydsl-apt:4.2.1")
    implementation("com.querydsl:querydsl-sql-spring:4.2.1")
    kapt("com.querydsl:querydsl-apt:4.2.1:jpa")
    implementation("org.springframework.boot:spring-boot-starter-actuator")
    implementation("org.springframework.boot:spring-boot-starter-aop")
    implementation("org.springframework.boot:spring-boot-starter-amqp")
    implementation("org.springframework.boot:spring-boot-starter-data-jpa")
    implementation("org.springframework.boot:spring-boot-starter-data-rest")
    implementation("org.springframework.boot:spring-boot-starter-web")
    implementation("org.springframework.boot:spring-boot-starter-webflux")
    implementation("org.springframework.boot:spring-boot-starter-undertow")
    implementation("org.springframework.boot:spring-boot-starter-data-redis")

    implementation("com.fasterxml.jackson.module:jackson-module-kotlin")

    implementation("org.jetbrains.kotlin:kotlin-reflect")
    implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8")
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.3.2")
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-reactor:1.3.2")

    implementation("org.hibernate", "hibernate-core", "5.4.4.Final")

    implementation("io.springfox", "springfox-swagger2", "2.9.2")
    implementation("io.springfox", "springfox-swagger-ui", "2.9.2")

    implementation("org.apache.httpcomponents", "httpclient", "4.5.9")
    implementation("org.apache.commons", "commons-lang3", "3.11")
    implementation("org.modelmapper", "modelmapper", "2.3.2")
    implementation(fileTree(mapOf("dir" to "libs", "include" to listOf("ojdbc7-12.1.0.2.jar"))))

    developmentOnly?.let { it("org.springframework.boot:spring-boot-devtools") }
    kapt("org.springframework.boot:spring-boot-configuration-processor")
    testImplementation("org.springframework.boot:spring-boot-starter-test")

    testImplementation("io.projectreactor:reactor-test")
    testImplementation("org.junit.jupiter", "junit-jupiter-api")
    testImplementation("org.mockito:mockito-core:3.1.0")
    testImplementation("org.jetbrains.kotlinx", "kotlinx-coroutines-test", "1.3.2")
    testRuntimeOnly("org.junit.jupiter", "junit-jupiter-engine")

}

tasks.withType<KotlinCompile> {
    doFirst {
        val git = org.ajoberstar.grgit.Grgit.open {
            file(".")
        }
        val infoFile = file("${projectDir}/src/main/resources/build-info.properties")
        val properties = Properties()
        val localDateTime = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"))
        properties.setProperty("info.version", phaseVersion)
        properties.setProperty("info.git.hash", git.head().getAbbreviatedId(7))
        properties.setProperty("info.git.buildDate", localDateTime)
        properties.store(infoFile.writer(), "Build Information")
    }
    kotlinOptions {
        freeCompilerArgs = listOf("-Xjsr305=strict")
        jvmTarget = "1.8"
    }
}

tasks.withType<Test> {
    useJUnitPlatform()
}

val querydslSourcesDir = file("src/main/generated")

sourceSets {
    main {
        java {
            listOf("src/main/java", querydslSourcesDir)
        }
        kotlin {
            listOf("src/main/kotlin", querydslSourcesDir)
        }
    }
}

tasks.getByName<BootJar>("bootJar") {
    enabled = true
}