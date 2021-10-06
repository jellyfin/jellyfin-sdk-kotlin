package org.jellyfin.testutils

import kotlinx.coroutines.CoroutineScope

public expect fun runTest(block: suspend CoroutineScope.() -> Unit)
