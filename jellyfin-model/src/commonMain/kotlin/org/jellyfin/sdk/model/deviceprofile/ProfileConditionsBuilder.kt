package org.jellyfin.sdk.model.deviceprofile

import org.jellyfin.sdk.model.api.ProfileCondition
import org.jellyfin.sdk.model.api.ProfileConditionType
import org.jellyfin.sdk.model.api.ProfileConditionValue

@DeviceProfileBuilderDsl
public class ProfileConditionsBuilder(parent: Collection<ProfileCondition>? = null) {
	private val conditions = parent?.toMutableList() ?: mutableListOf()

	// Equals
	public fun equals(property: ProfileConditionValue, value: String?, required: Boolean = false): ProfileCondition =
		add(
			operator = ProfileConditionType.EQUALS,
			property = property,
			value = value,
			required = required,
		)

	public infix fun ProfileConditionValue.equals(value: String?): ProfileCondition = equals(this, value, false)

	public fun equals(property: ProfileConditionValue, value: Number, required: Boolean = false): ProfileCondition =
		add(
			operator = ProfileConditionType.EQUALS,
			property = property,
			value = value.toString(),
			required = required,
		)

	public infix fun ProfileConditionValue.equals(value: Number): ProfileCondition = equals(this, value, false)

	public fun equals(property: ProfileConditionValue, value: Boolean, required: Boolean = false): ProfileCondition =
		add(
			operator = ProfileConditionType.EQUALS,
			property = property,
			value = value.toString(),
			required = required,
		)

	public infix fun ProfileConditionValue.equals(value: Boolean): ProfileCondition = equals(this, value, false)

	// Not equals
	public fun notEquals(property: ProfileConditionValue, value: String, required: Boolean = false): ProfileCondition =
		add(
			operator = ProfileConditionType.NOT_EQUALS,
			property = property,
			value = value,
			required = required,
		)

	public infix fun ProfileConditionValue.notEquals(value: String): ProfileCondition = notEquals(this, value, false)

	public fun notEquals(property: ProfileConditionValue, value: Number, required: Boolean = false): ProfileCondition =
		add(
			operator = ProfileConditionType.NOT_EQUALS,
			property = property,
			value = value.toString(),
			required = required,
		)

	public infix fun ProfileConditionValue.notEquals(value: Number): ProfileCondition = notEquals(this, value, false)

	public fun notEquals(property: ProfileConditionValue, value: Boolean, required: Boolean = false): ProfileCondition =
		add(
			operator = ProfileConditionType.NOT_EQUALS,
			property = property,
			value = value.toString(),
			required = required,
		)

	public infix fun ProfileConditionValue.notEquals(value: Boolean): ProfileCondition = notEquals(this, value, false)

	// Greater than or equals
	public fun greaterThanOrEquals(
		property: ProfileConditionValue,
		value: Number,
		required: Boolean = false
	): ProfileCondition = add(
		operator = ProfileConditionType.GREATER_THAN_EQUAL,
		property = property,
		value = value.toString(),
		required = required,
	)

	public infix fun ProfileConditionValue.greaterThanOrEquals(value: Number): ProfileCondition =
		greaterThanOrEquals(this, value, false)

	// Lower than or equals
	public fun lowerThanOrEquals(
		property: ProfileConditionValue,
		value: Number,
		required: Boolean = false
	): ProfileCondition = add(
		operator = ProfileConditionType.LESS_THAN_EQUAL,
		property = property,
		value = value.toString(),
		required = required,
	)

	public infix fun ProfileConditionValue.lowerThanOrEquals(value: Number): ProfileCondition =
		lowerThanOrEquals(this, value, false)

	// In collection
	@JvmName("inStringCollection")
	public fun inCollection(
		property: ProfileConditionValue,
		value: Collection<String>,
		required: Boolean = false
	): ProfileCondition =
		add(
			operator = ProfileConditionType.EQUALS_ANY,
			property = property,
			value = value.joinToString("|"),
			required = required,
		)

	@JvmName("inStringCollection")
	public infix fun ProfileConditionValue.inCollection(value: Collection<String>): ProfileCondition =
		inCollection(this, value, false)

	@JvmName("inBooleanCollection")
	public fun inCollection(
		property: ProfileConditionValue,
		value: Collection<Boolean>,
		required: Boolean = false
	): ProfileCondition =
		add(
			operator = ProfileConditionType.EQUALS_ANY,
			property = property,
			value = value.joinToString("|") { it.toString() },
			required = required,
		)

	@JvmName("inBooleanCollection")
	public infix fun ProfileConditionValue.inCollection(value: Collection<Boolean>): ProfileCondition =
		inCollection(this, value, false)

	@JvmName("inNumberCollection")
	public fun inCollection(
		property: ProfileConditionValue,
		value: Collection<Number>,
		required: Boolean = false
	): ProfileCondition =
		add(
			operator = ProfileConditionType.EQUALS_ANY,
			property = property,
			value = value.joinToString("|") { it.toString() },
			required = required,
		)

	@JvmName("inNumberCollection")
	public infix fun ProfileConditionValue.inCollection(value: Collection<Number>): ProfileCondition =
		inCollection(this, value, false)

	public fun add(
		property: ProfileConditionValue,
		operator: ProfileConditionType,
		value: String?,
		required: Boolean,
	): ProfileCondition = add(
		condition = ProfileCondition(
			condition = operator,
			property = property,
			value = value,
			isRequired = required,
		)
	)

	public fun add(condition: ProfileCondition): ProfileCondition {
		conditions.add(condition)
		return condition
	}

	public fun addAll(conditions: Collection<ProfileCondition>): Collection<ProfileCondition> {
		this.conditions.addAll(conditions)
		return conditions
	}

	public fun build(): Collection<ProfileCondition> = conditions.toList()
}

@DeviceProfileBuilderDsl
public fun buildProfileConditions(
	body: ProfileConditionsBuilder.() -> Unit,
): Collection<ProfileCondition> = ProfileConditionsBuilder()
	.apply(body)
	.build()

@DeviceProfileBuilderDsl
public fun Collection<ProfileCondition>.buildUpon(
	body: ProfileConditionsBuilder.() -> Unit,
): Collection<ProfileCondition> = ProfileConditionsBuilder(this)
	.apply(body)
	.build()
