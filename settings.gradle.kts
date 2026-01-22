enableFeaturePreview("TYPESAFE_PROJECT_ACCESSORS")

rootProject.name = "jellyfin-sdk-kotlin"

// Core
include(":jellyfin-core")
include(":jellyfin-model")
include(":jellyfin-api")
include(":jellyfin-api-okhttp")

// Code generation
include(":openapi-generator")

// Samples
include(":samples:kotlin-cli")

pluginManagement {
	repositories {
		gradlePluginPortal()
		mavenCentral()
		google()
	}
}
