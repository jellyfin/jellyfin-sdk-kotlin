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
			// TODO move to commonMain folder
			kotlin.srcDir("src/main/kotlin-generated")
			kotlin.srcDir("src/main/kotlin")

			dependencies {
				implementation(projects.jellyfinModel)

				implementation(libs.kotlinx.coroutines)
				implementation(libs.ktor.core)
				implementation(libs.ktor.serialization)

				implementation(libs.kotlinlogging)
			}
		}

		val jvmMain by getting {
			dependencies {
				implementation(libs.ktor.okhttp)
			}
		}

		val commonTest by getting {
			dependencies {
				// Testing
				implementation(libs.kotlin.test.junit)
				implementation(libs.slf4j.simple)
			}
		}
	}
}

val javadocJar by tasks.creating(Jar::class) {
	dependsOn(tasks.getByName("dokkaJavadoc"))
	archiveClassifier.set("javadoc")
	from("$buildDir/dokka/javadoc")
}

publishing.publications.withType<MavenPublication> {
	artifact(javadocJar)
}
