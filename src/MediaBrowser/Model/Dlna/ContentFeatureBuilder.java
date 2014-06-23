package MediaBrowser.Model.Dlna;

import MediaBrowser.Model.MediaInfo.*;

public class ContentFeatureBuilder
{
	private DeviceProfile _profile;

	public ContentFeatureBuilder(DeviceProfile profile)
	{
		_profile = profile;
	}

	public final String BuildImageHeader(String container, Integer width, Integer height)
	{
		String orgOp = ";DLNA.ORG_OP=" + DlnaMaps.GetImageOrgOpValue();

		// 0 = native, 1 = transcoded
		final String orgCi = ";DLNA.ORG_CI=0";

		DlnaFlags flagValue = DlnaFlags.forValue(DlnaFlags.StreamingTransferMode.getValue() | DlnaFlags.BackgroundTransferMode.getValue() | DlnaFlags.DlnaV15.getValue());

		String dlnaflags = String.format(";DLNA.ORG_FLAGS=%1$s", DlnaMaps.FlagsToString(flagValue));

		ResponseProfile mediaProfile = _profile.GetImageMediaProfile(container, width, height);

		String orgPn = mediaProfile == null ? null : mediaProfile.getOrgPn();

		if (tangible.DotNetToJavaStringHelper.isNullOrEmpty(orgPn))
		{
			orgPn = GetImageOrgPnValue(container, width, height);
		}

		String contentFeatures = tangible.DotNetToJavaStringHelper.isNullOrEmpty(orgPn) ? "" : "DLNA.ORG_PN=" + orgPn;

		return (contentFeatures + orgOp + orgCi + dlnaflags).trim(';');
	}

	public final String BuildAudioHeader(String container, String audioCodec, Integer audioBitrate, Integer audioSampleRate, Integer audioChannels, boolean isDirectStream, Long runtimeTicks, TranscodeSeekInfo transcodeSeekInfo)
	{
		// first bit means Time based seek supported, second byte range seek supported (not sure about the order now), so 01 = only byte seek, 10 = time based, 11 = both, 00 = none
		String orgOp = ";DLNA.ORG_OP=" + DlnaMaps.GetOrgOpValue(runtimeTicks != null, isDirectStream, transcodeSeekInfo);

		// 0 = native, 1 = transcoded
		String orgCi = isDirectStream ? ";DLNA.ORG_CI=0" : ";DLNA.ORG_CI=1";

		DlnaFlags flagValue = DlnaFlags.forValue(DlnaFlags.StreamingTransferMode.getValue() | DlnaFlags.BackgroundTransferMode.getValue() | DlnaFlags.DlnaV15.getValue());

		if (isDirectStream)
		{
			//flagValue = flagValue | DlnaFlags.DLNA_ORG_FLAG_BYTE_BASED_SEEK;
		}
		else if (runtimeTicks != null)
		{
			//flagValue = flagValue | DlnaFlags.DLNA_ORG_FLAG_TIME_BASED_SEEK;
		}

		String dlnaflags = String.format(";DLNA.ORG_FLAGS=%1$s", DlnaMaps.FlagsToString(flagValue));

		ResponseProfile mediaProfile = _profile.GetAudioMediaProfile(container, audioCodec, audioChannels, audioBitrate);

		String orgPn = mediaProfile == null ? null : mediaProfile.getOrgPn();

		if (tangible.DotNetToJavaStringHelper.isNullOrEmpty(orgPn))
		{
			orgPn = GetAudioOrgPnValue(container, audioBitrate, audioSampleRate, audioChannels);
		}

		String contentFeatures = tangible.DotNetToJavaStringHelper.isNullOrEmpty(orgPn) ? "" : "DLNA.ORG_PN=" + orgPn;

		return (contentFeatures + orgOp + orgCi + dlnaflags).trim(';');
	}

	public final String BuildVideoHeader(String container, String videoCodec, String audioCodec, Integer width, Integer height, Integer bitDepth, Integer videoBitrate, Integer audioChannels, Integer audioBitrate, TransportStreamTimestamp timestamp, boolean isDirectStream, Long runtimeTicks, String videoProfile, Double videoLevel, Float videoFramerate, Integer packetLength, TranscodeSeekInfo transcodeSeekInfo, Boolean isAnamorphic)
	{
		// first bit means Time based seek supported, second byte range seek supported (not sure about the order now), so 01 = only byte seek, 10 = time based, 11 = both, 00 = none
		String orgOp = ";DLNA.ORG_OP=" + DlnaMaps.GetOrgOpValue(runtimeTicks != null, isDirectStream, transcodeSeekInfo);

		// 0 = native, 1 = transcoded
		String orgCi = isDirectStream ? ";DLNA.ORG_CI=0" : ";DLNA.ORG_CI=1";

		DlnaFlags flagValue = DlnaFlags.forValue(DlnaFlags.StreamingTransferMode.getValue() | DlnaFlags.BackgroundTransferMode.getValue() | DlnaFlags.DlnaV15.getValue());

		if (isDirectStream)
		{
			//flagValue = flagValue | DlnaFlags.DLNA_ORG_FLAG_BYTE_BASED_SEEK;
		}
		else if (runtimeTicks != null)
		{
			//flagValue = flagValue | DlnaFlags.DLNA_ORG_FLAG_TIME_BASED_SEEK;
		}

		String dlnaflags = String.format(";DLNA.ORG_FLAGS=%1$s", DlnaMaps.FlagsToString(flagValue));

		ResponseProfile mediaProfile = _profile.GetVideoMediaProfile(container, audioCodec, videoCodec, audioBitrate, audioChannels, width, height, bitDepth, videoBitrate, videoProfile, videoLevel, videoFramerate, packetLength, timestamp, isAnamorphic);

		String orgPn = mediaProfile == null ? null : mediaProfile.getOrgPn();

		if (tangible.DotNetToJavaStringHelper.isNullOrEmpty(orgPn))
		{
			for (String s : GetVideoOrgPnValue(container, videoCodec, audioCodec, width, height, timestamp))
			{
				orgPn = s;
				break;
			}
		}

		if (tangible.DotNetToJavaStringHelper.isNullOrEmpty(orgPn))
		{
			// TODO: Support multiple values and return multiple headers?
			for (String s : ((orgPn != null) ? orgPn : "").split("[,]", -1))
			{
				orgPn = s;
				break;
			}
		}

		String contentFeatures = tangible.DotNetToJavaStringHelper.isNullOrEmpty(orgPn) ? "" : "DLNA.ORG_PN=" + orgPn;

		return (contentFeatures + orgOp + orgCi + dlnaflags).trim(';');
	}

	private String GetImageOrgPnValue(String container, Integer width, Integer height)
	{
		MediaFormatProfile format = (new MediaFormatProfileResolver()).ResolveImageFormat(container, width, height);

		return format != null ? format.toString() : null;
	}

	private String GetAudioOrgPnValue(String container, Integer audioBitrate, Integer audioSampleRate, Integer audioChannels)
	{
		MediaFormatProfile format = (new MediaFormatProfileResolver()).ResolveAudioFormat(container, audioBitrate, audioSampleRate, audioChannels);

		return format != null ? format.toString() : null;
	}

	private java.util.ArrayList<String> GetVideoOrgPnValue(String container, String videoCodec, String audioCodec, Integer width, Integer height, TransportStreamTimestamp timestamp)
	{
		java.util.ArrayList<String> list = new java.util.ArrayList<String>();
		for (MediaFormatProfile i : (new MediaFormatProfileResolver()).ResolveVideoFormat(container, videoCodec, audioCodec, width, height, timestamp))
		{
			list.add(i.toString());
		}
		return list;
	}
}