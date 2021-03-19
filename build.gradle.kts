plugins {
	id("io.github.gradle-nexus.publish-plugin") version "1.0.0"
	id("io.gitlab.arturbosch.detekt").version(Dependencies.detektVersion)
}

// Versioning
allprojects {
	group = "org.jellyfin.sdk"
	version = getProperty("jellyfin.version")?.removePrefix("v") ?: "latest-SNAPSHOT"
}

buildscript {
	repositories.defaultRepositories()

	dependencies {
		classpath(Dependencies.Android.buildTools)
		classpath(Dependencies.Kotlin.gradlePlugin)
	}
}

// Add Sonatype publishing repository
nexusPublishing.repositories.sonatype {
	nexusUrl.set(uri("https://s01.oss.sonatype.org/service/local/"))
	snapshotRepositoryUrl.set(uri("https://s01.oss.sonatype.org/content/repositories/snapshots/"))

	username.set(getProperty("ossrh.username"))
	password.set(getProperty("ossrh.password"))
}

subprojects {
	// Enable required plugins
	apply<SigningPlugin>()
	apply<MavenPublishPlugin>()
	apply<io.gitlab.arturbosch.detekt.DetektPlugin>()

	// Add dependency repositories
	repositories.defaultRepositories()

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
			val publication = publications.findByName("default") as? MavenPublication
			publication?.defaultPom()
		}
	}

	// Detekt linting
	detekt {
		buildUponDefaultConfig = true
		ignoreFailures = true
		config = files("$rootDir/detekt.yml")
	}
}
