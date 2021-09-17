package org.jellyfin.openapi.builder.api

import com.squareup.kotlinpoet.ClassName
import com.squareup.kotlinpoet.FileSpec
import com.squareup.kotlinpoet.FunSpec
import com.squareup.kotlinpoet.PropertySpec
import org.jellyfin.openapi.builder.Builder
import org.jellyfin.openapi.builder.extra.FileSpecBuilder
import org.jellyfin.openapi.constants.Classes
import org.jellyfin.openapi.constants.Packages
import org.jellyfin.openapi.model.ApiService

class ApiClientExtensionsBuilder(
	private val fileSpecBuilder: FileSpecBuilder,
) : Builder<Collection<ApiService>, FileSpec> {
	override fun build(data: Collection<ApiService>): FileSpec {
		return FileSpec.builder(Packages.API_CLIENT_EXTENSIONS, Classes.API_CLIENT_EXTENSIONS).apply {
			fileSpecBuilder.buildHeader(this)
			data.forEach { api -> addProperty(buildExtensionProperty(api)) }
		}.build()
	}

	fun buildExtensionProperty(api: ApiService): PropertySpec {
		val apiType = ClassName(Packages.API, api.name)
		val propertyName = api.name.replaceFirstChar(Char::lowercase)

		return PropertySpec.builder(propertyName, apiType).apply {
			receiver(ClassName(Packages.API_CLIENT, Classes.API_CLIENT))
			getter(FunSpec.getterBuilder().apply {
				addStatement("return getOrCreateApi { %T(it) }", apiType)
			}.build())
		}.build()
	}
}
