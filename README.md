<!-- markdownlint-disable MD033 no-inline-html -->
<h1 align="center">Jellyfin Kotlin SDK</h1>
<h3 align="center">Part of the <a href="https://jellyfin.org/">Jellyfin Project</a></h3>

---

<p align="center">
<img alt="Logo Banner" src="https://raw.githubusercontent.com/jellyfin/jellyfin-ux/master/branding/SVG/banner-logo-solid.svg?sanitize=true"/>
<br/>
<br/>
<a href="https://github.com/jellyfin/jellyfin-sdk-kotlin">
<img alt="LGPL 3.0 license" src="https://img.shields.io/github/license/jellyfin/jellyfin-sdk-kotlin.svg"/>
</a>
<a href="https://github.com/jellyfin/jellyfin-sdk-kotlin/releases">
<img alt="Current Release" src="https://img.shields.io/github/release/jellyfin/jellyfin-sdk-kotlin.svg"/>
</a>
<a href="https://search.maven.org/search?q=org.jellyfin.sdk">
<img alt="Maven Central Release" src="https://img.shields.io/maven-central/v/org.jellyfin.sdk/jellyfin-core.svg"/>
</a>
<br/>
<a href="https://opencollective.com/jellyfin">
<img alt="Donate" src="https://img.shields.io/opencollective/all/jellyfin.svg?label=backers"/>
</a>
<a href="https://matrix.to/#/+jellyfin-android-dev:matrix.org">
<img alt="Chat on Matrix" src="https://img.shields.io/matrix/jellyfin-android-dev:matrix.org.svg?logo=matrix"/>
</a>
<a href="https://www.reddit.com/r/jellyfin">
<img alt="Join our Subreddit" src="https://img.shields.io/badge/reddit-r%2Fjellyfin-%23FF5700.svg"/>
</a>
<a href="https://github.com/jellyfin/jellyfin-sdk-kotlin/releases.atom">
<img alt="Release RSS Feed" src="https://img.shields.io/badge/rss-releases-ffa500?logo=rss" />
</a>
<a href="https://github.com/jellyfin/jellyfin-sdk-kotlin/commits/master.atom">
<img alt="Master Commits RSS Feed" src="https://img.shields.io/badge/rss-commits-ffa500?logo=rss" />
</a>
</p>

---

The Kotlin SDK for Jellyfin implements the Jellyfin API to easily access servers. It is currently available
for the JVM and Android. Developer documentation is available at [kotlin-sdk.jellyfin.org].

[kotlin-sdk.jellyfin.org]: https://kotlin-sdk.jellyfin.org/guide/getting-started.html

## Contributing

We welcome contributions to the SDK. Open an issue or ask in our official chats if you plan to make bigger changes.

To validate binary compatibility we use the [Binary compatibility validator] tool from the Kotlin team. When creating
pull requests the api files need to be updated. Use the `apiDump` Gradle task to generate the api files. Add the changes
from this command to a separate commit to make the review process easier.

[Binary compatibility validator]: https://github.com/Kotlin/binary-compatibility-validator

## Testing

The SDK includes two example projects, the kotlin-cli and java-cli, to test various larger functions like server
discovery. Besides that we use unit tests to test smaller components, these can be executed with the `allTests` Gradle
task. We recommend adding new tests for changes to the
code.

### Testing in app

It is also possible to test a new version of the SDK in your own app. Use the `publishToMavenLocal` Gradle task to
publish the SDK to your local system, afterwards you can add `mavenLocal()` as repository and use the `latest-SNAPSHOT`
version for the SDK. This process is simplified in our official apps by adding an option to the `gradle.properties`
file.
