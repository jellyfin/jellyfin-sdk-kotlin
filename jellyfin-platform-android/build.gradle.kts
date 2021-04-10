plugins {
	id("com.android.library")
	id("kotlin-android")
}

android {
	compileSdkVersion(30)

	defaultConfig {
		minSdkVersion(19)
		targetSdkVersion(30)
		multiDexEnabled = true

		consumerProguardFiles("proguard-rules.pro")
	}

	compileOptions {
		isCoreLibraryDesugaringEnabled = true

		sourceCompatibility = JavaVersion.VERSION_1_8
		targetCompatibility = JavaVersion.VERSION_1_8
	}

	kotlinOptions {
		jvmTarget = JavaVersion.VERSION_1_8.toString()
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

	lintOptions {
		isAbortOnError = false
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

	implementation(Dependencies.Kotlin.stdlib)

	implementation(Dependencies.AndroidX.core)
	implementation(Dependencies.AndroidX.annotation)

	testImplementation(Dependencies.Kotlin.Test.junit)

	coreLibraryDesugaring(Dependencies.Android.desugarJdkLibs)
}
