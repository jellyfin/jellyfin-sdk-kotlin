plugins {
	kotlin("jvm")
	id("application")
}

application {
	mainClassName = "org.jellyfin.sample.cli.MainKt"
}

dependencies {
	// Depend on the library project
	implementationProject(":jellyfin-core")

	// Use Kotlin stdlib
	implementation(Dependencies.Kotlin.stdlib)

	// Use Kotlin coroutines to interact with the library
	implementation(Dependencies.KotlinX.coroutinesCore)

	// The CLI library
	implementation(Dependencies.clikt)

	// Logging
	implementation(Dependencies.Slf4j.simple)
}
