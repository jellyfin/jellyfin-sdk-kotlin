buildscript {
	repositories {
		google()
		jcenter()
	}

	dependencies {
		classpath("com.android.tools.build:gradle:3.6.3")
		classpath(kotlin("gradle-plugin", "1.3.72"))
		classpath("com.github.dcendents:android-maven-gradle-plugin:2.1")
	}
}

allprojects {
	repositories {
		google()
		jcenter()
	}
}
