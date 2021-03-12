import org.gradle.api.publish.maven.MavenPublication

fun MavenPublication.defaultPom() = pom {
	name.set("jellyfin-apiclient-java")
	description.set("Kotlin API Client for Jellyfin")
	url.set("https://github.com/jellyfin/jellyfin-apiclient-java")

	scm {
		url.set("https://github.com/jellyfin/jellyfin-apiclient-java")
		connection.set("scm:git:git@github.com:jellyfin/jellyfin-apiclient-java.git")
		developerConnection.set("scm:git:git@github.com/jellyfin/jellyfin-apiclient-java.git")
	}

	licenses {
		license {
			name.set("GNU Lesser General Public License v3.0")
			url.set("https://www.gnu.org/licenses/lgpl-3.0.txt")
		}
	}

	developers {
		developer {
			id.set("nielsvanvelzen")
			name.set("Niels van Velzen")
			url.set("https://github.com/nielsvanvelzen")
		}
	}
}
