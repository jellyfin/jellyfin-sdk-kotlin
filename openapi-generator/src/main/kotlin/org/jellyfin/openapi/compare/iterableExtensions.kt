package org.jellyfin.openapi.compare

fun <T, K> Iterable<T>.compare(
	other: Iterable<T>,
	keySelector: (T) -> K,
	comparator: (T, T) -> Boolean = { a, b -> a == b },
): Iterable<CompareEntry<T>> {
	val references = map(keySelector).toSet()
	val otherReferences = other.associateBy(keySelector)

	val entries = mutableListOf<CompareEntry<T>>()

	other.forEach { otherItem ->
		val otherKey = keySelector(otherItem)
		if (otherKey !in references) entries.add(CompareEntry.Added(otherItem))
	}

	forEach { item ->
		val otherItem = otherReferences[keySelector(item)]

		if (otherItem == null) entries.add(CompareEntry.Removed(item))
		else if (!comparator(item, otherItem)) entries.add(CompareEntry.Modified(item, otherItem))
		else entries.add(CompareEntry.Unchanged(item))
	}

	return entries
}

sealed interface CompareEntry<T> {
	data class Added<T>(val item: T) : CompareEntry<T>
	data class Modified<T>(val before: T, val after: T) : CompareEntry<T>
	data class Unchanged<T>(val item: T) : CompareEntry<T>
	data class Removed<T>(val item: T) : CompareEntry<T>
}
