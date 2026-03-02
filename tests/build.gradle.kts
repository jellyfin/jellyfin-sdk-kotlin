plugins {
    kotlin("jvm")
    id("application")
}

application {
    mainClass.set("org.jellyfin.sdk.tests.IntegrationTestKt")
}

dependencies {
    implementation(projects.jellyfinCore)
    implementation(libs.kotlinx.coroutines)
    implementation(libs.slf4j.simple)
}
