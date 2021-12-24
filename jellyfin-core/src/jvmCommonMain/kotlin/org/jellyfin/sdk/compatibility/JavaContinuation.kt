package org.jellyfin.sdk.compatibility

import kotlin.coroutines.Continuation
import kotlin.coroutines.EmptyCoroutineContext

/**
 * Compatibility class to use Kotlin suspend functions in Java files.
 */
public object JavaContinuation {
	public fun interface ReturnCallback<R> {
		public fun onReturn(error: Throwable?, result: R?)
	}
	/**
	 * .
	 * @param returnCallback Called when the function ends with error or data.
	 */
	@JvmStatic
	public fun <R> get(returnCallback: ReturnCallback<R>): Continuation<R> {
		return object : Continuation<R> {
			override val context = EmptyCoroutineContext
			override fun resumeWith(result: Result<R>) = returnCallback.onReturn(result.exceptionOrNull(), result.getOrNull())
		}
	}
}
