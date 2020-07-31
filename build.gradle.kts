plugins {
	id("maven-publish")
	id("io.gitlab.arturbosch.detekt").version("1.9.1")
}

// Versioning
allprojects {
	group = "org.jellyfin.apiclient"
	version = getProperty("jellyfin.version") ?: "SNAPSHOT"
}

buildscript {
	repositories {
		google()
		jcenter()
	}

	dependencies {
		classpath("com.android.tools.build:gradle:4.0.1")
		classpath(kotlin("gradle-plugin", "1.3.72"))
	}
}

allprojects {
	repositories {
		google()
		jcenter()
	}

	// Publishing
	plugins.apply("maven-publish")
	publishing.repositories.maven {
		name = "bintray"
		url = uri("https://bintray.com/jellyfin/jellyfin-apiclient-java/jellyfin-apiclient-java;publish=1;override=1")

		credentials {
			username = getProperty("bintray.user") as String?
			password = getProperty("bintray.key") as String?
		}
	}

	// Detekt
	plugins.apply("io.gitlab.arturbosch.detekt")
	detekt {
		buildUponDefaultConfig = true
		ignoreFailures = true
		config = files("$rootDir/detekt.yml")
	}
}

/**
 * Helper function to retrieve configuration variable values
 */
fun getProperty(name: String): Any? {
	// sample.var --> SAMPLE_VAR
	val environmentName = name.toUpperCase().replace(".", "_")

	return project.findProperty(name) ?: System.getenv(environmentName) ?: null
}
