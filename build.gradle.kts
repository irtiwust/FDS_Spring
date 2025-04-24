plugins {
    id("java")
}

group = "ru.tbank"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    implementation("org.springframework.boot:spring-boot-starter-web:3.4.2")
    implementation("org.springframework.boot:spring-boot-starter-data-jpa")
    runtimeOnly("org.postgresql:postgresql")
    annotationProcessor("org.projectlombok:lombok")
    compileOnly("org.projectlombok:lombok:1.18.24")

    implementation("org.hibernate.orm:hibernate-core:6.6.4.Final")


    testImplementation("org.springframework.boot:spring-boot-starter-test:3.4.2")
    testImplementation(platform("org.junit:junit-bom:5.10.0"))
    testImplementation("org.junit.jupiter:junit-jupiter")

    implementation("org.liquibase:liquibase-core")
    implementation ("org.hibernate:hibernate-core")

    implementation("jakarta.validation:jakarta.validation-api:3.1.0-M2")

    implementation("org.springframework.boot:spring-boot-starter-data-jpa:3.4.2")
    implementation("org.springframework.data:spring-data-jpa:3.4.2")
}

tasks.test {
    useJUnitPlatform()
}