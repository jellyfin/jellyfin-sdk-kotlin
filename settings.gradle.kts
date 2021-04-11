enableFeaturePreview("TYPESAFE_PROJECT_ACCESSORS")
enableFeaturePreview("VERSION_CATALOGS")

// Core
include(":jellyfin-core")
include(":jellyfin-model")
include(":jellyfin-api")

// Platforms
include(":jellyfin-platform-android")

// Code generation
include(":openapi-generator")

// Samples
include(":samples:kotlin-cli")
