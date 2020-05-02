plugins {
	id("maven-publish")
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
		classpath("com.android.tools.build:gradle:3.6.3")
		classpath(kotlin("gradle-plugin", "1.3.72"))
	}
}

allprojects {
	apply(plugin = "maven-publish")

	repositories {
		google()
		jcenter()
	}

	publishing {
		repositories {
			maven {
				url = uri("https://api.bintray.com/maven/nielsvanvelzen/jellyfin-apiclient-java/jellyfin-apiclient-java/;publish=0")

				credentials {
					username = getProperty("bintray.user") as String?
					password = getProperty("bintray.key") as String?
				}
			}
		}
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
