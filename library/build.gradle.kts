plugins {
	id("kotlin")
}

dependencies {
	apiProject(":api")
	apiProject(":model")

	implementation(Dependencies.Kotlin.stdlib)
	implementation(Dependencies.KotlinX.coroutinesCore)
	implementation(Dependencies.KotlinX.serializationCore)

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
	from(components["java"])

	artifact(sourcesJar)
}
