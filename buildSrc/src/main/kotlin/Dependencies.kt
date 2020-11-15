import org.gradle.api.artifacts.dsl.DependencyHandler
import org.gradle.kotlin.dsl.project

object Dependencies {
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

	object KotlinX {
		private fun item(module: String, version: String) = "org.jetbrains.kotlinx:kotlinx-${module}:${version}"

		val coroutinesCore = item("coroutines-core", "1.4.1")
		val serializationJson = item("serialization-json", "1.0.1")
	}

	object Android {
		const val buildTools = "com.android.tools.build:gradle:4.0.1"
		const val desugarJdkLibs = "com.android.tools:desugar_jdk_libs:1.1.0"
	}

	object AndroidX {
		private fun item(library: String, module: String = library, version: String) = "androidx.${library}:${module}:${version}"

		val core = item("core", "core-ktx", "1.3.2")
		val annotation = item("annotation", version = "1.1.0")
	}

	object Ktor {
		private const val version = "1.4.2"
		private fun item(library: String) = "io.ktor:ktor-$library:$version"

		val okhttp = item("client-okhttp")
		val serialization = item("client-serialization-jvm")

		val http = item("http")
		val io = item("io")
	}

	object Koin {
		private const val version = "2.2.0"
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
	const val swaggerParser = "io.swagger.parser.v3:swagger-parser:2.0.23"
	const val kotlinPoet = "com.squareup:kotlinpoet:1.7.2"
	const val kasechange = "net.pearx.kasechange:kasechange:1.3.0"
	const val clikt = "com.github.ajalt.clikt:clikt:3.0.1"
	const val detektVersion = "1.14.2"
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
