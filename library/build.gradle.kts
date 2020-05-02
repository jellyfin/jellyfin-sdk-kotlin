plugins {
	id("java-library")
}

dependencies {
	api(project(":model"))

	implementation("org.java-websocket:Java-WebSocket:1.4.1")
	implementation("com.google.code.gson:gson:2.8.6")

	testImplementation("junit:junit:4.12")
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
