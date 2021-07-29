plugins {
	id("com.android.library")
	id("kotlin-android")
}

android {
	compileSdk =  30

	defaultConfig {
		minSdk = 19
		targetSdk = 30
		multiDexEnabled = true

		consumerProguardFiles("proguard-rules.pro")
	}

	kotlinOptions {
		// The Android DSL doesn't support the explicitApi() function
		// so we need to add it to the compiler arguments
		freeCompilerArgs += "-Xexplicit-api=strict"
	}

	sourceSets["main"].java.srcDirs("src/main/kotlin")
	sourceSets["test"].java.srcDirs("src/test/kotlin")

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

// Generate sources jar
tasks.create<Jar>("sourcesArtifact") {
	archiveClassifier.set("sources")

	from(android.sourceSets["main"].java.srcDirs)
}

val javadocJar by tasks.creating(Jar::class) {
	dependsOn(tasks.getByName("dokkaJavadoc"))
	archiveClassifier.set("javadoc")
	from("$buildDir/dokka/javadoc")
}

// Because of limitations in the android plugin
// the publishing definition should be inside the "afterEvaluate" block
afterEvaluate {
	publishing.publications.create<MavenPublication>("default") {
		from(components["release"])

		artifact(tasks["sourcesArtifact"])
		artifact(tasks["javadocJar"])

		defaultPom()
	}
}

dependencies {
	api(projects.jellyfinCore)

	implementation(libs.androidx.core)
	implementation(libs.androidx.annotation)

	testImplementation(libs.kotlin.test.junit)
}
