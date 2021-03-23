plugins {
	id("kotlin")
	kotlin("plugin.serialization") version Dependencies.Kotlin.version
}

dependencies {
	compileOnly(Dependencies.KotlinX.serializationJson)

	// Testing
	testImplementation(Dependencies.Kotlin.Test.junit)
	testImplementation(Dependencies.KotlinX.serializationJson)
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
