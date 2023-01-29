-keep class org.jellyfin.sdk.model.**.* { *; }
-keep class org.jellyfin.sdk.api.client.exception.**.* { *; }

# Fix for https://youtrack.jetbrains.com/issue/KTOR-2708
-keep public class io.ktor.client.** {
    public <methods>;
    private <methods>;
}
