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
				implementation(libs.ktor.core)
				implementation(libs.ktor.serialization)

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
				// Testing
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
