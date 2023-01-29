package org.jellyfin.openapi.builder

interface Builder<T, R> {
	fun build(data: T): R
}
