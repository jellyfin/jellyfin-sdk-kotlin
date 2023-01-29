# Authentication

There are multiple ways to authenticate with a Jellyfin server. The most common ways are password authentication and
Quick Connect.

## The access token

When an authentication attempt is successful the server grants a token. This token can be used in future API requests to
authorize the user. In the SDK this token can be set in two ways.

### When creating an API client instance with `jellyfin.createApi()`

Use the `accessToken` parameter in the `createApi` function like so:

```kotlin
val api = jellyfin.createApi(
	baseUrl = "https://demo.jellyfin.org/stable/",
	accessToken = "02a7174a4d1843448b6d177d8288efd0",
)
```

### By changing the token in an existing API client

Change the token in an existing API client instance like so:

```kotlin
api.accessToken = "02a7174a4d1843448b6d177d8288efd0"
```

## Password login

The password login is the easiest method to implement. Use the `authenticateUserByName` method in the userApi with a
username and password combination to get an access token. The server responds with an HTTP 401 status code when the
credentials are invalid, otherwise it contains the authentication result with user information.

```kotlin
val api = jellyfin.createApi(/* .. */)

try {
	val authenticationResult by api.userApi.authenticateUserByName(
		username = "demo",
		password = "",
	)

	// Use access token in api instance
	api.accessToken = authenticationResult.accessToken

	// Print session information
	println(authenticationResult.sessionInfo)
} catch (err: InvalidStatusException) {
	if (err.status == 401) {
		// Username or password is incorrect
		println("Invalid user")
	}
}
```

### Passwordless users

A user might not have a password. You still need an access token for those users. Use the same `authenticateUserByName`
function but leave the password as an empty string.

## Quick Connect

Using passwords is not the best choice on all platforms. Using the remote of a television or entering a complex password
on mobile is not the best experience. For those cases Quick Connect helps out. With Quick Connect the app requests a
code from the server and displays it to the user. This code can then be used in an already signed in app to authorize
the new one.

The Quick Connect functionality may be disabled by a server. In those cases the server responds with an HTTP 401
response. You can check if QuickConnect is enabled first or deal with it when trying to use it.

To start a Quick Connect session you need to request a Quick Connect code with the initiate function. This returns a
state object which can be updated by calling the connect method.

```kotlin
val api = jellyfin.createApi(/* .. */)

// Check if Quick Connect is enabled (this is optional)
val enabled by api.quickConnectApi.getEnabled()
if (!enabled) println("QuickConnect is disabled in the server!")

// Create a Quick Connect session and store the state
try {
	var quickConnectState by api.quickConnectApi.initiate()
} catch (err: InvalidStatusException) {
	if (err.status == 401) {
		// Quick Connect is disabled
		println("QuickConnect is disabled in the server!")
	}
}
```

The Quick Connect state contains a few values that are of interest:

- `authenticated` is a boolean that indicates if the user authorized the app. When this is `true` you can retrieve an
  access token.
- `secret` is a string with a unique code for this session. It is used to update the session state.
- The `code` is for the user to input in a different app

The next step is to show the code to the user. Depending on your app you might want to either show a "next" button to
press
when the user authorized the app or automatically update the Quick Connect state. In the latter example we recommend
updating every 5 seconds.

```kotlin
// Update the Quick Connect session state
quickConnectState = api.quickConnectApi.connect(
	secret = quickConnectState.secret,
)
```

Eventually the `authenticated` value will change to `true`. This means the user authorized your app. You can now use the
secret to get an access token in the user api.

```kotlin
val authenticationResult by api.userApi.authenticateWithQuickConnect(
	secret = quickConnectState.secret,
)

// Use access token in api instance
api.accessToken = authenticationResult.accessToken

// Print session information
println(authenticationResult.sessionInfo)
```

So in summary:

1. Create a Quick Connect session
2. Show the `code` to the user
3. Update the state every 5 seconds or manually
4. Request an access token when `authenticated` is true
