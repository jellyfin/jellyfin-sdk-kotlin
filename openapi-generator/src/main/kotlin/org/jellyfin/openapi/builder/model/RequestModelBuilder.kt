package org.jellyfin.openapi.builder.model

import net.pearx.kasechange.toPascalCase
import org.jellyfin.openapi.builder.Builder
import org.jellyfin.openapi.constants.Packages
import org.jellyfin.openapi.constants.Strings
import org.jellyfin.openapi.model.ApiServiceOperation
import org.jellyfin.openapi.model.DefaultValue
import org.jellyfin.openapi.model.JellyFile
import org.jellyfin.openapi.model.ObjectApiModel
import org.jellyfin.openapi.model.ObjectApiModelProperty

class RequestModelBuilder(
	private val objectModelBuilder: ObjectModelBuilder,
) : Builder<ApiServiceOperation, JellyFile> {
	override fun build(data: ApiServiceOperation): JellyFile = objectModelBuilder.build(
		ObjectApiModel(
			data.name.toPascalCase() + Strings.MODEL_REQUEST_SUFFIX,
			data.description,
			false,
			(data.pathParameters + data.queryParameters).map { parameter ->
				ObjectApiModelProperty(
					name = parameter.name,
					originalName = parameter.originalName,
					type = parameter.type,
					defaultValue = when (parameter.defaultValue) {
						is DefaultValue.Conditional -> parameter.defaultValue.modelValue
						else -> parameter.defaultValue
					},
					description = parameter.description,
					deprecated = parameter.deprecated,
				)
			}.toSet()
		)
	).copy(
		namespace = Packages.MODEL_REQUEST,
	)
}
