package org.jellyfin.openapi.model

import com.squareup.kotlinpoet.FileSpec

data class GeneratorResult(
	val files: List<FileSpec>,
)
