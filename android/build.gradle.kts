plugins {
	id("com.android.library")

	id("kotlin-android")
	id("kotlin-android-extensions")
}

/**
 * Get the versioncode for a given semver. Returns null if value is invalid.
 *
 * Sample output:
 * 0.0.0 -> 0
 * 1.1.1 -> 10101
 * 0.7.0 -> 700
 * 99.99.99 -> 999999
 */
fun getVersionCode(semver: String): Int? {
	val parts = semver.splitToSequence('.')
		.take(3)
		.map { it.toIntOrNull() }
		.filterNotNull()
		.toList()

	// Not a valid semver
	if (parts.size != 3) return null

	var code = 0
	code += parts[0] * 10000 // Major (0-99)
	code += parts[1] * 100 // Minor (0-99)
	code += parts[2] // Patch (0-99)

	return code
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
	api(project(":library"))

	implementation(kotlin("stdlib-jdk8"))

	implementation("androidx.core:core-ktx:1.2.0")
	implementation("androidx.annotation:annotation:1.1.0")

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
