plugins {
	id("java-library")
}

val sourcesJar by tasks.creating(Jar::class) {
	archiveClassifier.set("sources")

	from(sourceSets.getByName("main").allSource)
}

publishing {
	publications {
		create<MavenPublication>("maven") {
			from(components["java"])

			artifact(sourcesJar)
		}
	}
}
