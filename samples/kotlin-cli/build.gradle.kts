plugins {
	kotlin("jvm")
	id("application")
}

application {
	mainClassName = "org.jellyfin.sample.cli.MainKt"
}

repositories {
	jcenter()

	// Repository needed for kotlinx-cli
	maven("https://kotlin.bintray.com/kotlinx")
}

dependencies {
	// Depend on the library project
	implementationProject(":jellyfin-core")

	// Use Kotlin stdlib
	implementation(Dependencies.Kotlin.stdlib)

	// Use Kotlin coroutines to interact with the library
	implementation(Dependencies.KotlinX.coroutinesCore)

	// The CLI library
	implementation(Dependencies.KotlinX.cli)

	// Logging
	implementation(Dependencies.Slf4j.simple)
}

tasks.withType<org.jetbrains.kotlin.gradle.tasks.KotlinCompile>().all {
	kotlinOptions.freeCompilerArgs += "-Xopt-in=kotlinx.cli.ExperimentalCli"
}
