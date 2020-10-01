package org.jellyfin.openapi.hooks

import org.jellyfin.openapi.hooks.api.BinaryOperationUrlHook
import org.jellyfin.openapi.hooks.model.ImageMapsHook
import org.jellyfin.openapi.hooks.model.NullableReferencesHook
import org.jellyfin.openapi.hooks.model.SyncPlayGroupUpdateHook
import org.koin.dsl.bind
import org.koin.dsl.module

val hooksModule = module {
	single { ImageMapsHook() } bind TypeBuilderHook::class
	single { NullableReferencesHook() } bind TypeBuilderHook::class
	single { SyncPlayGroupUpdateHook() } bind TypeBuilderHook::class

	single { BinaryOperationUrlHook() } bind OperationUrlHook::class
}
