plugins {
	id("com.android.library")

	id("kotlin-android")
	id("kotlin-android-extensions")
}

android {
	compileSdkVersion(29)

	defaultConfig {
		minSdkVersion(19)
		targetSdkVersion(29)

		versionCode = getVersionCode(project.version.toString()) ?: 0
		versionName = project.version.toString()
	}

	compileOptions {
		sourceCompatibility = JavaVersion.VERSION_1_8
		targetCompatibility = JavaVersion.VERSION_1_8
	}

	kotlinOptions {
		jvmTarget = JavaVersion.VERSION_1_8.toString()
	}

	sourceSets["main"].java.srcDirs("src/main/kotlin")

	buildTypes {
		getByName("release") {
			isMinifyEnabled = false
		}
	}

	lintOptions {
		isAbortOnError = false
	}
}

// Generate sources jar
tasks.create<Jar>("sourcesArtifact") {
	archiveClassifier.set("sources")

	from(android.sourceSets["main"].java.srcDirs)
}

// Because of limitations in the android plugin
// the publishing definition should be inside the "afterEvaluate" block
afterEvaluate {
	publishing.publications.create<MavenPublication>("default") {
		from(components["release"])

		artifact(tasks["sourcesArtifact"])
	}
}

dependencies {
	apiProject(":library")

	implementation(Dependencies.Kotlin.stdlib)

	implementation(Dependencies.AndroidX.core)
	implementation(Dependencies.AndroidX.annotation)

	implementation(Dependencies.volley)
}
