import org.jetbrains.kotlin.gradle.dsl.JvmTarget
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
	alias(libs.plugins.kotlin.multiplatform)
	alias(libs.plugins.dokka)
	alias(libs.plugins.animalsniffer)
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
				implementation(projects.jellyfinModel)

				implementation(libs.kotlinx.coroutines)
				implementation(libs.kotlinx.serialization.json)

				implementation(libs.kotlin.logging)
			}
		}

		val jvmMain by getting {
			dependencies {
				implementation(libs.okhttp)
			}
		}

		val commonTest by getting {
			dependencies {
				implementation(libs.kotlinx.coroutines)
				implementation(libs.kotlinx.coroutines.test)
				implementation(libs.kotest.framework.engine)
				implementation(libs.kotest.assertions)
				implementation(libs.kotest.assertions.table)
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
		dependsOn(tasks.dokkaGeneratePublicationHtml)
		from(tasks.dokkaGeneratePublicationHtml.flatMap { it.outputDirectory })
		archiveClassifier.set("javadoc")
	}

	publications.withType<MavenPublication> {
		artifact(javadocJar)
	}
}

dependencies.signature(libs.gummybears.api19) {
	artifact {
		classifier = "coreLib2"
		type = "signature"
	}
}

tasks.named<KotlinCompile>("compileTestKotlinJvm") {
	compilerOptions.jvmTarget = JvmTarget.JVM_11
}
