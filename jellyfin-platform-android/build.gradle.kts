plugins {
	id("com.android.library")

	id("kotlin-android")
	id("kotlin-android-extensions")
}

android {
	compileSdkVersion(30)

	defaultConfig {
		minSdkVersion(19)
		targetSdkVersion(30)
		multiDexEnabled = true

		versionCode = getVersionCode(project.version.toString()) ?: 0
		versionName = project.version.toString()

		consumerProguardFiles("proguard-rules.pro")
	}

	compileOptions {
		coreLibraryDesugaringEnabled = true

		sourceCompatibility = JavaVersion.VERSION_1_8
		targetCompatibility = JavaVersion.VERSION_1_8
	}

	kotlinOptions {
		jvmTarget = JavaVersion.VERSION_1_8.toString()
	}

	sourceSets["main"].java.srcDirs("src/main/kotlin")
	sourceSets["test"].java.srcDirs("src/test/kotlin")

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
	apiProject(":jellyfin-core")

	implementation(Dependencies.Kotlin.stdlib)

	implementation(Dependencies.AndroidX.core)
	implementation(Dependencies.AndroidX.annotation)

	testImplementation(Dependencies.Kotlin.Test.junit)

	coreLibraryDesugaring(Dependencies.Android.desugarJdkLibs)
}
