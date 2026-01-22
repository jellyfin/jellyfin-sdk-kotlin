import org.jetbrains.kotlin.gradle.dsl.JvmTarget
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
	alias(libs.plugins.kotlin.multiplatform)
	alias(libs.plugins.dokka)
	alias(libs.plugins.android.library)
	alias(libs.plugins.animalsniffer)
}

kotlin {
	explicitApi()

	jvm {
		compilerOptions {
			jvmTarget = JvmTarget.JVM_1_8
		}
	}

	androidLibrary {
		namespace = "org.jellyfin.sdk"
		compileSdk = libs.versions.android.sdk.get().toInt()
		minSdk = 19

		compilerOptions {
			jvmTarget = JvmTarget.JVM_1_8
		}

		withHostTest {}

		@Suppress("UnstableApiUsage")
		optimization {
			minify = false

			consumerKeepRules.apply {
				publish = true
				file("proguard-rules.pro")
			}
		}

		lint {
			lintConfig = file("$rootDir/android-lint.xml")
			abortOnError = false
			sarifReport = true
		}
	}

	jvmToolchain(21)

	applyDefaultHierarchyTemplate()

	sourceSets {
		all {
			languageSettings {
				progressiveMode = true
			}
		}

		val commonMain by getting {
			dependencies {
				api(projects.jellyfinApi)
				api(projects.jellyfinApiOkhttp)
				api(projects.jellyfinModel)

				implementation(libs.kotlinx.coroutines)
				implementation(libs.kotlinx.serialization.json)

				implementation(libs.okhttp)

				// Logging
				implementation(libs.kotlin.logging)
			}
		}

		val jvmCommonMain by creating {
			dependsOn(commonMain)
		}

		val jvmMain by getting {
			dependsOn(jvmCommonMain)
		}

		val androidMain by getting {
			dependsOn(jvmCommonMain)
		}

		val commonTest by getting {
			dependencies {
				implementation(libs.kotlinx.coroutines)
				implementation(libs.kotlinx.coroutines.test)
				implementation(libs.kotest.framework.engine)
				implementation(libs.kotest.assertions)
			}
		}

		val jvmCommonTest by creating {
			dependsOn(commonTest)
			dependencies {
				implementation(libs.slf4j.simple)
				implementation(libs.kotest.runner.junit5)
			}
		}

		val jvmTest by getting {
			dependsOn(jvmCommonTest)
		}

		val androidHostTest by getting {
			dependsOn(jvmCommonTest)
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
