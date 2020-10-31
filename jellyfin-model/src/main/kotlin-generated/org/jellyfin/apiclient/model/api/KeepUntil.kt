// !!        WARNING
// !! DO NOT EDIT THIS FILE
//
// This file is generated by the openapi-generator module and is not meant for manual changes.
// Please read the README.md file in the openapi-generator module for additional information.
package org.jellyfin.apiclient.model.api

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
public enum class KeepUntil {
	@SerialName("UntilDeleted")
	UNTIL_DELETED,
	@SerialName("UntilSpaceNeeded")
	UNTIL_SPACE_NEEDED,
	@SerialName("UntilWatched")
	UNTIL_WATCHED,
	@SerialName("UntilDate")
	UNTIL_DATE,
}
