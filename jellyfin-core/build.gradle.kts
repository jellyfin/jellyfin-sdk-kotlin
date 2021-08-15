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
			kotlin.srcDir("src/main/kotlin")

			dependencies {
				api(projects.jellyfinApi)
				api(projects.jellyfinModel)

				implementation(libs.kotlinx.coroutines)
				implementation(libs.kotlinx.serialization.json)

				api(libs.ktor.http)

				// Logging
				implementation(libs.kotlin.logging)
			}
		}

		val commonTest by getting {
			dependencies {
				implementation(libs.slf4j.simple)
				implementation(libs.kotlin.test.junit)
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
