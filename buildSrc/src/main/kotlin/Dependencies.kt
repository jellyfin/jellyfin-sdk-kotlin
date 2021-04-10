import org.gradle.api.artifacts.dsl.DependencyHandler
import org.gradle.api.artifacts.dsl.RepositoryHandler
import org.gradle.kotlin.dsl.project

fun RepositoryHandler.defaultRepositories() {
	google()
	mavenCentral()
	jcenter()
}

object Dependencies {
	object Kotlin {
		const val version = "1.4.30"
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

		val coroutinesCore = item("coroutines-core", "1.4.2")
		val serializationJson = item("serialization-json", "1.0.1")
		const val binaryCompatibilityValidatorVersion = "0.5.0"
	}

	object Android {
		const val buildTools = "com.android.tools.build:gradle:4.1.2"
		const val desugarJdkLibs = "com.android.tools:desugar_jdk_libs:1.1.0"
	}

	object AndroidX {
		private fun item(library: String, module: String = library, version: String) = "androidx.${library}:${module}:${version}"

		val core = item("core", "core-ktx", "1.3.2")
		val annotation = item("annotation", version = "1.1.0")
	}

	object Ktor {
		private const val version = "1.5.1"
		private fun item(library: String) = "io.ktor:ktor-$library:$version"

		val okhttp = item("client-okhttp")
		val serialization = item("client-serialization-jvm")

		val http = item("http")
		val io = item("io")
	}

	object Koin {
		private const val version = "2.2.2"
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

	// Gradle plugins
	const val detektVersion = "1.14.2"
	const val nexusPublishPluginVersion = "1.0.0"
	const val dokkaVersion = "1.4.30"
}
