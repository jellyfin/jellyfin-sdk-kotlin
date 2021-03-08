plugins {
	id("kotlin-multiplatform")
	kotlin("plugin.serialization") version Dependencies.Kotlin.version
}

kotlin {
	explicitApi()

	jvm {
		withJava()
	}

	sourceSets {
		val commonMain by getting {
			kotlin.srcDir("src/commonMain/kotlin-generated")

			dependencies {
				compileOnly(Dependencies.KotlinX.serializationJson)
			}
		}

		val commonTest by getting {
			dependencies {
				// Testing
				implementation(Dependencies.Kotlin.Test.junit)
				implementation(Dependencies.KotlinX.serializationJson)
			}
		}
	}
}

// TODO
//val sourcesJar by tasks.creating(Jar::class) {
//	archiveClassifier.set("sources")
//
//	from(sourceSets.getByName("main").allSource)
//}
//
//publishing.publications.create<MavenPublication>("default") {
//	from(components["kotlin"])
//
//	artifact(sourcesJar)
//}
