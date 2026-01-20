plugins {
	alias(libs.plugins.animalsniffer)
	alias(libs.plugins.binarycompatibilityvalidator)
	alias(libs.plugins.detekt)
	alias(libs.plugins.dokka)
	alias(libs.plugins.download)
	alias(libs.plugins.kotest)
	alias(libs.plugins.nexuspublish)
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

// Add Sonatype publishing repository
nexusPublishing.repositories.sonatype {
	nexusUrl.set(uri("https://ossrh-staging-api.central.sonatype.com/service/local/"))
	snapshotRepositoryUrl.set(uri("https://central.sonatype.com/repository/maven-snapshots/"))

	username.set(getProperty("ossrh.username"))
	password.set(getProperty("ossrh.password"))
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
		sarif.required.set(true)
	}
}

subprojects {
	apply<io.kotest.framework.multiplatform.gradle.KotestMultiplatformCompilerGradlePlugin>()

	tasks.withType<Test> {
		useJUnitPlatform()
	}
}

dependencies {
	dokka(project(":jellyfin-core"))
	dokka(project(":jellyfin-api"))
	dokka(project(":jellyfin-api-okhttp"))
	dokka(project(":jellyfin-model"))
}
