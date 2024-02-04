import org.gradle.api.Project

/**
 * Helper function to retrieve configuration variable values
 */
fun Project.getProperty(name: String): String? {
	// sample.var --> SAMPLE_VAR
	val environmentName = name.uppercase().replace(".", "_")
	val value = findProperty(name)?.toString() ?: System.getenv(environmentName) ?: null
	logger.debug("getProperty($name): $environmentName - found=${!value.isNullOrBlank()}")
	return value
}

/**
 * Helper function to create the SDK version using the `jellyfin.version` and `jellyfin.branch` properties.
 * Uses [getProperty] to retrieve the properties. Defaults to `latest-SNAPSHOT` if both properties are not set.
 */
fun Project.createVersion(): String {
	val jellyfinVersion = getProperty("jellyfin.version")?.removePrefix("v")
	val jellyfinBranch = getProperty("jellyfin.branch")?.let { "$it-SNAPSHOT" }

	return jellyfinVersion ?: jellyfinBranch ?: "latest-SNAPSHOT"
}
