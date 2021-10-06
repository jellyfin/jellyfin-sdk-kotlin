package org.jellyfin.testutils

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.runBlocking

public actual fun runTest(block: suspend CoroutineScope.() -> Unit): Unit = runBlocking(block = block)
