plugins {
	id("java-library")
	id("maven")
}

dependencies {
	api(project(":model"))

	implementation("org.java-websocket:Java-WebSocket:1.4.1")
	implementation("com.google.code.gson:gson:2.8.6")
	testImplementation("junit:junit:4.12")
}
