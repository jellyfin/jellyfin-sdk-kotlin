package org.jellyfin.openapi.hooks

import org.jellyfin.openapi.hooks.model.ImageMapsHook
import org.koin.dsl.module

val hooksModule = module {
	single<TypeBuilderHook> { ImageMapsHook() }
}
