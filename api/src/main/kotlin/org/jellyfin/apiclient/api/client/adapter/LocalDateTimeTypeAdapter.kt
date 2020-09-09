package org.jellyfin.apiclient.api.client.adapter

import com.google.gson.TypeAdapter
import com.google.gson.stream.JsonReader
import com.google.gson.stream.JsonWriter
import java.time.LocalDateTime
import java.time.ZoneId
import java.time.ZonedDateTime

/**
 * Adapter to read zoned date times as local date time and writing it back
 */
class LocalDateTimeTypeAdapter : TypeAdapter<LocalDateTime>() {
	override fun write(out: JsonWriter, value: LocalDateTime?) {
		out.value(value?.atZone(ZoneId.systemDefault()).toString())
	}

	override fun read(`in`: JsonReader): LocalDateTime {
		return ZonedDateTime.parse(`in`.nextString()).toLocalDateTime()
	}
}
