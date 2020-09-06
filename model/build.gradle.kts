plugins {
	id("kotlin")
}

dependencies {
	implementation(kotlin("stdlib-jdk7"))
}

sourceSets.getByName("main").java.srcDir("src/main/kotlin-generated")

val sourcesJar by tasks.creating(Jar::class) {
	archiveClassifier.set("sources")

	from(sourceSets.getByName("main").allSource)
}

publishing.publications.create<MavenPublication>("default") {
	from(components["java"]) //TODO: Remove when deleting java sources

	artifact(sourcesJar)
}
