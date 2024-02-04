plugins {
	kotlin("multiplatform")
	alias(libs.plugins.kotlin.serialization)
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
				compileOnly(libs.kotlinx.serialization.json)
			}
		}

		val commonTest by getting {
			dependencies {
				implementation(projects.testutils)
				implementation(libs.kotlinx.serialization.json)
			}
		}
	}
}

enablePublishing {
	val javadocJar by tasks.creating(Jar::class) {
		dependsOn(tasks.dokkaHtml)
		from(tasks.dokkaHtml.flatMap { it.outputDirectory })
		archiveClassifier.set("html-docs")
	}

	publications.withType<MavenPublication> {
		artifact(javadocJar)
	}
}
