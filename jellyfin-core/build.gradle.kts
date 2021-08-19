plugins {
	id("kotlin")
}

dependencies {
	api(projects.jellyfinApi)
	api(projects.jellyfinModel)

	implementation(libs.kotlinx.coroutines)
	implementation(libs.kotlinx.serialization.json)

	api(libs.ktor.http)

	// Logging
	implementation(libs.kotlin.logging)
	testImplementation(libs.slf4j.simple)

	// Testing
	testImplementation(libs.kotlin.test.junit)
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
