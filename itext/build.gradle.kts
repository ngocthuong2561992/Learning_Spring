import org.jetbrains.kotlin.gradle.tasks.KotlinCompile
import org.springframework.boot.gradle.tasks.bundling.BootBuildImage

plugins {
    id("org.springframework.boot") version "2.7.3"
    id("io.spring.dependency-management") version "1.0.13.RELEASE"
    kotlin("jvm") version "1.6.21"
    kotlin("plugin.spring") version "1.6.0"
    kotlin("plugin.jpa") version "1.6.21"
    id("org.springframework.experimental.aot") version "0.12.1"
}

group = "com"
version = "0.0.1"
java.sourceCompatibility = JavaVersion.VERSION_17

configurations {
    compileOnly {
        extendsFrom(configurations.annotationProcessor.get())
    }
}

repositories {
    maven { url = uri("https://repo.spring.io/release") }
    mavenCentral()
}

dependencies {
    implementation("org.springframework.boot:spring-boot-starter-thymeleaf")
    implementation("org.springframework.boot:spring-boot-starter-web")
    implementation("com.fasterxml.jackson.module:jackson-module-kotlin")
    implementation("org.jetbrains.kotlin:kotlin-reflect")
    implementation("org.jetbrains.kotlin:kotlin-stdlib:1.6.0")
    developmentOnly("org.springframework.boot:spring-boot-devtools")
    annotationProcessor("org.springframework.boot:spring-boot-configuration-processor")
    testImplementation("org.springframework.boot:spring-boot-starter-test")

//    log
    implementation("org.springframework.boot:spring-boot-starter-logging:2.7.3")
    implementation("org.springframework.boot:spring-boot-starter-log4j2:2.7.3")

    //	itextPdf
    implementation("com.itextpdf:itextpdf:5.5.13.3")
    implementation ("com.itextpdf:html2pdf:4.0.2")
    testImplementation ("com.itextpdf:font-asian:7.2.2")
    implementation ("com.itextpdf:sign:7.2.2")
    implementation ("com.itextpdf:barcodes:7.2.2")
    testImplementation ("com.itextpdf:hyph:7.2.2")
    implementation ("com.itextpdf:forms:7.2.2")
    implementation ("com.itextpdf:itext-pdfa:5.5.13.3")
    implementation("org.apache.poi:poi:5.2.2")
    implementation("org.apache.poi:poi-ooxml:5.2.2")
}

configurations {
    all {
        exclude(group = "org.springframework.boot", module = "spring-boot-starter-logging")
    }
}

tasks.withType<KotlinCompile> {
    kotlinOptions {
        freeCompilerArgs = listOf("-Xjsr305=strict")
        jvmTarget = "17"
    }
}

tasks.withType<Test> {
    useJUnitPlatform()
}

tasks.withType<BootBuildImage> {
    builder = "paketobuildpacks/builder:tiny"
    environment = mapOf("BP_NATIVE_IMAGE" to "true")
}
