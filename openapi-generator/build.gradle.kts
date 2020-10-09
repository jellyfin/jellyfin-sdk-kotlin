plugins {
	kotlin("jvm")
	id("application")
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

	// Dependency Injection
	implementation(Dependencies.Koin.core)

	// Testing
	testImplementation(Dependencies.Kotlin.Test.junit)
}

task("generateSources", JavaExec::class) {
	main = application.mainClassName
	classpath = sourceSets.main.get().runtimeClasspath

	args = mapOf(
		"openApiFile" to file("../openapi.json"),
		"apiOutputDir" to file("../jellyfin-api/src/main/kotlin-generated"),
		"modelsOutputDir" to file("../jellyfin-model/src/main/kotlin-generated")
	).map { listOf("--${it.key}", it.value.toString()) }.flatten()
}
