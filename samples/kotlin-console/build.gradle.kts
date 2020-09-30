plugins {
	kotlin("jvm")
	id("application")
}

application {
	mainClassName = "org.jellyfin.sample.console.MainKt"
}

repositories {
	jcenter()

	// Repository needed for kotlinx-cli
	maven("https://kotlin.bintray.com/kotlinx")
}

dependencies {
	// Depend on the library project
	implementationProject(":library")

	// Use Kotlin stdlib
	implementation(Dependencies.Kotlin.stdlib)

	// Use Kotlin coroutines to interact with the library
	implementation(Dependencies.KotlinX.coroutinesCore)

	// The CLI library
	implementation(Dependencies.KotlinX.cli)
}

tasks.withType<org.jetbrains.kotlin.gradle.tasks.KotlinCompile>().all {
	kotlinOptions.freeCompilerArgs += "-Xopt-in=kotlinx.cli.ExperimentalCli"
}
