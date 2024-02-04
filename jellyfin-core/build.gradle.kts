plugins {
	kotlin("multiplatform")
	id("com.android.library")
}

kotlin {
	explicitApi()

	jvm()
	androidTarget {
		publishAllLibraryVariants()
	}

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
				api(projects.jellyfinApiKtor)
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

		val jvmCommonMain by creating {
			dependsOn(commonMain)
		}

		val jvmMain by getting {
			 dependsOn(jvmCommonMain)
		}

		val androidMain by getting {
			 dependsOn(jvmCommonMain)
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
	val javadocJar by tasks.creating(Jar::class) {
		dependsOn(tasks.getByName("dokkaHtml"))
		archiveClassifier.set("javadoc")
		from("$buildDir/dokka/html")
	}

	publications.withType<MavenPublication> {
		artifact(javadocJar)
	}
}
