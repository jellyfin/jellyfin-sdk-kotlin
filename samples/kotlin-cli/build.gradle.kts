plugins {
	kotlin("jvm")
	application
}

application {
	mainClass = "org.jellyfin.sample.cli.MainKt"
}

dependencies {
	// Depend on the library project
	implementation(projects.jellyfinCore)

	// Use Kotlin coroutines to interact with the library
	implementation(libs.kotlinx.coroutines)

	// The CLI library
	implementation(libs.clikt)

	// Logging
	implementation(libs.slf4j.simple)
}
