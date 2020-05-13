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

		versionCode = 27
		versionName = "0.6.0"
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
	api(project(":library"))

	implementation(kotlin("stdlib-jdk8"))

	implementation("androidx.core:core-ktx:1.2.0")
	implementation("androidx.annotation:annotation:1.1.0")

	implementation("com.google.code.gson:gson:2.8.6")
	implementation("com.android.volley:volley:1.1.1")
}

// Because of limitations in the android plugin
// the publishing definition should be inside the "afterEvaluate" block
afterEvaluate {
	publishing.publications.create<MavenPublication>("default") {
		// Should be the same as the build type
		from(components["release"])
	}
}
