// !!        WARNING
// !! DO NOT EDIT THIS FILE
//
// This file is generated by the openapi-generator module and is not meant for manual changes.
// Please read the README.md file in the openapi-generator module for additional information.
package org.jellyfin.sdk.model.api

import kotlin.String
import kotlin.requireNotNull
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * Enum TaskTriggerInfoType.
 */
@Serializable
public enum class TaskTriggerInfoType(
	public val serialName: String,
) {
	@SerialName("DailyTrigger")
	DAILY_TRIGGER("DailyTrigger"),
	@SerialName("WeeklyTrigger")
	WEEKLY_TRIGGER("WeeklyTrigger"),
	@SerialName("IntervalTrigger")
	INTERVAL_TRIGGER("IntervalTrigger"),
	@SerialName("StartupTrigger")
	STARTUP_TRIGGER("StartupTrigger"),
	;

	/**
	 * Get the serial name of the enum member.
	 */
	override fun toString(): String = serialName

	public companion object {
		/**
		 * Find the enum member by the serial name or return null.
		 */
		public fun fromNameOrNull(serialName: String): TaskTriggerInfoType? = when (serialName) {
			"DailyTrigger" -> DAILY_TRIGGER
			"WeeklyTrigger" -> WEEKLY_TRIGGER
			"IntervalTrigger" -> INTERVAL_TRIGGER
			"StartupTrigger" -> STARTUP_TRIGGER
			else -> null
		}

		/**
		 * Find the enum member by the serial name or throw.
		 */
		public fun fromName(serialName: String): TaskTriggerInfoType =
				requireNotNull(fromNameOrNull(serialName)) { """Unknown value $serialName""" }
	}
}