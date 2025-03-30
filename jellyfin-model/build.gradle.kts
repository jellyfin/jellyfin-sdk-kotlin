import org.jetbrains.kotlin.gradle.dsl.JvmTarget

plugins {
	kotlin("multiplatform")
	alias(libs.plugins.kotlin.serialization)
	alias(libs.plugins.dokka)
}

kotlin {
	explicitApi()

	jvm {
		compilerOptions{
			jvmTarget = JvmTarget.JVM_1_8
		}
	}

	jvmToolchain(21)

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
	val javadocJar by tasks.registering(Jar::class) {
		dependsOn(tasks.dokkaHtml)
		from(tasks.dokkaHtml.flatMap { it.outputDirectory })
		archiveClassifier.set("javadoc")
	}

	publications.withType<MavenPublication> {
		artifact(javadocJar)
	}
}
