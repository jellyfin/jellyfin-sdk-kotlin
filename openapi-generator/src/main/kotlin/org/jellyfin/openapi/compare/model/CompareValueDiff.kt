package org.jellyfin.openapi.compare.model

import kotlinx.serialization.Serializable

@Serializable
data class CompareValueDiff(val name: String, val from: String, val to: String)

fun <T> buildCompareValueDiffCollection(
	a: T?,
	b: T,
	body: BuildCompareValueDiffCollectionContext<T>.() -> Unit,
): Collection<CompareValueDiff> {
	if (a == null) return emptyList()

	val context = BuildCompareValueDiffCollectionContext(a, b)
	context.body()
	return context.values
}

class BuildCompareValueDiffCollectionContext<T>(
	private val a: T,
	private val b: T,
) {
	private val _values = mutableListOf<CompareValueDiff>()
	val values get() = _values.toList()

	fun <K> detect(selector: T.() -> K, name: String) {
		val valueA = a.selector()
		val valueB = b.selector()

		if (valueA != valueB) add(name, valueA, valueB)
	}

	fun <K> add(name: String, from: K, to: K) {
		_values.add(CompareValueDiff(name, from.toString(), to.toString()))
	}
}
