import org.jetbrains.kotlin.gradle.dsl.JvmTarget

plugins {
	kotlin("multiplatform")
	alias(libs.plugins.dokka)
}

kotlin {
	explicitApi()

	jvm {
		compilerOptions {
			jvmTarget = JvmTarget.JVM_1_8
		}
	}

	jvmToolchain(21)

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

				implementation(libs.kotlinx.serialization.core)
				implementation(libs.ktor.core)

				implementation(libs.kotlin.logging)
			}
		}

		val jvmMain by getting {
			dependencies {
				implementation(libs.ktor.okhttp)
			}
		}

		val commonTest by getting {
			dependencies {
				implementation(projects.testutils)
			}
		}
	}
}

enablePublishing {
	val javadocJar by tasks.registering(Jar::class) {
		dependsOn(tasks.dokkaHtml)
		from(tasks.dokkaHtml.flatMap { it.outputDirectory })
		archiveClassifier.set("javadoc")
	}

	publications.withType<MavenPublication> {
		artifact(javadocJar)
	}
}
