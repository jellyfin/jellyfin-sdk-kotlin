package org.jellyfin.apiclient.api.client.compatibility

import org.jellyfin.apiclient.api.client.Response
import kotlin.coroutines.Continuation
import kotlin.coroutines.CoroutineContext
import kotlin.coroutines.EmptyCoroutineContext

abstract class JavaDataCallback<T> : Continuation<Response<T>> {
	override val context: CoroutineContext = EmptyCoroutineContext
	override fun resumeWith(result: Result<Response<T>>) = onData(result.getOrNull()?.getData())

	abstract fun onData(data: T?)
}
