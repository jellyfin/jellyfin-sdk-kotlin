import org.jetbrains.kotlin.gradle.dsl.JvmTarget

plugins {
	kotlin("multiplatform")
	id("com.android.library")
	alias(libs.plugins.dokka)
}

kotlin {
	explicitApi()

	jvm {
		compilerOptions {
			jvmTarget = JvmTarget.JVM_1_8
		}
	}
	androidTarget {
		publishLibraryVariants("release", "debug")
		compilerOptions {
			jvmTarget = JvmTarget.JVM_1_8
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

		val androidUnitTest by getting {
			dependsOn(jvmCommonTest)
		}
	}
}

android {
	namespace = "org.jellyfin.sdk"
	compileSdk = libs.versions.android.sdk.get().toInt()

	defaultConfig {
		minSdk = 19
		multiDexEnabled = true

		consumerProguardFiles("proguard-rules.pro")
	}

	buildTypes {
		getByName("release") {
			isMinifyEnabled = false
		}
	}

	lint {
		lintConfig = file("$rootDir/android-lint.xml")
		abortOnError = false
		sarifReport = true
	}

	publishing {
		multipleVariants {
			withSourcesJar()
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
