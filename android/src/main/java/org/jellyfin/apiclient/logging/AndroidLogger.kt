package org.jellyfin.apiclient.logging

import android.util.Log

/**
 * ILogger implementation for Android. Uses the [android.util.Log] class.
 */
class AndroidLogger(
	private val tag: String = "jellyfin-apiclient"
) : ILogger {
	private fun getFormattedString(raw: String, vararg parameters: Any): String {
		return String.format(raw, *parameters)
	}

	override fun debug(msg: String, vararg parameters: Any) {
		Log.d(tag, getFormattedString(msg, *parameters))
	}

	override fun info(msg: String, vararg parameters: Any) {
		Log.i(tag, getFormattedString(msg, *parameters))
	}

	override fun error(msg: String, vararg parameters: Any) {
		Log.e(tag, getFormattedString(msg, *parameters))
	}

	override fun error(msg: String, exception: Exception, vararg parameters: Any) {
		Log.e(tag, getFormattedString(msg, *parameters), exception)
	}
}
