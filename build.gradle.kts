plugins {
	id("io.github.gradle-nexus.publish-plugin") version "1.0.0"
	id("io.gitlab.arturbosch.detekt").version(Dependencies.detektVersion)
}

// Versioning
allprojects {
	group = "org.jellyfin.apiclient"
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

	username.set(project.properties["ossrh.username"].toString())
	password.set(project.properties["ossrh.password"].toString())
}

subprojects {
	// Enable required plugins
	apply<MavenPublishPlugin>()
	apply<SigningPlugin>()
	apply<io.gitlab.arturbosch.detekt.DetektPlugin>()

	// Add dependency repositories
	repositories.defaultRepositories()

	// Run block after creating project specific configuration
	afterEvaluate {
		// Add signing config
		configure<SigningExtension> {
			val signingKey = project.properties["signing.key"]?.toString()
			val signingPassword = project.properties["signing.password"]?.toString()

			useInMemoryPgpKeys(signingKey, signingPassword)
			val publishing: PublishingExtension by project
			sign(publishing.publications)
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
