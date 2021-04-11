plugins {
	id("kotlin")
	kotlin("plugin.serialization") version Plugins.Versions.kotlin
}

dependencies {
	compileOnly(libs.kotlinx.serialization.json)

	// Testing
	testImplementation(libs.kotlin.test.junit)
	testImplementation(libs.kotlinx.serialization.json)
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
