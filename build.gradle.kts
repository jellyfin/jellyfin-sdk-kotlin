buildscript {
	repositories {
		google()
		jcenter()
	}

	dependencies {
		classpath("com.android.tools.build:gradle:3.6.3")
		classpath(kotlin("gradle-plugin", "1.3.72"))
	}
}

allprojects {
	repositories {
		google()
		jcenter()
	}
}
