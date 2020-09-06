plugins {
	id("kotlin")
}

dependencies {
	implementation(kotlin("stdlib-jdk7"))

	api(project(":model"))

	// HTTP
	implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.3.9")
	implementation("io.ktor:ktor-client-okhttp:1.3.2")
	implementation("io.ktor:ktor-client-gson:1.3.2")

	// Testing
	testImplementation(kotlin("test-junit"))
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
