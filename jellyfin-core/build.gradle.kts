import org.jetbrains.kotlin.gradle.dsl.JvmTarget
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
	kotlin("multiplatform")
	id("com.android.library")
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

	androidTarget {
		publishLibraryVariants("release", "debug")
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
			"-Xexpect-actual-classes",
		)
	}

	applyDefaultHierarchyTemplate()

	sourceSets {
		commonMain {
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
			dependsOn(commonMain.get())
		}

		jvmMain {
			dependsOn(jvmCommonMain)
		}

		androidMain {
			dependsOn(jvmCommonMain)
		}

		commonTest {
			dependencies {
				implementation(libs.kotlinx.coroutines)
				implementation(libs.kotlinx.coroutines.test)
				implementation(libs.kotest.framework.engine)
				implementation(libs.kotest.assertions)
			}
		}

		val jvmCommonTest by creating {
			dependsOn(commonTest.get())
			dependencies {
				implementation(libs.slf4j.simple)
				implementation(libs.kotest.runner.junit5)
			}
		}

		jvmTest {
			dependsOn(jvmCommonTest)
		}

		androidUnitTest {
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
		release {
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
