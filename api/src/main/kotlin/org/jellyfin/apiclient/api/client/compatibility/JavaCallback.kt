package org.jellyfin.apiclient.api.client.compatibility

import org.jellyfin.apiclient.api.client.Response
import kotlin.coroutines.Continuation
import kotlin.coroutines.CoroutineContext
import kotlin.coroutines.EmptyCoroutineContext

abstract class JavaCallback<T> : Continuation<Response<T>> {
	override val context: CoroutineContext = EmptyCoroutineContext
	override fun resumeWith(result: Result<Response<T>>) = onResponse(result.getOrNull())

	abstract fun onResponse(response: Response<T>?)
}
