package org.jellyfin.sdk.api.client.compatibility

import org.jellyfin.sdk.api.client.Response
import kotlin.coroutines.Continuation
import kotlin.coroutines.CoroutineContext
import kotlin.coroutines.EmptyCoroutineContext

public abstract class JavaCallback<T> : Continuation<Response<T>> {
	override val context: CoroutineContext = EmptyCoroutineContext
	override fun resumeWith(result: Result<Response<T>>): Unit = onResponse(result.getOrNull())

	public abstract fun onResponse(response: Response<T>?)
}
