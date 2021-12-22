package org.jellyfin.sdk.compatibility

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlin.coroutines.Continuation
import kotlin.coroutines.CoroutineContext

/**
 * Compatibility class to use Kotlin suspend functions in Java files.
 */
public object JavaContinuation {
	public fun interface ReturnCallback<R> {
		public fun onReturn(error: Throwable?, result: R?)
	}
	/**
	 * .
	 * @param resultCallback Called when the function ends with error or data.
	 */
	@JvmOverloads
	@JvmStatic
	public fun <R> get(returnCallback: ReturnCallback<R>, dispatcher: CoroutineDispatcher = Dispatchers.Default): Continuation<R> {
		return object : Continuation<R> {
			override val context: CoroutineContext
				get() = dispatcher
			override fun resumeWith(result: Result<R>) {
				returnCallback.onReturn(result.exceptionOrNull(), result.getOrNull())
			}
		}
	}
}
