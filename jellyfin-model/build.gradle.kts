import org.jetbrains.kotlin.gradle.dsl.JvmTarget

plugins {
	kotlin("multiplatform")
	alias(libs.plugins.kotlin.serialization)
	alias(libs.plugins.dokka)
}

kotlin {
	explicitApi()

	jvm {
		compilerOptions {
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
				implementation(libs.kotlinx.serialization.json)
			}
		}

		val commonTest by getting {
			dependencies {
				implementation(libs.kotlinx.coroutines)
				implementation(libs.kotlinx.coroutines.test)
				implementation(libs.kotest.framework.engine)
				implementation(libs.kotest.assertions)
				implementation(libs.kotlinx.serialization.json)
			}
		}

		val jvmTest by getting {
			dependencies {
				implementation(libs.slf4j.simple)
				implementation(libs.kotest.runner.junit5)
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
