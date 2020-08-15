import org.gradle.api.Project
import org.gradle.api.artifacts.dsl.RepositoryHandler

fun RepositoryHandler.jellyfinBintray(project: Project) = maven {
	name = "bintray"
	url = project.uri("https://bintray.com/jellyfin/jellyfin-apiclient-java/jellyfin-apiclient-java;publish=1;override=1")

	credentials {
		username = project.getProperty("bintray.user")
		password = project.getProperty("bintray.key")
	}
}
