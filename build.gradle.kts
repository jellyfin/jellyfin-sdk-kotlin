plugins {
	id("maven-publish")
//	id("io.gitlab.arturbosch.detekt").version("1.9.1")
}

// Versioning
allprojects {
	group = "org.jellyfin.apiclient"
	version = getProperty("jellyfin.version")?.removePrefix("v") ?: "SNAPSHOT"
}

buildscript {
	repositories {
		mavenCentral()
		google()
	}

	dependencies {
		classpath("com.android.tools.build:gradle:4.0.2")
		classpath(kotlin("gradle-plugin", "1.3.72"))
	}
}

allprojects {
	repositories {
		mavenCentral()
		google()
	}

	// Publishing
	plugins.apply("maven-publish")
//	publishing.repositories.jellyfinBintray(this)

	// Detekt
//	plugins.apply("io.gitlab.arturbosch.detekt")
//	detekt {
//		buildUponDefaultConfig = true
//		ignoreFailures = true
//		config = files("$rootDir/detekt.yml")
//	}
}
