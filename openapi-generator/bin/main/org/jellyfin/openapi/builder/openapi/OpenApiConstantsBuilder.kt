package org.jellyfin.openapi.builder.openapi

import com.squareup.kotlinpoet.KModifier
import com.squareup.kotlinpoet.PropertySpec
import com.squareup.kotlinpoet.TypeSpec
import io.swagger.v3.oas.models.info.Info
import org.jellyfin.openapi.builder.ContextBuilder
import org.jellyfin.openapi.builder.extra.FileSpecBuilder
import org.jellyfin.openapi.constants.Classes
import org.jellyfin.openapi.constants.Packages
import org.jellyfin.openapi.constants.Types
import org.jellyfin.openapi.model.GeneratorContext
import org.jellyfin.openapi.model.JellyFile

class OpenApiConstantsBuilder(
	private val fileSpecBuilder: FileSpecBuilder,
) : ContextBuilder<Info, Unit> {
	private fun TypeSpec.Builder.addConstant(name: String, value: String): TypeSpec.Builder = addProperty(
		PropertySpec.builder(name, Types.STRING)
			.initializer("%S", value)
			.addModifiers(KModifier.CONST).build()
	)

	override fun build(context: GeneratorContext, data: Info) {
		val typeSpec = TypeSpec.Companion.objectBuilder(Classes.CONSTANTS_OBJECT)
			.addConstant("apiVersion", data.version)
			.build()

		context += fileSpecBuilder.build(JellyFile(Packages.API_CONSTANTS, emptySet(), typeSpec))
	}
}
