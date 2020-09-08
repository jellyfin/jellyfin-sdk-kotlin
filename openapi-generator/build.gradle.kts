plugins {
	kotlin("jvm")
	id("application")
}

application {
	mainClassName = "org.jellyfin.openapi.MainKt"
}

dependencies {
	implementation(kotlin("stdlib-jdk8"))

	// Reading OpenAPI
	implementation("io.swagger.parser.v3:swagger-parser:2.0.19")

	// Kotlin code generation
	implementation("com.squareup:kotlinpoet:1.6.0")

	// Dependency Injection
	implementation("org.koin:koin-core:2.1.6")

	// Testing
	testImplementation(kotlin("test-junit"))
}

task("generateSources", JavaExec::class) {
	main = application.mainClassName
	classpath = sourceSets.main.get().runtimeClasspath

	args = mapOf(
		"openApiFile" to file("../openapi.json"),
		"apiOutputDir" to file("../api/src/main/kotlin-generated"),
		"modelsOutputDir" to file("../model/src/main/kotlin-generated")
	).map { listOf("--${it.key}", it.value.toString()) }.flatten()
}
