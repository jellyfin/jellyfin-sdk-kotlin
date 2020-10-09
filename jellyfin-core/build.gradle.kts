plugins {
	id("kotlin")
}

dependencies {
	apiProject(":jellyfin-api")
	apiProject(":jellyfin-model")

	implementation(Dependencies.KotlinX.coroutinesCore)
	implementation(Dependencies.KotlinX.serializationJson)

	implementation(Dependencies.Ktor.http)

	// Logging
	implementation(Dependencies.Slf4j.api)
	testImplementation(Dependencies.Slf4j.simple)

	// Testing
	testImplementation(Dependencies.Kotlin.Test.junit)
}

val sourcesJar by tasks.creating(Jar::class) {
	archiveClassifier.set("sources")

	from(sourceSets.getByName("main").allSource)
}

publishing.publications.create<MavenPublication>("default") {
	from(components["kotlin"])

	artifact(sourcesJar)
}
