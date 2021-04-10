plugins {
	id("kotlin")
}

dependencies {
	implementation(projects.jellyfinModel)

	// HTTP
	implementation(libs.kotlinx.coroutines)
	implementation(libs.ktor.okhttp)
	implementation(libs.ktor.serialization)

	// Logging
	implementation(libs.slf4j.api)
	testImplementation(libs.slf4j.simple)

	// Unit testing
	testImplementation(libs.kotlin.test.junit)
}

kotlin {
	explicitApi()
}

sourceSets.getByName("main").java.srcDir("src/main/kotlin-generated")

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
