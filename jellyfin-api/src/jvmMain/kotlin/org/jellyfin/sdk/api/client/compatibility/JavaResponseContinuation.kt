package org.jellyfin.sdk.api.client.compatibility

import org.jellyfin.sdk.api.client.Response
import kotlin.coroutines.Continuation
import kotlin.coroutines.CoroutineContext
import kotlin.coroutines.EmptyCoroutineContext

public abstract class JavaResponseContinuation<T> : Continuation<Response<T>> {
	override val context: CoroutineContext = EmptyCoroutineContext
	override fun resumeWith(result: Result<Response<T>>): Unit = result.fold(
		onSuccess = { onResponse(it.content) },
		onFailure = ::onError,
	)

	public abstract fun onResponse(response: T)
	public open fun onError(error: Throwable): Unit = throw error
}
