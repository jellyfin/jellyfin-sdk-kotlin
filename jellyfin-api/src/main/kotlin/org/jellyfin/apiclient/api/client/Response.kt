package org.jellyfin.apiclient.api.client

import kotlin.reflect.KProperty

class Response<T>(
	private val data: T
) {
	@JvmSynthetic
	operator fun getValue(thisRef: Any?, property: KProperty<*>): T = data

	fun getData() = data
}
