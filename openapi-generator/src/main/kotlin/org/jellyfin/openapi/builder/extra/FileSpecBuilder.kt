package org.jellyfin.openapi.builder.extra

import com.squareup.kotlinpoet.FileSpec
import org.jellyfin.openapi.builder.Builder
import org.jellyfin.openapi.constants.Strings
import org.jellyfin.openapi.model.JellyFile

class FileSpecBuilder : Builder<JellyFile, FileSpec> {
	override fun build(data: JellyFile): FileSpec {
		return FileSpec.builder(data.namespace, data.typeSpec.name!!).apply {
			indent("\t")
			addComment(Strings.FILE_TOP_WARNING)
			data.annotations.forEach { addAnnotation(it) }
			addType(data.typeSpec)
		}.build()
	}
}
