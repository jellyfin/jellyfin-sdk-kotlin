package org.jellyfin.openapi.builder.openapi

import com.squareup.kotlinpoet.TypeName
import io.swagger.v3.oas.models.responses.ApiResponse
import org.jellyfin.openapi.constants.MimeType
import org.jellyfin.openapi.constants.Types
import org.jellyfin.openapi.hooks.ApiTypePath

class OpenApiReturnTypeBuilder(
	private val openApiTypeBuilder: OpenApiTypeBuilder
) {
	@Suppress("ComplexMethod")
	fun build(path: ApiTypePath, response: ApiResponse?): TypeName {
		val supportedReturnMimeTypes = response?.content?.keys.orEmpty()

		return when {
			// String types
			MimeType.TEXT_ALL in supportedReturnMimeTypes ||
			MimeType.TEXT_PLAIN in supportedReturnMimeTypes ||
			MimeType.TEXT_CSS in supportedReturnMimeTypes ||
			MimeType.TEXT_XML in supportedReturnMimeTypes ||
			MimeType.TEXT_HTML in supportedReturnMimeTypes ||
			MimeType.APPLICATION_X_JAVASCRIPT in supportedReturnMimeTypes ->
				Types.STRING
			// Binary types
			MimeType.AUDIO_ALL in supportedReturnMimeTypes ||
			MimeType.VIDEO_ALL in supportedReturnMimeTypes ||
			MimeType.IMAGE_ALL in supportedReturnMimeTypes ||
			MimeType.FONT_ALL in supportedReturnMimeTypes ||
			MimeType.APPLICATION_X_MPEG_URL in supportedReturnMimeTypes ||
			MimeType.APPLICATION_OCTET_STREAM in supportedReturnMimeTypes ->
				Types.BYTE_ARRAY
			// JSON (restful) types
			MimeType.APPLICATION_JSON in supportedReturnMimeTypes -> {
				val schema = response!!.content[MimeType.APPLICATION_JSON]!!.schema
				openApiTypeBuilder.build(path, schema)
			}
			// Default to no response
			else -> Types.NONE
		}
	}
}
