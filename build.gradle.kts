plugins {
    id("java")
}

group = "ru.netology"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    testImplementation(platform("org.junit:junit-bom:5.9.1"))
    testImplementation("org.junit.jupiter:junit-jupiter")
    implementation ("org.apache.httpcomponents:httpclient:4.5.13")
    implementation ("com.fasterxml.jackson.core:jackson-core:2.11.1")
    implementation ("com.fasterxml.jackson.core:jackson-databind:2.11.1")
    implementation ("com.fasterxml.jackson.core:jackson-annotations:2.11.1")
}

tasks.test {
    useJUnitPlatform()
}