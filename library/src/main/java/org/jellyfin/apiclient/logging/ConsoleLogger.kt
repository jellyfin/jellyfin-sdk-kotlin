package org.jellyfin.apiclient.logging

class ConsoleLogger : ILogger {
	override fun debug(str: String, vararg args: Any) {
		println(if (args.isNotEmpty()) String.format(str, *args) else str)
	}

	override fun info(str: String, vararg args: Any) {
		println(if (args.isNotEmpty()) String.format(str, *args) else str)
	}

	override fun error(str: String, vararg args: Any) {
		println(if (args.isNotEmpty()) String.format(str, *args) else str)
	}

	override fun error(str: String, exception: Exception, vararg args: Any) {
		println(if (args.isNotEmpty()) String.format(str, *args) else str)
		println(exception.stackTrace)
	}
}
