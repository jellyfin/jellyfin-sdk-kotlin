package org.jellyfin.sdk.model.deviceprofile

import org.jellyfin.sdk.model.api.CodecProfile
import org.jellyfin.sdk.model.api.ContainerProfile
import org.jellyfin.sdk.model.api.DeviceProfile
import org.jellyfin.sdk.model.api.DirectPlayProfile
import org.jellyfin.sdk.model.api.SubtitleDeliveryMethod
import org.jellyfin.sdk.model.api.SubtitleProfile
import org.jellyfin.sdk.model.api.TranscodingProfile

@DeviceProfileBuilderDsl
public class DeviceProfileBuilder(
	parent: DeviceProfile? = null,
) {
	/**
	 * @see [DeviceProfile.id].
	 */
	public var id: String? = null

	/**
	 * @see [DeviceProfile.name].
	 */
	public var name: String? = null

	/**
	 * @see [DeviceProfile.maxStreamingBitrate].
	 */
	public var maxStreamingBitrate: Int? = parent?.maxStreamingBitrate

	/**
	 * @see [DeviceProfile.maxStaticBitrate].
	 */
	public var maxStaticBitrate: Int? = parent?.maxStaticBitrate

	/**
	 * @see [DeviceProfile.musicStreamingTranscodingBitrate].
	 */
	public var musicStreamingTranscodingBitrate: Int? = parent?.musicStreamingTranscodingBitrate

	/**
	 * @see [DeviceProfile.maxStaticMusicBitrate].
	 */
	public var maxStaticMusicBitrate: Int? = parent?.maxStaticMusicBitrate

	/**
	 * @see [DeviceProfile.directPlayProfiles].
	 */
	public val directPlayProfiles: MutableCollection<DirectPlayProfile> =
		parent?.directPlayProfiles?.toMutableList() ?: mutableListOf()

	/**
	 * @see [DeviceProfile.transcodingProfiles].
	 */
	public val transcodingProfiles: MutableCollection<TranscodingProfile> =
		parent?.transcodingProfiles?.toMutableList() ?: mutableListOf()

	/**
	 * @see [DeviceProfile.containerProfiles].
	 */
	public val containerProfiles: MutableCollection<ContainerProfile> =
		parent?.containerProfiles?.toMutableList() ?: mutableListOf()

	/**
	 * @see [DeviceProfile.codecProfiles].
	 */
	public val codecProfiles: MutableCollection<CodecProfile> =
		parent?.codecProfiles?.toMutableList() ?: mutableListOf()

	/**
	 * @see [DeviceProfile.subtitleProfiles].
	 */
	public val subtitleProfiles: MutableCollection<SubtitleProfile> =
		parent?.subtitleProfiles?.toMutableList() ?: mutableListOf()

	public fun directPlayProfile(
		body: DirectPlayProfileBuilder.() -> Unit,
	): DirectPlayProfile = add(buildDirectPlayProfile(body))

	public fun add(profile: DirectPlayProfile): DirectPlayProfile {
		directPlayProfiles.add(profile)
		return profile
	}

	public fun transcodingProfile(
		body: TranscodingProfileBuilder.() -> Unit,
	): TranscodingProfile = add(buildTranscodingProfile(body))

	public fun add(profile: TranscodingProfile): TranscodingProfile {
		transcodingProfiles.add(profile)
		return profile
	}

	public fun containerProfile(
		body: ContainerProfileBuilder.() -> Unit,
	): ContainerProfile = add(buildContainerProfile(body))

	public fun add(profile: ContainerProfile): ContainerProfile {
		containerProfiles.add(profile)
		return profile
	}

	public fun codecProfile(
		body: CodecProfileBuilder.() -> Unit,
	): CodecProfile = add(buildCodecProfile(body))

	public fun add(profile: CodecProfile): CodecProfile {
		codecProfiles.add(profile)
		return profile
	}

	public fun subtitleProfile(
		body: SubtitleProfileBuilder.() -> Unit,
	): SubtitleProfile = add(buildSubtitleProfile(body))

	public fun subtitleProfile(
		format: String,
		method: SubtitleDeliveryMethod,
		language: String? = null,
	): SubtitleProfile = add(buildSubtitleProfile {
		this.format = format
		this.method = method
		this.language = language
	})

	public fun add(profile: SubtitleProfile): SubtitleProfile {
		subtitleProfiles.add(profile)
		return profile
	}

	public fun build(): DeviceProfile = DeviceProfile(
		name = name,
		id = id,
		maxStreamingBitrate = maxStreamingBitrate,
		maxStaticBitrate = maxStaticBitrate,
		musicStreamingTranscodingBitrate = musicStreamingTranscodingBitrate,
		maxStaticMusicBitrate = maxStaticMusicBitrate,
		directPlayProfiles = directPlayProfiles.toList(),
		transcodingProfiles = transcodingProfiles.toList(),
		containerProfiles = containerProfiles.toList(),
		codecProfiles = codecProfiles.toList(),
		subtitleProfiles = subtitleProfiles.toList(),
		// DLNA only options that can be ignored
		supportedMediaTypes = "",
		xmlRootAttributes = emptyList(),
		responseProfiles = emptyList(),
	)
}

@DeviceProfileBuilderDsl
public fun buildDeviceProfile(
	body: DeviceProfileBuilder.() -> Unit,
): DeviceProfile = DeviceProfileBuilder()
	.apply(body)
	.build()

@DeviceProfileBuilderDsl
public fun DeviceProfile.buildUpon(
	body: DeviceProfileBuilder.() -> Unit,
): DeviceProfile = DeviceProfileBuilder(this)
	.apply(body)
	.build()
