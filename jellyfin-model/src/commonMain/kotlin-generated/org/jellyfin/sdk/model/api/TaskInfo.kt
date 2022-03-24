// !!        WARNING
// !! DO NOT EDIT THIS FILE
//
// This file is generated by the openapi-generator module and is not meant for manual changes.
// Please read the README.md file in the openapi-generator module for additional information.
package org.jellyfin.sdk.model.api

import kotlin.Boolean
import kotlin.Double
import kotlin.String
import kotlin.collections.List
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * Class TaskInfo.
 */
@Serializable
public data class TaskInfo(
	/**
	 * Gets or sets the name.
	 */
	@SerialName("Name")
	public val name: String? = null,
	/**
	 * Gets or sets the state of the task.
	 */
	@SerialName("State")
	public val state: TaskState,
	/**
	 * Gets or sets the progress.
	 */
	@SerialName("CurrentProgressPercentage")
	public val currentProgressPercentage: Double? = null,
	/**
	 * Gets or sets the id.
	 */
	@SerialName("Id")
	public val id: String? = null,
	/**
	 * Gets or sets the last execution result.
	 */
	@SerialName("LastExecutionResult")
	public val lastExecutionResult: TaskResult? = null,
	/**
	 * Gets or sets the triggers.
	 */
	@SerialName("Triggers")
	public val triggers: List<TaskTriggerInfo>? = null,
	/**
	 * Gets or sets the description.
	 */
	@SerialName("Description")
	public val description: String? = null,
	/**
	 * Gets or sets the category.
	 */
	@SerialName("Category")
	public val category: String? = null,
	/**
	 * Gets or sets a value indicating whether this instance is hidden.
	 */
	@SerialName("IsHidden")
	public val isHidden: Boolean,
	/**
	 * Gets or sets the key.
	 */
	@SerialName("Key")
	public val key: String? = null,
)
