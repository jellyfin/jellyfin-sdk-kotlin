plugins {
	id("kotlin")
}

dependencies {
	implementationProject(":jellyfin-model")

	// HTTP
	implementation(Dependencies.KotlinX.coroutinesCore)
	implementation(Dependencies.Ktor.okhttp)
	implementation(Dependencies.Ktor.serialization)

	// Logging
	implementation(Dependencies.Slf4j.api)
	testImplementation(Dependencies.Slf4j.simple)

	// Unit testing
	testImplementation(Dependencies.Kotlin.Test.junit)
}

kotlin {
	explicitApiWarning()
}

sourceSets.getByName("main").java.srcDir("src/main/kotlin-generated")

val sourcesJar by tasks.creating(Jar::class) {
	archiveClassifier.set("sources")

	from(sourceSets.getByName("main").allSource)
}

publishing.publications.create<MavenPublication>("default") {
	from(components["kotlin"])

	artifact(sourcesJar)
}
