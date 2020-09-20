plugins {
	id("kotlin")
}

dependencies {
	apiProject(":api")
	apiProject(":model")

	implementation(Dependencies.Kotlin.stdlib)
	implementation(Dependencies.KotlinX.coroutinesCore)

	implementation(Dependencies.javaWebSocket)
	implementation(Dependencies.gson)

	testImplementation(Dependencies.junit)
}

val sourcesJar by tasks.creating(Jar::class) {
	archiveClassifier.set("sources")

	from(sourceSets.getByName("main").allSource)
}

publishing.publications.create<MavenPublication>("default") {
	from(components["java"])

	artifact(sourcesJar)
}
