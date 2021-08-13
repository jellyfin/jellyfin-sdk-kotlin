plugins {
	kotlin("multiplatform")
	kotlin("plugin.serialization") version Plugins.Versions.kotlin
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
				compileOnly(libs.kotlinx.serialization.json)
			}
		}

		val commonTest by getting {
			dependencies {
				// Testing
				implementation(libs.kotlin.test.junit)
				implementation(libs.kotlinx.serialization.json)
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
