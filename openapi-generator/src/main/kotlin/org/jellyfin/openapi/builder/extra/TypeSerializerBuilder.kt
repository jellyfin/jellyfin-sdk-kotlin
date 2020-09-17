package org.jellyfin.openapi.builder.extra

import com.squareup.kotlinpoet.ClassName
import com.squareup.kotlinpoet.ParameterizedTypeName
import com.squareup.kotlinpoet.TypeName
import com.squareup.kotlinpoet.asTypeName
import org.jellyfin.openapi.builder.Builder
import org.jellyfin.openapi.constants.Classes
import org.jellyfin.openapi.constants.Packages
import java.time.LocalDateTime
import java.util.*

/**
 * Returns the required serializer for a given type or null if no serializer defined
 */
class TypeSerializerBuilder : Builder<TypeName, TypeName?> {
	// Types should be non-nullable
	private val serializers = mapOf(
		UUID::class.asTypeName() to Classes.Serializers.UUID,
		LocalDateTime::class.asTypeName() to Classes.Serializers.LOCAL_DATE_TIME,
	)

	override fun build(data: TypeName): TypeName? {
		// Look at parameter types to support collections
		val knownTypes = mutableSetOf<TypeName>()

		if (data is ParameterizedTypeName) {
			knownTypes += data.rawType
			knownTypes += data.typeArguments
		} else {
			knownTypes += data
		}

		val knownSerializers = knownTypes.mapNotNull {
			// Set nullable to false for a not-null comparison
			serializers[it.copy(nullable = false)]
		}

		// None found
		if (knownSerializers.isEmpty()) return null

		// One or multiple found
		require(knownSerializers.size == 1) { "Can not use multiple serializers" }

		// Return type name for serializer
		return ClassName(Packages.MODEL_SERIALIZERS, knownSerializers.first())
	}
}
