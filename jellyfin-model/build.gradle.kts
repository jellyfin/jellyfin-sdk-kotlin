import org.jetbrains.kotlin.gradle.dsl.JvmTarget
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
	kotlin("multiplatform")
	alias(libs.plugins.kotlin.serialization)
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
		freeCompilerArgs.addAll(
			"-opt-in=kotlinx.serialization.ExperimentalSerializationApi",
			"-Xexpect-actual-classes",
		)
	}

	sourceSets {
		commonMain {
			kotlin.srcDir("src/commonMain/kotlin-generated")

			dependencies {
				implementation(libs.kotlinx.serialization.json)
			}
		}

		commonTest {
			dependencies {
				implementation(libs.kotlinx.coroutines)
				implementation(libs.kotlinx.coroutines.test)
				implementation(libs.kotest.framework.engine)
				implementation(libs.kotest.assertions)
				implementation(libs.kotlinx.serialization.json)
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

