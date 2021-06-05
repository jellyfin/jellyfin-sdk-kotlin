plugins {
	id("application")
}

application {
	mainClass.set("org.jellyfin.sample.cli.Main")
}

dependencies {
	// Depend on the library project
	implementation(projects.jellyfinCore)

	// Logging
	implementation(libs.slf4j.simple)
}
