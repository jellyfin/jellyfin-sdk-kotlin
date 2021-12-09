package org.jellyfin.sdk.discovery.compatibility

import kotlinx.coroutines.*
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.onCompletion
import kotlinx.coroutines.flow.onStart

@InternalCoroutinesApi
public class JavaFlow<T>(
		private val coroutineScope: CoroutineScope = CoroutineScope(Dispatchers.Default)
) {
	public interface ResultCallback <T> {
		public fun onResult(result: T)
	}
	public interface StartCallback {
		public fun onStart():Unit
	}
	public interface CompletionCallback {
		public fun onCompletion(thr: Throwable?):Unit
	}
	public fun collect(flow: Flow<T>,startCallback: StartCallback, resultCallback: ResultCallback<T>) {
		return collect(flow, startCallback, resultCallback, null)
	}
	public fun collect(flow: Flow<T>, resultCallback: ResultCallback<T>) {
		return collect(flow, null, resultCallback, null)
	}
	public fun collect(flow: Flow<T>, resultCallback: ResultCallback<T>, completeCallback: CompletionCallback) {
		return collect(flow, null, resultCallback, completeCallback)
	}
	public fun collect(
			flow: Flow<T>,
			startCallback: StartCallback?,
			resultCallback: ResultCallback<T>,
			completeCallback: CompletionCallback?,
	) {
		coroutineScope.launch {
			flow
					.onStart { startCallback?.onStart() }
					.onCompletion { completeCallback?.onCompletion(it) }
					.collect { resultCallback.onResult(it) }
		}
	}
	public fun close() {
		coroutineScope.cancel()
	}
}
