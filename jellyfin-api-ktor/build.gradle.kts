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
	val javadocJar by tasks.creating(Jar::class) {
		dependsOn(tasks.getByName("dokkaHtml"))
		archiveClassifier.set("javadoc")
		from("$buildDir/dokka/html")
	}

	publications.withType<MavenPublication> {
		artifact(javadocJar)
	}
}
