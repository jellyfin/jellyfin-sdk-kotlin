import org.gradle.api.Project
import org.gradle.api.publish.PublishingExtension
import org.gradle.api.publish.maven.MavenPublication
import org.gradle.api.publish.maven.plugins.MavenPublishPlugin
import org.gradle.api.publish.maven.tasks.AbstractPublishToMaven
import org.gradle.kotlin.dsl.apply
import org.gradle.kotlin.dsl.configure
import org.gradle.kotlin.dsl.getByType
import org.gradle.kotlin.dsl.provideDelegate
import org.gradle.kotlin.dsl.withType
import org.gradle.plugins.signing.Sign
import org.gradle.plugins.signing.SigningExtension
import org.gradle.plugins.signing.SigningPlugin

fun Project.enablePublishing(init: PublishingExtension.() -> Unit = {}) {
	apply<SigningPlugin>()
	apply<MavenPublishPlugin>()

	extensions.getByType<PublishingExtension>().init()

	// FIXME - workaround for https://github.com/gradle/gradle/issues/26091
	val signingTasks = tasks.withType<Sign>()
	tasks.withType<AbstractPublishToMaven>().configureEach {
		mustRunAfter(signingTasks)
	}

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
}
