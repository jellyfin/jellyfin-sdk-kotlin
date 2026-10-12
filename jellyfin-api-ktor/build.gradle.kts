import org.jetbrains.kotlin.gradle.dsl.JvmTarget

plugins {
	alias(libs.plugins.kotlin.multiplatform)
	alias(libs.plugins.dokka)
	alias(libs.plugins.ksp)
	alias(libs.plugins.kotest)
}

kotlin {
	explicitApi()

	jvm {
		compilerOptions {
			jvmTarget = JvmTarget.JVM_1_8
		}
	}

	iosArm64()
	iosSimulatorArm64()
	iosX64()

	jvmToolchain(21)

	applyDefaultHierarchyTemplate()

	sourceSets {
		all {
			languageSettings {
				progressiveMode = true
			}
		}

		val commonMain by getting {
			dependencies {
				implementation(projects.jellyfinApi)
				implementation(projects.jellyfinModel)

				implementation(libs.kotlinx.coroutines)
				implementation(libs.kotlinx.serialization.core)
				api(libs.ktor.client.core)
				implementation(libs.ktor.client.websockets)

				implementation(libs.kotlin.logging)
			}
		}

		val jvmMain by getting {
			dependencies {
				implementation(libs.ktor.client.okhttp)
			}
		}

		val iosMain by getting {
			dependencies {
				implementation(libs.ktor.client.darwin)
			}
		}

		val commonTest by getting {
			dependencies {
				implementation(libs.kotlinx.coroutines)
				implementation(libs.kotlinx.coroutines.test)
				implementation(libs.kotest.framework.engine)
				implementation(libs.kotest.assertions)
				implementation(libs.ktor.client.mock)
			}
		}

		val jvmTest by getting {
			dependencies {
				implementation(libs.slf4j.simple)
				implementation(libs.kotest.runner.junit5)
			}
		}
	}
}

enablePublishing {
	val javadocJar by tasks.registering(Jar::class) {
		dependsOn(tasks.dokkaGeneratePublicationHtml)
		from(tasks.dokkaGeneratePublicationHtml.flatMap { it.outputDirectory })
		archiveClassifier.set("javadoc")
	}

	publications.withType<MavenPublication> {
		artifact(javadocJar)
	}
}
