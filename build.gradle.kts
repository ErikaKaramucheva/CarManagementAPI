plugins {
	java
	id("org.springframework.boot") version "3.4.0"
	id("io.spring.dependency-management") version "1.1.6"
	id("io.freefair.lombok") version "8.11"
}

group = "uni.pu.fmi"
version = "0.0.1-SNAPSHOT"

java {
	toolchain {
		languageVersion = JavaLanguageVersion.of(17)
	}
}

repositories {
	mavenCentral()
}

dependencies {
	implementation("org.springframework.boot:spring-boot-starter")
	implementation("org.springframework.boot:spring-boot-starter-web")
	implementation("org.springdoc:springdoc-openapi-starter-webmvc-ui:2.6.0")
	implementation("org.springframework.data:spring-data-jpa:3.4.0")
	implementation("jakarta.persistence:jakarta.persistence-api:3.2.0")
	implementation("org.modelmapper:modelmapper:2.1.1")

	runtimeOnly("com.h2database:h2")
	testImplementation("org.springframework.boot:spring-boot-starter-test")
	testRuntimeOnly("org.junit.platform:junit-platform-launcher")

}

tasks.withType<Test> {
	useJUnitPlatform()
}
