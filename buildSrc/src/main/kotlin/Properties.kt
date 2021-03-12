import org.gradle.api.Project

/**
 * Helper function to retrieve configuration variable values
 */
fun Project.getProperty(name: String): String? {
	// sample.var --> SAMPLE_VAR
	val environmentName = name.toUpperCase().replace(".", "_")
	val value = findProperty(name)?.toString() ?: System.getenv(environmentName) ?: null
	logger.info("getProperty($name): $environmentName - found=${!value.isNullOrBlank()}")
	return value
}
