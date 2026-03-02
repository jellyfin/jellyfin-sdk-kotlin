package org.jellyfin.openapi.compare

import org.jellyfin.openapi.compare.model.CompareModel
import org.jellyfin.openapi.compare.model.CompareModelConstant
import org.jellyfin.openapi.compare.model.CompareModelProperty
import org.jellyfin.openapi.compare.model.buildCompareCollectionDiff
import org.jellyfin.openapi.compare.model.buildCompareValueDiffCollection
import org.jellyfin.openapi.compare.model.emptyCompareCollectionDiff
import org.jellyfin.openapi.model.ApiModel
import org.jellyfin.openapi.model.EnumApiModel
import org.jellyfin.openapi.model.GeneratorContext
import org.jellyfin.openapi.model.ObjectApiModel

class ModelComparator {
	private fun compareObjectModelProperties(
		oldModel: ObjectApiModel?,
		newModel: ObjectApiModel,
	) = buildCompareCollectionDiff(
		first = oldModel?.properties ?: newModel.properties,
		second = newModel.properties,
		keySelector = { name },
		comparator = { oldProperty, newProperty -> oldProperty == newProperty },
		createModel = { oldProperty, newProperty ->
			CompareModelProperty(
				newProperty.name,
				buildCompareValueDiffCollection(oldProperty, newProperty) {
					detect({ name }, "Name")
					detect({ description }, "Description")
					detect({ deprecated }, "Deprecated")
					detect({ defaultValue }, "Default value")
					// Check type and nullability separately
					detect({ type.copy(nullable = false) }, "Type")
					detect({ type.isNullable }, "Nullable")
				}
			)
		},
	)

	private fun compareEnumModelConstants(
		oldModel: EnumApiModel?,
		newModel: EnumApiModel,
	) = buildCompareCollectionDiff(
		first = oldModel?.constants ?: newModel.constants,
		second = newModel.constants,
		keySelector = { this },
		comparator = { oldConstant, newConstant -> oldConstant == newConstant },
		createModel = { _, newConstant ->
			CompareModelConstant(
				name = newConstant,
				changes = buildCompareValueDiffCollection(oldModel, newModel) {
					detect({ name }, "Name")
				}
			)
		},
	)

	private fun compareModel(
		oldModel: ApiModel?,
		newModel: ApiModel,
	) = CompareModel(
		name = newModel.name,
		description = newModel.description,
		properties = when {
			newModel is ObjectApiModel -> compareObjectModelProperties(oldModel as? ObjectApiModel?, newModel)
			else -> emptyCompareCollectionDiff()
		},
		constants = when {
			newModel is EnumApiModel -> compareEnumModelConstants(oldModel as? EnumApiModel?, newModel)
			else -> emptyCompareCollectionDiff()
		},
		changes = buildCompareValueDiffCollection(oldModel, newModel) {
			detect({ name }, "Name")
			detect({ description }, "Description")
			detect({ deprecated }, "Deprecated")

			if (oldModel != null && oldModel::class != newModel::class) {
				add("ObjectType", oldModel::class, newModel::class)
			}
		},
	)

	fun compare(
		oldSchema: GeneratorContext,
		newSchema: GeneratorContext,
	) = buildCompareCollectionDiff(
		first = oldSchema.models,
		second = newSchema.models,
		keySelector = { name },
		createModel = ::compareModel
	)
}
