package org.jellyfin.sdk.model.deviceprofile

import org.jellyfin.sdk.model.api.DlnaProfileType
import org.jellyfin.sdk.model.api.EncodingContext
import org.jellyfin.sdk.model.api.ProfileCondition
import org.jellyfin.sdk.model.api.TranscodeSeekInfo
import org.jellyfin.sdk.model.api.TranscodingProfile

@DeviceProfileBuilderDsl
public class TranscodingProfileBuilder {
	private val videoCodecs = mutableListOf<String>()
	private val audioCodecs = mutableListOf<String>()
	private var conditions = mutableListOf<ProfileCondition>()

	/**
	 * @see [TranscodingProfile.type]
	 */
	public var type: DlnaProfileType = DlnaProfileType.VIDEO

	/**
	 * @see [TranscodingProfile.context]
	 */
	public var context: EncodingContext = EncodingContext.STREAMING

	/**
	 * @see [TranscodingProfile.protocol]
	 */
	public var protocol: String = "http"

	/**
	 * @see [TranscodingProfile.container]
	 */
	public var container: String = ""

	/**
	 * @see [TranscodingProfile.estimateContentLength]
	 */
	public var estimateContentLength: Boolean = false

	/**
	 * @see [TranscodingProfile.enableMpegtsM2TsMode]
	 */
	public var enableMpegtsM2TsMode: Boolean = false

	/**
	 * @see [TranscodingProfile.transcodeSeekInfo]
	 */
	public var transcodeSeekInfo: TranscodeSeekInfo = TranscodeSeekInfo.AUTO

	/**
	 * @see [TranscodingProfile.copyTimestamps]
	 */
	public var copyTimestamps: Boolean = false

	/**
	 * @see [TranscodingProfile.enableSubtitlesInManifest]
	 */
	public var enableSubtitlesInManifest: Boolean = false

	/**
	 * @see [TranscodingProfile.maxAudioChannels]
	 */
	public var maxAudioChannels: String? = null

	/**
	 * @see [TranscodingProfile.minSegments]
	 */
	public var minSegments: Int = 0

	/**
	 * @see [TranscodingProfile.segmentLength]
	 */
	public var segmentLength: Int = 0

	/**
	 * @see [TranscodingProfile.breakOnNonKeyFrames]
	 */
	public var breakOnNonKeyFrames: Boolean = false

	public fun videoCodec(vararg codec: String) {
		videoCodecs.addAll(codec)
	}

	public fun audioCodec(vararg codec: String) {
		audioCodecs.addAll(codec)
	}

	public fun conditions(body: ProfileConditionsBuilder.() -> Unit) {
		conditions.addAll(ProfileConditionsBuilder().apply(body).build())
	}

	public fun build(): TranscodingProfile = TranscodingProfile(
		type = type,
		context = context,
		protocol = protocol,
		container = container,
		videoCodec = videoCodecs.joinToString(","),
		audioCodec = audioCodecs.joinToString(","),
		estimateContentLength = estimateContentLength,
		enableMpegtsM2TsMode = enableMpegtsM2TsMode,
		transcodeSeekInfo = transcodeSeekInfo,
		copyTimestamps = copyTimestamps,
		enableSubtitlesInManifest = enableSubtitlesInManifest,
		maxAudioChannels = maxAudioChannels,
		minSegments = minSegments,
		segmentLength = segmentLength,
		breakOnNonKeyFrames = breakOnNonKeyFrames,
		conditions = conditions,
	)
}

@DeviceProfileBuilderDsl
public fun buildTranscodingProfile(
	body: TranscodingProfileBuilder.() -> Unit,
): TranscodingProfile = TranscodingProfileBuilder()
	.apply(body)
	.build()
