plugins {
	id("java")
	id("maven")
}

dependencies {
	implementation("org.java-websocket:Java-WebSocket:1.4.0")
	implementation("com.google.code.gson:gson:2.8.2")
	testImplementation("junit:junit:4.12")
}
