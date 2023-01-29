import org.gradle.api.publish.maven.MavenPublication

fun MavenPublication.defaultPom() = pom {
	name.set("Jellyfin Kotlin SDK - $artifactId")
	description.set("Official Kotlin/Java SDK for Jellyfin. ${groupId}:${artifactId}")
	url.set("https://github.com/jellyfin/jellyfin-sdk-kotlin")

	scm {
		connection.set("scm:git:git://github.com/jellyfin/jellyfin-sdk-kotlin.git")
		developerConnection.set("scm:git:ssh://github.com:jellyfin/jellyfin-sdk-kotlin.git")
		url.set("https://github.com/jellyfin/jellyfin-sdk-kotlin/tree/master")
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
			organization.set("Jellyfin")
			organizationUrl.set("https://jellyfin.org")
		}
	}
}
