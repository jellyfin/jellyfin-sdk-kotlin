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
	implementation(project(":library"))

	// Use Kotlin stdlib
	implementation(kotlin("stdlib"))

	// Use Kotlin coroutines to interact with the library
	implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.3.5")

	// The CLI library
	implementation("org.jetbrains.kotlinx:kotlinx-cli:0.2.1")

	// Use JSON
	implementation("com.google.code.gson:gson:2.8.6")
}

tasks.withType<org.jetbrains.kotlin.gradle.tasks.KotlinCompile>().all {
	kotlinOptions.freeCompilerArgs += "-Xopt-in=kotlinx.cli.ExperimentalCli"
}
