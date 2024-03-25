package org.jellyfin.sdk.model.extensions

import org.jellyfin.sdk.model.api.GeneralCommandMessage
import kotlin.reflect.KProperty

// Single get
public operator fun GeneralCommandMessage.get(name: String): String? =
	data?.arguments?.entries?.firstOrNull { (key) ->
		key.contentEquals(name, ignoreCase = true)
	}?.value

// Multi get
public operator fun GeneralCommandMessage.get(vararg names: String): List<String?> =
	names.map(::get)

// Property delegation
public operator fun GeneralCommandMessage.getValue(thisRef: Any?, property: KProperty<*>): String? =
	get(property.name)

// Value checking
public operator fun GeneralCommandMessage.contains(name: String): Boolean =
	data?.arguments?.any { (key) ->
		key.contentEquals(name, ignoreCase = true)
	} ?: false
