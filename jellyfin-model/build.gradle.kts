plugins {
	kotlin("multiplatform")
	alias(libs.plugins.kotlin.serialization)
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
				implementation(libs.kotlin.test.junit)
				implementation(libs.kotlinx.serialization.json)
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
