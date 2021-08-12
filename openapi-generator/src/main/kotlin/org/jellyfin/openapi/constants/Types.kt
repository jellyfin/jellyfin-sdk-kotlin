package org.jellyfin.openapi.constants

import com.squareup.kotlinpoet.asTypeName
import io.ktor.utils.io.*
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlinx.serialization.UseSerializers
import kotlinx.serialization.json.JsonElement
import java.time.LocalDateTime
import java.util.*

object Types {
	// Custom
	val BINARY = ByteReadChannel::class.asTypeName()
	val UUID = UUID::class.asTypeName()
	val DATETIME = LocalDateTime::class.asTypeName()
	val JSON_ELEMENT = JsonElement::class.asTypeName()

	// Special
	val ANY = Any::class.asTypeName()
	val NONE = Unit::class.asTypeName()

	// Collections
	val MAP = Map::class.asTypeName()
	val COLLECTION = Collection::class.asTypeName()
	val LIST = List::class.asTypeName()

	// Primitives
	val STRING = String::class.asTypeName()
	val INT = Int::class.asTypeName()
	val LONG = Long::class.asTypeName()
	val DOUBLE = Double::class.asTypeName()
	val FLOAT = Float::class.asTypeName()
	val BOOLEAN = Boolean::class.asTypeName()

	// Annotations
	val DEPRECATED = Deprecated::class.asTypeName()
	val SERIALIZABLE = Serializable::class.asTypeName()
	val SERIAL_NAME = SerialName::class.asTypeName()
	val USE_SERIALIZERs = UseSerializers::class.asTypeName()
}
