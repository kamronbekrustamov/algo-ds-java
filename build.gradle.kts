plugins {
    id("java")
}

group 'org.kamronbek'
version '1.0-SNAPSHOT'

repositories {
    mavenCentral()
}

java {
    sourceCompatibility = JavaVersion.VERSION_17
}

dependencies {
    testImplementation('org.junit.jupiter:junit-jupiter-api:5.10.1')
    testRuntimeOnly('org.junit.jupiter:junit-jupiter-engine:5.10.1')
}

test {
    useJUnitPlatform()
}