import org.jetbrains.kotlin.gradle.dsl.JvmTarget

plugins {
	kotlin("multiplatform")
}

kotlin {
	explicitApi()

	jvm{
		compilerOptions{
			jvmTarget = JvmTarget.JVM_1_8
		}
	}
	jvm()

	jvmToolchain(21)

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
				api(libs.kotest.framework.engine)
				api(libs.kotest.assertions)
			}
		}

		val jvmMain by getting {
			dependencies {
				implementation(libs.slf4j.simple)
				api(libs.kotest.runner.junit5)
			}
		}
	}
}
