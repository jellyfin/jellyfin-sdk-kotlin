package org.jellyfin.sdk.model.deviceprofile

import org.jellyfin.sdk.model.api.CodecProfile
import org.jellyfin.sdk.model.api.CodecType
import org.jellyfin.sdk.model.api.ProfileCondition

@DeviceProfileBuilderDsl
public class CodecProfileBuilder {
	private var conditions = mutableListOf<ProfileCondition>()
	private var applyConditions = mutableListOf<ProfileCondition>()

	/**
	 * @see [CodecProfile.type]
	 */
	public var type: CodecType = CodecType.VIDEO_AUDIO

	/**
	 * @see [CodecProfile.container]
	 */
	public var container: String? = null

	/**
	 * @see [CodecProfile.codec]
	 */
	public var codec: String? = null

	public fun condition(vararg condition: ProfileCondition) {
		conditions.addAll(condition)
	}

	public fun conditions(body: ProfileConditionsBuilder.() -> Unit) {
		conditions.addAll(ProfileConditionsBuilder().apply(body).build())
	}

	public fun applyConditions(body: ProfileConditionsBuilder.() -> Unit) {
		applyConditions.addAll(ProfileConditionsBuilder().apply(body).build())
	}

	public fun build(): CodecProfile = CodecProfile(
		type = type,
		conditions = conditions,
		applyConditions = applyConditions,
		codec = codec,
		container = container,
	)
}

@DeviceProfileBuilderDsl
public fun buildCodecProfile(
	body: CodecProfileBuilder.() -> Unit,
): CodecProfile = CodecProfileBuilder()
	.apply(body)
	.build()
