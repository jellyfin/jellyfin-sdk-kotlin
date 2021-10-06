plugins {
	kotlin("multiplatform")
	id("com.android.library")
}

kotlin {
	explicitApi()

	jvm()
	android {
		publishAllLibraryVariants()
	}

	sourceSets {
		all {
			languageSettings {
				progressiveMode = true
			}
		}

		val commonMain by getting {
			dependencies {
				api(projects.jellyfinApi)
				api(projects.jellyfinModel)

				implementation(libs.kotlinx.coroutines)
				implementation(libs.kotlinx.serialization.json)

				api(libs.ktor.http)

				// Logging
				implementation(libs.kotlin.logging)
			}
		}

		val commonTest by getting {
			dependencies {
				implementation(projects.testutils)
			}
		}

		// Not actually used due too IntelliJ issue
		// sub-sourcesets depend on source folder directly
		val jvmCommonMain by creating {
			dependsOn(commonMain)
		}

		val jvmMain by getting {
			// dependsOn(jvmCommonMain)
			kotlin.srcDir("src/jvmCommonMain/kotlin")
		}

		val androidMain by getting {
			// dependsOn(jvmCommonMain)
			kotlin.srcDir("src/jvmCommonMain/kotlin")

			dependencies {
				implementation(libs.androidx.core)
				implementation(libs.androidx.annotation)
			}
		}
	}
}

android {
	compileSdk = 30

	sourceSets["main"].manifest.srcFile("src/androidMain/AndroidManifest.xml")

	defaultConfig {
		minSdk = 19
		targetSdk = 30
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
		isAbortOnError = false
		sarifReport = true
	}
}

val javadocJar by tasks.creating(Jar::class) {
	// FIXME: Dokka is failing for this module - temporarily disabled
	// dependsOn(tasks.getByName("dokkaHtml"))
	archiveClassifier.set("javadoc")
	// from("$buildDir/dokka/html")
}

publishing.publications.withType<MavenPublication> {
	artifact(javadocJar)
}
