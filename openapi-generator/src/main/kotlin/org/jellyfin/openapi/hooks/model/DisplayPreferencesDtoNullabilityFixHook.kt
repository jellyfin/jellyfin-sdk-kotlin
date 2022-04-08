package org.jellyfin.openapi.hooks.model

import com.squareup.kotlinpoet.typeNameOf
import io.swagger.v3.oas.models.media.Schema
import org.jellyfin.openapi.builder.openapi.OpenApiTypeBuilder
import org.jellyfin.openapi.hooks.ModelTypePath
import org.jellyfin.openapi.hooks.TypeBuilderHook
import org.jellyfin.openapi.hooks.TypePath

/**
 * A hook that modifies the type of the "customPrefs" property in "DisplayPreferencesDto" to fix a nullability issue.
 * In the 10.7 API specification the value of the map is incorrectly labelled as not-null.
 * This hook changes the type to the exact type emitted from the 10.8 alpha API specification.
 */
class DisplayPreferencesDtoNullabilityFixHook : TypeBuilderHook {
	override fun onBuildType(path: TypePath, schema: Schema<*>, typeBuilder: OpenApiTypeBuilder) = when (path) {
		ModelTypePath("DisplayPreferencesDto", "customPrefs") -> typeNameOf<Map<String, String?>>()
		else -> null
	}
}
