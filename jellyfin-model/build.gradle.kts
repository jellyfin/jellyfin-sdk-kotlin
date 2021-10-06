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

val javadocJar by tasks.creating(Jar::class) {
	dependsOn(tasks.getByName("dokkaHtml"))
	archiveClassifier.set("javadoc")
	from("$buildDir/dokka/html")
}

publishing.publications.withType<MavenPublication> {
	artifact(javadocJar)
}
