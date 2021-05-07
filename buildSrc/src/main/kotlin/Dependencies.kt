import org.gradle.api.artifacts.dsl.RepositoryHandler

fun RepositoryHandler.defaultRepositories() {
	google()
	mavenCentral()
}

object Plugins {
	object Versions {
		const val kotlin = "1.5.0"
		const val binaryCompatibilityValidatorVersion = "0.5.0"
		const val detekt = "1.17.0-RC2"
		const val nexusPublish = "1.1.0"
		const val dokka = "1.4.32"
		const val androidBuildTools = "4.2.0"
	}

	const val kotlin = "org.jetbrains.kotlin:kotlin-gradle-plugin:${Versions.kotlin}"
	const val androidBuildTools = "com.android.tools.build:gradle:${Versions.androidBuildTools}"
}
