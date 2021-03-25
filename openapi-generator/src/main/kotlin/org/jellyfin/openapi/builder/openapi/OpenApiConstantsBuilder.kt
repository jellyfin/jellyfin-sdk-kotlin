package org.jellyfin.openapi.builder.openapi

import com.squareup.kotlinpoet.PropertySpec
import com.squareup.kotlinpoet.TypeSpec
import io.swagger.v3.oas.models.info.Info
import org.jellyfin.openapi.builder.Builder
import org.jellyfin.openapi.constants.Classes
import org.jellyfin.openapi.constants.Packages
import org.jellyfin.openapi.model.JellyFile

class OpenApiConstantsBuilder : Builder<Info, JellyFile> {
	private fun TypeSpec.Builder.addConstant(name: String, value: String): TypeSpec.Builder =
		addProperty(PropertySpec.builder(name, String::class).initializer("%S", value).build())

	override fun build(data: Info): JellyFile {
		val typeSpec = TypeSpec.Companion.objectBuilder(Classes.CONSTANTS_OBJECT)
			.addConstant("apiVersion", data.version)
			.build()

		return JellyFile(Packages.API_CONSTANTS, emptySet(), typeSpec)
	}
}
