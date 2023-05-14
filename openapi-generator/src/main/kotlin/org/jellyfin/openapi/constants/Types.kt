package org.jellyfin.openapi.constants

import com.squareup.kotlinpoet.ClassName
import com.squareup.kotlinpoet.asTypeName

object Types {
	// Custom
	val BINARY = ClassName("io.ktor.utils.io", "ByteReadChannel")
	val BYTE_ARRAY = ByteArray::class.asTypeName()
	val UUID = ClassName(Packages.MODEL_TYPES, Classes.Types.UUID)
	val DATETIME = ClassName(Packages.MODEL_TYPES, Classes.Types.DATETIME)
	val JSON_ELEMENT = ClassName("kotlinx.serialization.json", "JsonElement")

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
	val SERIALIZABLE = ClassName("kotlinx.serialization", "Serializable")
	val SERIAL_NAME = ClassName("kotlinx.serialization", "SerialName")
	val USE_SERIALIZERs = ClassName("kotlinx.serialization", "UseSerializers")
	val JSON_DISCRIMINATOR = ClassName("kotlinx.serialization.json", "JsonClassDiscriminator")
}
