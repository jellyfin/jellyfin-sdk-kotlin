package org.jellyfin.apiclient.api.client.adapter

import com.google.gson.TypeAdapter
import com.google.gson.stream.JsonReader
import com.google.gson.stream.JsonWriter
import java.util.*

/**
 * A custom UUID type adapter that supports the badly formatted UUIDs from the Jellyfin API
 */
class UUIDTypeAdapter : TypeAdapter<UUID>() {
	override fun write(out: JsonWriter, value: UUID?) {
		out.value(value?.toString())
	}

	override fun read(`in`: JsonReader): UUID {
		val uuid = `in`.nextString()

		return if (uuid.length == 32) UUID.fromString(uuid.replace(UUID_REGEX, "$1-$2-$3-$4-$5"))
		else UUID.fromString(uuid)
	}

	companion object {
		val UUID_REGEX = "^([a-z\\d]{8})([a-z\\d]{4})(4[a-z\\d]{3})([a-z\\d]{4})([a-z\\d]{12})\$".toRegex()
	}
}
