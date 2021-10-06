plugins {
	kotlin("multiplatform")
}

kotlin {
	explicitApi()

	jvm()

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
				implementation(libs.kotlin.test.annotations.common)
				implementation(libs.kotlin.test.common)
			}
		}

		val jvmTest by getting {
			dependencies {
				implementation(libs.kotlin.test.junit)
				implementation(libs.slf4j.simple)
			}
		}
	}
}

val javadocJar by tasks.creating(Jar::class) {
	dependsOn(tasks.getByName("dokkaHtml"))
	archiveClassifier.set("javadoc")
	from("$buildDir/dokka/html")
}

publishing.publications.withType<MavenPublication> {
	artifact(javadocJar)
}
