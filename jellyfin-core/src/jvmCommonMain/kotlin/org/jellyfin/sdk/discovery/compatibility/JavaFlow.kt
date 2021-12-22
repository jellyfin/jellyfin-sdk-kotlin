package org.jellyfin.sdk.discovery.compatibility

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.onCompletion
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch
import java.io.Closeable

/**
 * Compatibility class to use Kotlin Coroutine [Flow] in Java files.
 */
public object JavaFlow {
	public fun interface ResultCallback<T> {
		public fun onResult(result: T)
	}

	public fun interface StartCallback {
		public fun onStart()
	}

	public fun interface CompletionCallback {
		public fun onCompletion(error: Throwable?)
	}

	public class FlowJob(private val job: Job) : Closeable {
		public override fun close(): Unit = job.cancel()
	}

	/**
	 * Collect all entries in a given [Flow].
	 *
	 * @param flow The [Flow] instance.
	 * @param startCallback Called when the flow starts emitting entries.
	 * @param resultCallback Called for each entry.
	 * @param completeCallback Called when the flow ends.
	 */
	@JvmOverloads
	@JvmStatic
	public fun <T> collect(
		flow: Flow<T>,
		startCallback: StartCallback? = null,
		resultCallback: ResultCallback<T>,
		completeCallback: CompletionCallback? = null,
		coroutineScope: CoroutineScope = CoroutineScope(Dispatchers.Default),
	): FlowJob = coroutineScope.launch {
		flow
			.onStart { startCallback?.onStart() }
			.onCompletion { error -> completeCallback?.onCompletion(error) }
			.collect { entry -> resultCallback.onResult(entry) }
	}.let(::FlowJob)
}
