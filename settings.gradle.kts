enableFeaturePreview("TYPESAFE_PROJECT_ACCESSORS")

rootProject.name = "jellyfin-sdk-kotlin"

// Core
include(":jellyfin-core")
include(":jellyfin-model")
include(":jellyfin-api")

// Code generation
include(":openapi-generator")

// Samples
include(":samples:kotlin-cli")
include(":samples:java-cli")

// Utilities
include(":testutils")
