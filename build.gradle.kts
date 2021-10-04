plugins {
	alias(libs.plugins.nexuspublish)
	alias(libs.plugins.detekt)
	alias(libs.plugins.dokka)
	alias(libs.plugins.binarycompatibilityvalidator)
}

// Versioning
allprojects {
	group = "org.jellyfin.sdk"
	version = createVersion()

	// Add default dependency repositories
	repositories.defaultRepositories()
}

buildscript {
	repositories.defaultRepositories()

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
	apply<SigningPlugin>()
	apply<MavenPublishPlugin>()
	apply<io.gitlab.arturbosch.detekt.DetektPlugin>()
	apply<org.jetbrains.dokka.gradle.DokkaPlugin>()

	// Run block after creating project specific configuration
	afterEvaluate {
		// Add signing config
		configure<SigningExtension> {
			val signingKey = getProperty("signing.key")
			val signingPassword = getProperty("signing.password") ?: ""

			if (signingKey != null) {
				useInMemoryPgpKeys(signingKey, signingPassword)
				val publishing: PublishingExtension by project
				sign(publishing.publications)
			}
		}

		// Add POM to projects that use publishing
		configure<PublishingExtension> {
			publications.withType<MavenPublication>().forEach(MavenPublication::defaultPom)
		}
	}

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
}
