import org.gradle.api.artifacts.dsl.RepositoryHandler

fun RepositoryHandler.defaultRepositories() {
	mavenCentral()
	google()
}
