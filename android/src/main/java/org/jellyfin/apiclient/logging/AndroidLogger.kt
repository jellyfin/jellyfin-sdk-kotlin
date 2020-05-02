package org.jellyfin.apiclient.logging

import android.util.Log
import org.jellyfin.apiclient.model.logging.ILogger

/**
 * ILogger implementation for Android. Uses the [android.util.Log] class.
 */
class AndroidLogger(
	private val tag: String = "jellyfin-apiclient"
) : ILogger {
	private fun getFormattedString(raw: String, vararg parameters: Any): String {
		return String.format(raw, *parameters)
	}

	override fun info(msg: String, vararg parameters: Any) {
		Log.i(tag, getFormattedString(msg, *parameters))
	}

	override fun error(msg: String, vararg parameters: Any) {
		Log.e(tag, getFormattedString(msg, *parameters))
	}

	override fun warn(msg: String, vararg parameters: Any) {
		Log.w(tag, getFormattedString(msg, *parameters))
	}

	override fun debug(msg: String, vararg parameters: Any) {
		Log.d(tag, getFormattedString(msg, *parameters))
	}

	override fun fatal(msg: String, vararg parameters: Any) {
		Log.wtf(tag, getFormattedString(msg, *parameters))
	}

	override fun fatalException(msg: String, exception: Exception, vararg parameters: Any) {
		Log.wtf(tag, getFormattedString(msg, *parameters), exception)
	}

	override fun errorException(msg: String, exception: Exception, vararg parameters: Any) {
		Log.e(tag, getFormattedString(msg, *parameters), exception)
	}
}
