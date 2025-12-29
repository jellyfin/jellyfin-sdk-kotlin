package org.jellyfin.openapi

import com.squareup.kotlinpoet.FileSpec
import io.github.oshai.kotlinlogging.KotlinLogging
import org.jellyfin.openapi.model.GeneratorResult
import java.io.File
import java.nio.file.Files
import java.nio.file.LinkOption
import java.security.MessageDigest
import kotlin.io.path.Path
import kotlin.io.path.isRegularFile
import kotlin.io.path.readText
import kotlin.io.path.relativeTo
import kotlin.streams.asSequence

private val logger = KotlinLogging.logger { }

class Verification(
	private val apiOutputDir: File,
	private val modelsOutputDir: File,
) {
	private fun getFileTree(directory: File): Map<String, ByteArray> {
		assert(directory.exists() && directory.isDirectory)

		val digest = MessageDigest.getInstance("MD5")
		val path = directory.toPath()

		return Files
			.walk(path)
			.asSequence()
			.filter { it.isRegularFile(LinkOption.NOFOLLOW_LINKS) }
			.map {
				val source = it
					.readText()
					.replace(System.lineSeparator(), "\n") // Kotlinpoet always uses \n
					.toByteArray()
				val hash = digest.digest(source)

				it.relativeTo(path).toString() to hash
			}
			.toMap()
	}

	private fun getFileTree(fileSpecs: Collection<FileSpec>) = lazy {
		val digest = MessageDigest.getInstance("MD5")

		fileSpecs.associate {
			val obj = it.toJavaFileObject()
			val hash = digest.digest(it.toString().toByteArray())

			Path(obj.name).toString() to hash
		}
	}

	private fun compare(current: Map<String, ByteArray>, new: Map<String, ByteArray>): Boolean {
		val removedKeys = current.keys subtract new.keys
		val newKeys = new.keys subtract current.keys
		val modifiedKeys = current.keys
			.intersect(new.keys)
			.filter { key -> !current[key].contentEquals(new[key]) }

		removedKeys.forEach { key ->
			logger.error { "$key: removed from sources." }
		}

		newKeys.forEach { key ->
			logger.error { "$key: added to sources." }
		}

		modifiedKeys.forEach { key ->
			logger.error {
				"$key: modified (${current[key].toMd5String()} -> ${new[key].toMd5String()})."
			}
		}

		return removedKeys.isEmpty() && newKeys.isEmpty() && modifiedKeys.isEmpty()
	}

	private fun ByteArray?.toMd5String(): String =
		this?.joinToString(separator = "") { byte -> "%02x".format(byte) } ?: "null"

	fun verify(result: GeneratorResult): Boolean {
		val currentFiles = getFileTree(apiOutputDir) + getFileTree(modelsOutputDir)
		val newFiles by getFileTree(result.files)

		return compare(currentFiles, newFiles)
	}
}
