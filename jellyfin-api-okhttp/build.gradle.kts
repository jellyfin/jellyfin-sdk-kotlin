import org.jetbrains.kotlin.gradle.dsl.JvmTarget
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
	kotlin("multiplatform")
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

	jvmToolchain {
		languageVersion = JavaLanguageVersion.of(21)
		vendor = JvmVendorSpec.ADOPTIUM
	}

	compilerOptions {
		progressiveMode = true
	}

	sourceSets {
		commonMain {
			dependencies {
				implementation(projects.jellyfinApi)
				implementation(projects.jellyfinModel)

				implementation(libs.kotlinx.coroutines)
				implementation(libs.kotlinx.serialization.core)
				implementation(libs.okhttp)

				implementation(libs.kotlin.logging)
			}
		}

		commonTest {
			dependencies {
				implementation(libs.kotlinx.coroutines)
				implementation(libs.kotlinx.coroutines.test)
				implementation(libs.kotest.framework.engine)
				implementation(libs.kotest.assertions)
			}
		}

		jvmTest {
			dependencies {
				implementation(libs.slf4j.simple)
				implementation(libs.kotest.runner.junit5)
			}
		}
	}
}

enablePublishing {
	val javadocJar by tasks.registering(Jar::class) {
		dependsOn(tasks.named("dokkaGeneratePublicationHtml"))
		from(layout.buildDirectory.dir("dokka/html"))
		archiveClassifier = "javadoc"
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

// Compile tests with JVM 11 (required by Kotest 6.x)
tasks.named<KotlinCompile>("compileTestKotlinJvm") {
	compilerOptions {
		jvmTarget = JvmTarget.JVM_11
	}
}

