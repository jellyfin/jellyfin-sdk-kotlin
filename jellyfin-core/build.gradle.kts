plugins {
	id("kotlin")
}

dependencies {
	api(projects.jellyfinApi)
	api(projects.jellyfinModel)

	implementation(Dependencies.KotlinX.coroutinesCore)
	implementation(Dependencies.KotlinX.serializationJson)

	api(Dependencies.Ktor.http)

	// Logging
	implementation(Dependencies.Slf4j.api)
	testImplementation(Dependencies.Slf4j.simple)

	// Testing
	testImplementation(Dependencies.Kotlin.Test.junit)
}

kotlin {
	explicitApi()
}

val sourcesJar by tasks.creating(Jar::class) {
	archiveClassifier.set("sources")

	from(sourceSets.getByName("main").allSource)
}

val javadocJar by tasks.creating(Jar::class) {
	dependsOn(tasks.getByName("dokkaJavadoc"))
	archiveClassifier.set("javadoc")
	from("$buildDir/dokka/javadoc")
}

publishing.publications.create<MavenPublication>("default") {
	from(components["kotlin"])

	artifact(sourcesJar)
	artifact(javadocJar)
}
