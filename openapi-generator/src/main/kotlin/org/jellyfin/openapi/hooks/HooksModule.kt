package org.jellyfin.openapi.hooks

import org.jellyfin.openapi.hooks.api.BinaryOperationUrlHook
import org.jellyfin.openapi.hooks.api.ClientLogOperationUrlHook
import org.jellyfin.openapi.hooks.api.LargeOperationRequestModelHook
import org.jellyfin.openapi.hooks.api.PlayStateServiceNameHook
import org.jellyfin.openapi.hooks.model.DotNetDescriptionHook
import org.jellyfin.openapi.hooks.model.ImageMapsHook
import org.jellyfin.openapi.hooks.model.SwashbuckleDescriptionHook
import org.koin.dsl.bind
import org.koin.dsl.module

val hooksModule = module {
	single { ImageMapsHook() } bind TypeBuilderHook::class

	single { BinaryOperationUrlHook() } bind OperationUrlHook::class
	single { ClientLogOperationUrlHook() } bind OperationUrlHook::class

	single { LargeOperationRequestModelHook() } bind OperationRequestModelHook::class

	single { PlayStateServiceNameHook() } bind ServiceNameHook::class

	single { DotNetDescriptionHook() } bind DescriptionHook::class
	single { SwashbuckleDescriptionHook() } bind DescriptionHook::class
}
