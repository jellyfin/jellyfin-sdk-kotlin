import org.gradle.api.artifacts.dsl.DependencyHandler
import org.gradle.kotlin.dsl.project

object Dependencies {
	object KotlinX {
		private fun item(module: String, version: String) = "org.jetbrains.kotlinx:kotlinx-${module}:${version}"

		val cli = item("cli", "0.2.1")
		val coroutinesCore = item("coroutines-core", "1.3.9")
		val serializationCore = item("serialization-core", "1.0.0-RC")
	}

	object AndroidX {
		private fun item(library: String, module: String = library, version: String) = "androidx.${library}:${module}:${version}"

		val core = item("core", "core-ktx", "1.3.0")
		val annotation = item("annotation", version = "1.1.0")
	}

	object Kotlin {
		const val version = "1.4.10"
		private fun item(library: String) = "org.jetbrains.kotlin:kotlin-$library:$version"

		val stdlib = item("stdlib")
		val gradlePlugin = item("gradle-plugin")

		object Test {
			private fun testItem(library: String) = item("test-$library")

			val junit = testItem("junit")
		}
	}

	object Ktor {
		private const val version = "1.4.0"
		private fun item(library: String) = "io.ktor:ktor-$library:$version"

		val okhttp = item("client-okhttp")
		val serialization = item("client-serialization-jvm")

		val http = item("http")
	}

	object Koin {
		private const val version = "2.1.6"
		private fun item(module: String) = "org.koin:koin-$module:$version"

		val core = item("core")
	}

	object Slf4j {
		private const val version = "1.6.1"
		private fun item(module: String) = "org.slf4j:slf4j-$module:$version"

		val api = item("api")
		val simple = item("simple")
	}

	// Non-categorised dependencies
	const val androidBuildTools = "com.android.tools.build:gradle:4.0.1"
	const val swaggerParser = "io.swagger.parser.v3:swagger-parser:2.0.19"
	const val kotlinPoet = "com.squareup:kotlinpoet:1.6.0"
	const val kasechange = "net.pearx.kasechange:kasechange:1.3.0"
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
