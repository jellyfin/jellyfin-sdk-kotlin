plugins {
	kotlin("jvm")
	id("application")

	id("de.undercouch.download")
}

application {
	mainClassName = "org.jellyfin.openapi.MainKt"
}

dependencies {
	// Reading OpenAPI
	implementation(Dependencies.swaggerParser)

	// Capitalization helper
	implementation(Dependencies.kasechange)

	// Kotlin code generation
	implementation(Dependencies.kotlinPoet)

	// Needed for the kotlinx.serialization annotations
	implementation(Dependencies.KotlinX.serializationJson)

	// Needed for the ByteReadChannel class
	implementation(Dependencies.Ktor.io)

	// Dependency Injection
	implementation(Dependencies.Koin.core)

	// Testing
	testImplementation(Dependencies.Kotlin.Test.junit)
}

val openApiFile = file("../openapi.json")
tasks.register("generateSources", JavaExec::class) {
	main = application.mainClassName
	classpath = sourceSets.main.get().runtimeClasspath

	args = mapOf(
		"openApiFile" to openApiFile,
		"apiOutputDir" to file("../jellyfin-api/src/main/kotlin-generated"),
		"modelsOutputDir" to file("../jellyfin-model/src/main/kotlin-generated")
	).map { listOf("--${it.key}", it.value.toString()) }.flatten()
}

arrayOf("stable", "unstable").forEach { flavor ->
	tasks.register("downloadApiSpec${flavor.capitalize()}", de.undercouch.gradle.tasks.download.Download::class) {
		src("https://repo.jellyfin.org/releases/openapi/jellyfin-openapi-${flavor}.json")
		dest(openApiFile)
	}

	tasks.register("updateApiSpec${flavor.capitalize()}") {
		dependsOn("downloadApiSpec${flavor.capitalize()}", "generateSources")
	}
}
