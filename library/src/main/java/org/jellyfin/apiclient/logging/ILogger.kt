package org.jellyfin.apiclient.logging

interface ILogger {
	fun debug(str: String, vararg args: Any)
	fun info(str: String, vararg args: Any)
	fun error(str: String, vararg args: Any)
	fun error(str: String, exception: Exception, vararg args: Any)
}
