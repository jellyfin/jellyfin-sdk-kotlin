package org.jellyfin.apiclient.logging

class NullLogger : ILogger {
	override fun debug(str: String, vararg args: Any) = Unit
	override fun info(str: String, vararg args: Any) = Unit
	override fun error(str: String, vararg args: Any) = Unit
	override fun error(str: String, exception: Exception, vararg args: Any) = Unit
}
