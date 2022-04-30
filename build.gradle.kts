plugins {
	alias(libs.plugins.nexuspublish)
	alias(libs.plugins.detekt)
	alias(libs.plugins.dokka)
	alias(libs.plugins.binarycompatibilityvalidator)
	alias(libs.plugins.kotest)
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
	nexusUrl.set(uri("https://s01.oss.sonatype.org/service/local/"))
	snapshotRepositoryUrl.set(uri("https://s01.oss.sonatype.org/content/repositories/snapshots/"))

	username.set(getProperty("ossrh.username"))
	password.set(getProperty("ossrh.password"))
}

apiValidation {
	// Ignore generator / samples / other non jellyfin-x modules
	ignoredProjects.addAll(subprojects.map { it.name }.filter { !it.startsWith("jellyfin-") })
}

subprojects {
	// Enable required plugins
	apply<io.gitlab.arturbosch.detekt.DetektPlugin>()
	apply<org.jetbrains.dokka.gradle.DokkaPlugin>()
	apply<io.kotest.framework.multiplatform.gradle.KotestMultiplatformCompilerGradlePlugin>()

	// Detekt linting
	detekt {
		buildUponDefaultConfig = true
		ignoreFailures = true
		config = files("$rootDir/detekt.yaml")
		basePath = rootDir.absolutePath

		reports {
			sarif.enabled = true
		}
	}

	tasks.withType<Test> {
		useJUnitPlatform()
	}
}
