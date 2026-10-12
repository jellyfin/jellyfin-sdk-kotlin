# iOS

The SDK supports iOS (`iosArm64`, `iosSimulatorArm64` and `iosX64`) from Kotlin Multiplatform modules.

## Setup

```kotlin
kotlin {
    sourceSets {
        commonMain.dependencies {
            implementation("org.jellyfin.sdk:jellyfin-core:$sdkVersion")
        }
    }
}
```

## Usage

The API is the same as on other platforms. On iOS the SDK uses Ktor with the Darwin engine by default instead of OkHttp.
Use `iosDevice()` to create the device information:

```kotlin
val jellyfin = createJellyfin {
    clientInfo = ClientInfo(name = "My iOS app", version = "1.0.0")
    deviceInfo = iosDevice()
}
```

To use a custom Ktor client, create a `KtorFactory` from `jellyfin-api-ktor` and pass it as `apiClientFactory` and
`socketConnectionFactory`.

## Date and time and UUID

On iOS `DateTime` and `UUID` are `kotlinx.datetime.LocalDateTime` and `kotlin.uuid.Uuid`. On the JVM and Android they
are still `java.time.LocalDateTime` and `java.util.UUID`. Shared code can convert them using the functions in
`org.jellyfin.sdk.model.extensions`: `toKotlinxLocalDateTime()`, `toJellyfinDateTime()`, `toStdlibUuid()` and
`toJellyfinUuid()`.

## Local network discovery

Local server discovery uses a UDP broadcast. On iOS 14 and later add `NSLocalNetworkUsageDescription` to your
`Info.plist`.
