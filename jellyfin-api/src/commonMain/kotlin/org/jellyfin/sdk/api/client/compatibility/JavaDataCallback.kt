package org.jellyfin.sdk.api.client.compatibility

import org.jellyfin.sdk.api.client.Response
import kotlin.coroutines.Continuation
import kotlin.coroutines.CoroutineContext
import kotlin.coroutines.EmptyCoroutineContext

public abstract class JavaDataCallback<T> : Continuation<Response<T>> {
	override val context: CoroutineContext = EmptyCoroutineContext
	override fun resumeWith(result: Result<Response<T>>): Unit = onData(result.getOrNull()?.content)

	public abstract fun onData(data: T?)
}
