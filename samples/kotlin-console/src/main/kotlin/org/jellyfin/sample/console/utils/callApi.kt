package org.jellyfin.sample.console.utils

import org.jellyfin.apiclient.interaction.Response
import kotlin.coroutines.suspendCoroutine

suspend fun <T : Any> callApi(init: (callback: Response<T>) -> Unit): T = suspendCoroutine { continuation ->
	init(object : Response<T>() {
		override fun onResponse(response: T) = continuation.resumeWith(Result.success(response))
		override fun onError(exception: Exception) = continuation.resumeWith(Result.failure(exception))
	})
}
