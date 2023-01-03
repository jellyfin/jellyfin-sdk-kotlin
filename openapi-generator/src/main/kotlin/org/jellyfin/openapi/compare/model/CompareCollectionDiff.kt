package org.jellyfin.openapi.compare.model

import kotlinx.serialization.Serializable
import org.jellyfin.openapi.compare.CompareEntry
import org.jellyfin.openapi.compare.compare

@Serializable
data class CompareCollectionDiff<T>(
	val added: Collection<T> = emptyList(),
	val removed: Collection<T> = emptyList(),
	val modified: Collection<T> = emptyList(),
	val unchanged: Collection<T> = emptyList(),
) {
	fun isEmpty() = added.isEmpty() && removed.isEmpty() && modified.isEmpty() && unchanged.isEmpty()
	fun isNotEmpty() = added.isNotEmpty() || removed.isNotEmpty() || modified.isNotEmpty() || unchanged.isNotEmpty()

	fun isChanged() = added.isNotEmpty() || removed.isNotEmpty() || modified.isNotEmpty()
	fun isNotChanged() = added.isEmpty() && removed.isEmpty() && modified.isEmpty()
}

fun <T> emptyCompareCollectionDiff() = CompareCollectionDiff<T>()

@Suppress("LongParameterList")
fun <T, TN, K> buildCompareCollectionDiff(
	first: Iterable<T>,
	second: Iterable<T>,
	keySelector: T.() -> K,
	comparator: (T, T) -> Boolean = { a, b -> a == b },
	createModel: (T?, T) -> TN,
): CompareCollectionDiff<TN> {
	val result = first.compare(second, keySelector, comparator)

	return CompareCollectionDiff(
		added = result.filterIsInstance<CompareEntry.Added<T>>().map { createModel(null, it.item) },
		removed = result.filterIsInstance<CompareEntry.Removed<T>>().map { createModel(null, it.item) },
		modified = result.filterIsInstance<CompareEntry.Modified<T>>().map { createModel(it.before, it.after) },
		unchanged = result.filterIsInstance<CompareEntry.Unchanged<T>>().map { createModel(null, it.item) },
	)
}
