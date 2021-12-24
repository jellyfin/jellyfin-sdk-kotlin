package org.jellyfin.sdk.compatibility

import kotlin.coroutines.Continuation
import kotlin.coroutines.CoroutineContext
import kotlin.coroutines.EmptyCoroutineContext

public abstract class JavaContinuation<T> : Continuation<T> {
	override val context: CoroutineContext = EmptyCoroutineContext
	override fun resumeWith(result: Result<T>): Unit = result.fold(
		onSuccess = ::onSuccess,
		onFailure = ::onError,
	)

	public abstract fun onSuccess(result: T)
	public open fun onError(error: Throwable): Unit = throw error
}
