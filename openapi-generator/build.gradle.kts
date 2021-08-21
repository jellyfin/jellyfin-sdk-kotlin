import de.undercouch.gradle.tasks.download.Download

plugins {
	kotlin("jvm")
	id("application")
	id("de.undercouch.download")
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
	implementation(libs.swaggerParser)

	// Capitalization helper
	implementation(libs.kasechange)

	// Kotlin code generation
	implementation(libs.kotlinpoet)

	// Dependency Injection
	implementation(libs.koin)

	// Testing
	testImplementation(libs.kotlin.test.junit)
}

val defaultConfig = mapOf(
	"openApiFile" to file("../openapi.json"),
	"apiOutputDir" to file("../jellyfin-api/src/main/kotlin-generated"),
	"modelsOutputDir" to file("../jellyfin-model/src/main/kotlin-generated")
)

tasks.register("generateSources", JavaExec::class) {
	mainClass.set(application.mainClass)
	classpath = sourceSets.main.get().runtimeClasspath

	args = defaultConfig
		.map { listOf("--${it.key}", it.value.toString()) }
		.flatten()
}

tasks.register("verifySources", JavaExec::class) {
	mainClass.set(application.mainClass)
	classpath = sourceSets.main.get().runtimeClasspath

	args = defaultConfig
		.plus("verify" to "true")
		.map { listOf("--${it.key}", it.value.toString()) }
		.flatten()
}

arrayOf("stable", "unstable").forEach { flavor ->
	tasks.register("downloadApiSpec${flavor.capitalize()}", Download::class) {
		src("https://repo.jellyfin.org/releases/openapi/jellyfin-openapi-${flavor}.json")
		dest(defaultConfig["openApiFile"])
	}

	tasks.register("updateApiSpec${flavor.capitalize()}") {
		dependsOn("downloadApiSpec${flavor.capitalize()}", "generateSources")
	}
}
