package org.jellyfin.openapi.hooks

import org.jellyfin.openapi.hooks.api.BinaryOperationUrlHook
import org.jellyfin.openapi.hooks.api.ClientLogOperationUrlHook
import org.jellyfin.openapi.hooks.api.PlayStateServiceNameHook
import org.jellyfin.openapi.hooks.model.DefaultUserIdHook
import org.jellyfin.openapi.hooks.model.ImageMapsHook
import org.jellyfin.openapi.hooks.model.SyncPlayGroupUpdateHook
import org.jellyfin.openapi.hooks.model.TranscodingInfoTranscodeReasonsHook
import org.koin.dsl.bind
import org.koin.dsl.module

val hooksModule = module {
	single { ImageMapsHook() } bind TypeBuilderHook::class
	single { SyncPlayGroupUpdateHook() } bind TypeBuilderHook::class
	single { TranscodingInfoTranscodeReasonsHook() } bind TypeBuilderHook::class

	single { BinaryOperationUrlHook() } bind OperationUrlHook::class
	single { ClientLogOperationUrlHook() } bind OperationUrlHook::class

	single { PlayStateServiceNameHook() } bind ServiceNameHook::class

	single { DefaultUserIdHook() } bind DefaultValueHook::class
}
