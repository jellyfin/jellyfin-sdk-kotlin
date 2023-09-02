package org.jellyfin.sdk.model.deviceprofile

import org.jellyfin.sdk.model.api.DirectPlayProfile
import org.jellyfin.sdk.model.api.DlnaProfileType

@DeviceProfileBuilderDsl
public class DirectPlayProfileBuilder {
	private val containers = mutableListOf<String>()
	private val videoCodecs = mutableListOf<String>()
	private val audioCodecs = mutableListOf<String>()

	/**
	 * @see [DirectPlayProfile.type]
	 */
	public var type: DlnaProfileType = DlnaProfileType.VIDEO

	public fun container(vararg container: String) {
		containers.addAll(container)
	}

	public fun videoCodec(vararg codec: String) {
		videoCodecs.addAll(codec)
	}

	public fun audioCodec(vararg codec: String) {
		audioCodecs.addAll(codec)
	}

	public fun build(): DirectPlayProfile = DirectPlayProfile(
		type = type,
		container = containers.joinToString(","),
		videoCodec = videoCodecs.joinToString(","),
		audioCodec = audioCodecs.joinToString(","),
	)
}

@DeviceProfileBuilderDsl
public fun buildDirectPlayProfile(
	body: DirectPlayProfileBuilder.() -> Unit,
): DirectPlayProfile = DirectPlayProfileBuilder()
	.apply(body)
	.build()
