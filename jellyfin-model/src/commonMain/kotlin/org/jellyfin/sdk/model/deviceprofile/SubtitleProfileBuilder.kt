package org.jellyfin.sdk.model.deviceprofile

import org.jellyfin.sdk.model.api.SubtitleDeliveryMethod
import org.jellyfin.sdk.model.api.SubtitleProfile

@DeviceProfileBuilderDsl
public class SubtitleProfileBuilder {
	/**
	 * @see [SubtitleProfile.method]
	 */
	public var method: SubtitleDeliveryMethod = SubtitleDeliveryMethod.EMBED

	/**
	 * @see [SubtitleProfile.format]
	 */
	public var format: String? = null

	/**
	 * @see [SubtitleProfile.language]
	 */
	public var language: String? = null

	/**
	 * @see [SubtitleProfile.container]
	 */
	public var container: String? = null

	public fun build(): SubtitleProfile = SubtitleProfile(
		format = format,
		method = method,
		language = language,
		container = container,
	)
}

@DeviceProfileBuilderDsl
public fun buildSubtitleProfile(
	body: SubtitleProfileBuilder.() -> Unit,
): SubtitleProfile = SubtitleProfileBuilder()
	.apply(body)
	.build()
