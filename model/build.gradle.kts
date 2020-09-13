plugins {
	id("kotlin")
	kotlin("plugin.serialization") version Dependencies.Kotlin.version
}

dependencies {
	implementation(Dependencies.Kotlin.stdlib)
	compileOnly(Dependencies.KotlinX.serializationCore)

	// Testing
	testImplementation(Dependencies.Kotlin.Test.junit)
	testImplementation(Dependencies.KotlinX.serializationCore)
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
