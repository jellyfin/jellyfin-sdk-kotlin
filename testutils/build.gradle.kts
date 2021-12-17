plugins {
	kotlin("multiplatform")
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
			dependencies {
				api(libs.kotlinx.coroutines)
				api(libs.kotlinx.coroutines.test)
				api(libs.kotlin.test.common)
				api(libs.kotlin.test.annotations.common)
			}
		}

		val jvmMain by getting {
			dependencies {
				implementation(libs.slf4j.simple)
				api(libs.kotlin.test.junit)
			}
		}
	}
}
