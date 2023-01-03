plugins {
	alias(libs.plugins.download)
	alias(libs.plugins.kotlin.serialization)
	kotlin("jvm")
	id("application")
}

application {
	mainClass.set("org.jellyfin.openapi.MainKt")
}

java {
	sourceCompatibility = JavaVersion.VERSION_1_8
	targetCompatibility = JavaVersion.VERSION_1_8
}

dependencies {
	// Reading OpenAPI
	implementation(libs.swagger.parser)

	// Capitalization helper
	implementation(libs.kasechange)

	// Kotlin code generation
	implementation(libs.kotlinpoet)

	// Compare reporters
	implementation(libs.kotlinx.serialization.json)

	// Dependency Injection
	implementation(libs.koin)

	// CLI
	implementation(libs.clikt)

	// Logging
	implementation(libs.kotlin.logging)
	runtimeOnly(libs.slf4j.simple)

	// Testing
	testImplementation(projects.testutils)
}

val defaultConfig = mapOf(
	"openApiFile" to file("../openapi.json"),
	"apiOutputDir" to file("../jellyfin-api/src/commonMain/kotlin-generated"),
	"modelsOutputDir" to file("../jellyfin-model/src/commonMain/kotlin-generated")
)

tasks.register("generateSources", JavaExec::class) {
	mainClass.set(application.mainClass)
	classpath = sourceSets.main.get().runtimeClasspath

	inputs.file(defaultConfig["openApiFile"])
	outputs.dirs(defaultConfig["apiOutputDir"], defaultConfig["modelsOutputDir"])

	args = listOf("generate") + defaultConfig
		.map { listOf("--${it.key}", it.value.toString()) }
		.flatten()
}

tasks.register("verifySources", JavaExec::class) {
	mainClass.set(application.mainClass)
	classpath = sourceSets.main.get().runtimeClasspath

	args = listOf("verify") + defaultConfig
		.map { listOf("--${it.key}", it.value.toString()) }
		.flatten()
}

arrayOf(
	"stable" to "Stable",
	"stable-pre" to "Prerelease",
	"unstable" to "Unstable",
).forEach { (flavor, flavorPascalCase) ->
	tasks.register("downloadApiSpec${flavorPascalCase}", de.undercouch.gradle.tasks.download.Download::class) {
		src("https://repo.jellyfin.org/releases/openapi/jellyfin-openapi-${flavor}.json")
		dest(defaultConfig["openApiFile"])
		outputs.file(defaultConfig["openApiFile"])
	}

	tasks.register("updateApiSpec${flavorPascalCase}") {
		dependsOn("downloadApiSpec${flavorPascalCase}", "generateSources")
	}
}
