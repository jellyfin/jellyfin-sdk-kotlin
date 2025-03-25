plugins {
	kotlin("multiplatform")
	alias(libs.plugins.dokka)
}

kotlin {
	explicitApi()

	jvm()

	jvmToolchain(8)

	sourceSets {
		all {
			languageSettings {
				progressiveMode = true
			}
		}

		val commonMain by getting {
			kotlin.srcDir("src/commonMain/kotlin-generated")

			dependencies {
				implementation(projects.jellyfinModel)

				implementation(libs.kotlinx.coroutines)
				implementation(libs.kotlinx.serialization.json)
				implementation(libs.ktor.http)

				implementation(libs.kotlin.logging)
			}
		}

		val jvmMain by getting {
			dependencies {
				implementation(libs.okhttp)
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
