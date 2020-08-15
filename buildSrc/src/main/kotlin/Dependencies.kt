import org.gradle.api.artifacts.dsl.DependencyHandler
import org.gradle.kotlin.dsl.kotlin
import org.gradle.kotlin.dsl.project

object Dependencies {
	object KotlinX {
		private fun item(module: String, version: String) = "org.jetbrains.kotlinx:kotlinx-${module}:${version}"

		val cli = item("cli", "0.2.1")
		val coroutinesCore = item("coroutines-core", "1.3.5")
	}

	object AndroidX {
		private fun item(library: String, module: String = library, version: String) = "androidx.${library}:${module}:${version}"

		val core = item("core", "core-ktx", "1.3.0")
		val annotation = item("annotation", version = "1.1.0")
	}

	// Non-categorised dependencies
	const val volley = "com.android.volley:volley:1.1.1"
	const val gson = "com.google.code.gson:gson:2.8.6"
	const val javaWebSocket = "org.java-websocket:Java-WebSocket:1.4.1"
	const val junit = "junit:junit:4.12"
}

/**
 * Add given project as api
 */
fun DependencyHandler.apiProject(path: String) {
	add("api", project(path))
}

/**
 * Add given project as implementation
 */
fun DependencyHandler.implementationProject(path: String) {
	add("implementation", project(path))
}

/**
 * Add the Kotlin standard library as implementation
 */
fun DependencyHandler.implementationKotlinStdlib() = kotlin("stdlib")
