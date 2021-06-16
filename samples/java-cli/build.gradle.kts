plugins {
	id("application")
}

application {
	mainClass.set("org.jellyfin.sample.cli.Main")
}

java {
	sourceCompatibility = JavaVersion.VERSION_1_8
	targetCompatibility = JavaVersion.VERSION_1_8
}

dependencies {
	// Depend on the library project
	implementation(projects.jellyfinCore)

	// Logging
	implementation(libs.slf4j.simple)
}
