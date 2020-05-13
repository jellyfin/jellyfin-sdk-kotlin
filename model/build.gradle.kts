plugins {
	id("java-library")
	id("maven-publish")
}

val sourcesJar by tasks.creating(Jar::class) {
	archiveClassifier.set("sources")

	from(sourceSets.getByName("main").allSource)
}

publishing.publications.create<MavenPublication>("default") {
	from(components["java"])

	artifact(sourcesJar)
}
