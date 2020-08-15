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

	buildTypes {
		getByName("release") {
			isMinifyEnabled = false
		}
	}

	lintOptions {
		isAbortOnError = false
	}
}

dependencies {
	apiProject(":library")

	implementationKotlinStdlib()

	implementation(Dependencies.AndroidX.core)
	implementation(Dependencies.AndroidX.annotation)

	implementation(Dependencies.volley)
}

// Because of limitations in the android plugin
// the publishing definition should be inside the "afterEvaluate" block
afterEvaluate {
	publishing.publications.create<MavenPublication>("default") {
		// Should be the same as the build type
		from(components["release"])
	}
}
