package org.jellyfin.openapi.model

import com.squareup.kotlinpoet.FileSpec

data class GeneratorResult(
	val models: List<FileSpec>,
	val apis: List<FileSpec>,
	val constants: FileSpec,
)
