plugins {
	id("kotlin")
}

dependencies {
	implementation(Dependencies.Kotlin.stdlib)

	apiProject(":model")

	// HTTP
	implementation(Dependencies.KotlinX.coroutinesCore)
	implementation(Dependencies.Ktor.okhttp)
	implementation(Dependencies.Ktor.gson)

	// Testing
	testImplementation(Dependencies.Kotlin.Test.junit)
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
