plugins {
	alias(libs.plugins.binarycompatibilityvalidator)
	alias(libs.plugins.detekt)
	alias(libs.plugins.dokka)
	alias(libs.plugins.nexuspublish)
}

buildscript {
	repositories {
		mavenCentral()
		google()
	}

	dependencies {
		classpath(libs.android.gradle)
		classpath(libs.kotlin.gradle)
	}
}

// Versioning
allprojects {
	group = "org.jellyfin.sdk"
	version = createVersion()

	repositories {
		mavenCentral()
		google()
	}
}

// Add Sonatype publishing repository
nexusPublishing.repositories.sonatype {
	nexusUrl = uri("https://ossrh-staging-api.central.sonatype.com/service/local/")
	snapshotRepositoryUrl = uri("https://central.sonatype.com/repository/maven-snapshots/")

	username = getProperty("ossrh.username")
	password = getProperty("ossrh.password")
}

apiValidation {
	// Ignore generator / samples / other non jellyfin-x modules
	ignoredProjects.addAll(subprojects.map { it.name }.filter { !it.startsWith("jellyfin-") })
}

detekt {
	buildUponDefaultConfig = true
	ignoreFailures = true
	config.setFrom(files("$rootDir/detekt.yaml"))
	basePath = rootDir.absolutePath
	parallel = true

	source.setFrom(fileTree(projectDir) {
		include("**/*.kt", "**/*.kts")
	})
}

tasks.withType<io.gitlab.arturbosch.detekt.Detekt> {
	reports {
		sarif.required = true
	}
}

subprojects {
	tasks.withType<Test> {
		useJUnitPlatform()
	}
}

// Dokka multi-module aggregation
dependencies {
	dokka(project(":jellyfin-api"))
	dokka(project(":jellyfin-api-okhttp"))
	dokka(project(":jellyfin-core"))
	dokka(project(":jellyfin-model"))
}
