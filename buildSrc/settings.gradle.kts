pluginManagement {
	repositories {
		gradlePluginPortal()
		mavenCentral()
	}

	plugins {
		id("org.gradle.toolchains.foojay-resolver-convention") version "1.0.0"
	}
}

plugins {
	id("org.gradle.toolchains.foojay-resolver-convention")
}

dependencyResolutionManagement {
	repositories {
		mavenCentral()
		google()
		gradlePluginPortal()
	}

	versionCatalogs {
		create("libs") {
			from(files("../gradle/libs.versions.toml"))
		}
	}
}

rootProject.name = "buildSrc"
