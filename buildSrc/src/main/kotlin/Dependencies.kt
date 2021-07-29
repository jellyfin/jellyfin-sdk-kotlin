import org.gradle.api.artifacts.dsl.RepositoryHandler

fun RepositoryHandler.defaultRepositories() {
	mavenCentral()
	google()
}

object Plugins {
	object Versions {
		// Kotlin version should be consistent with version catalog in /gradle/libs.versions.toml!
		const val kotlin = "1.5.10"
		const val binaryCompatibilityValidatorVersion = "0.6.0"
		const val detekt = "1.17.1"
		const val nexusPublish = "1.1.0"
		const val dokka = "1.4.32"
		const val androidBuildTools = "7.0.0"
	}

	const val kotlin = "org.jetbrains.kotlin:kotlin-gradle-plugin:${Versions.kotlin}"
	const val androidBuildTools = "com.android.tools.build:gradle:${Versions.androidBuildTools}"
}
