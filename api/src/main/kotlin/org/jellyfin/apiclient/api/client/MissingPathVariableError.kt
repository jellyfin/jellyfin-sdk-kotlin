package org.jellyfin.apiclient.api.client

class MissingPathVariableError(
	name: String,
	path: String
) : Error("Missing path variable $name from path $path")
