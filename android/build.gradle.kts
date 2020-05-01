plugins {
	id("com.android.library")
	id("com.github.dcendents.android-maven")
}

android {
	compileSdkVersion(29)

	defaultConfig {
		minSdkVersion(19)
		targetSdkVersion(29)

		versionCode = 27
		versionName = "0.6.0"
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

	implementation("com.android.volley:volley:1.1.1")
}
