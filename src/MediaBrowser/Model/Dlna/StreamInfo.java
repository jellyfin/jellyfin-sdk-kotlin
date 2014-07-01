package MediaBrowser.Model.Dlna;

import MediaBrowser.Model.Drawing.*;
import MediaBrowser.Model.Dto.*;
import MediaBrowser.Model.Entities.*;
import MediaBrowser.Model.Extensions.*;
import MediaBrowser.Model.MediaInfo.*;

/** 
 Class StreamInfo.
*/
public class StreamInfo
{
	private String privateItemId;
	public final String getItemId()
	{
		return privateItemId;
	}
	public final void setItemId(String value)
	{
		privateItemId = value;
	}

	private boolean privateIsDirectStream;
	public final boolean getIsDirectStream()
	{
		return privateIsDirectStream;
	}
	public final void setIsDirectStream(boolean value)
	{
		privateIsDirectStream = value;
	}

	private DlnaProfileType privateMediaType = DlnaProfileType.values()[0];
	public final DlnaProfileType getMediaType()
	{
		return privateMediaType;
	}
	public final void setMediaType(DlnaProfileType value)
	{
		privateMediaType = value;
	}

	private String privateContainer;
	public final String getContainer()
	{
		return privateContainer;
	}
	public final void setContainer(String value)
	{
		privateContainer = value;
	}

	private String privateProtocol;
	public final String getProtocol()
	{
		return privateProtocol;
	}
	public final void setProtocol(String value)
	{
		privateProtocol = value;
	}

	private long privateStartPositionTicks;
	public final long getStartPositionTicks()
	{
		return privateStartPositionTicks;
	}
	public final void setStartPositionTicks(long value)
	{
		privateStartPositionTicks = value;
	}

	private String privateVideoCodec;
	public final String getVideoCodec()
	{
		return privateVideoCodec;
	}
	public final void setVideoCodec(String value)
	{
		privateVideoCodec = value;
	}
	private String privateVideoProfile;
	public final String getVideoProfile()
	{
		return privateVideoProfile;
	}
	public final void setVideoProfile(String value)
	{
		privateVideoProfile = value;
	}

	private String privateAudioCodec;
	public final String getAudioCodec()
	{
		return privateAudioCodec;
	}
	public final void setAudioCodec(String value)
	{
		privateAudioCodec = value;
	}

	private Integer privateAudioStreamIndex = null;
	public final Integer getAudioStreamIndex()
	{
		return privateAudioStreamIndex;
	}
	public final void setAudioStreamIndex(Integer value)
	{
		privateAudioStreamIndex = value;
	}

	private Integer privateSubtitleStreamIndex = null;
	public final Integer getSubtitleStreamIndex()
	{
		return privateSubtitleStreamIndex;
	}
	public final void setSubtitleStreamIndex(Integer value)
	{
		privateSubtitleStreamIndex = value;
	}

	private Integer privateMaxAudioChannels = null;
	public final Integer getMaxAudioChannels()
	{
		return privateMaxAudioChannels;
	}
	public final void setMaxAudioChannels(Integer value)
	{
		privateMaxAudioChannels = value;
	}

	private Integer privateAudioBitrate = null;
	public final Integer getAudioBitrate()
	{
		return privateAudioBitrate;
	}
	public final void setAudioBitrate(Integer value)
	{
		privateAudioBitrate = value;
	}

	private Integer privateVideoBitrate = null;
	public final Integer getVideoBitrate()
	{
		return privateVideoBitrate;
	}
	public final void setVideoBitrate(Integer value)
	{
		privateVideoBitrate = value;
	}

	private Integer privateVideoLevel = null;
	public final Integer getVideoLevel()
	{
		return privateVideoLevel;
	}
	public final void setVideoLevel(Integer value)
	{
		privateVideoLevel = value;
	}

	private Integer privateMaxWidth = null;
	public final Integer getMaxWidth()
	{
		return privateMaxWidth;
	}
	public final void setMaxWidth(Integer value)
	{
		privateMaxWidth = value;
	}
	private Integer privateMaxHeight = null;
	public final Integer getMaxHeight()
	{
		return privateMaxHeight;
	}
	public final void setMaxHeight(Integer value)
	{
		privateMaxHeight = value;
	}

	private Float privateMaxFramerate = null;
	public final Float getMaxFramerate()
	{
		return privateMaxFramerate;
	}
	public final void setMaxFramerate(Float value)
	{
		privateMaxFramerate = value;
	}

	private String privateDeviceProfileId;
	public final String getDeviceProfileId()
	{
		return privateDeviceProfileId;
	}
	public final void setDeviceProfileId(String value)
	{
		privateDeviceProfileId = value;
	}
	private String privateDeviceId;
	public final String getDeviceId()
	{
		return privateDeviceId;
	}
	public final void setDeviceId(String value)
	{
		privateDeviceId = value;
	}

	private Long privateRunTimeTicks = null;
	public final Long getRunTimeTicks()
	{
		return privateRunTimeTicks;
	}
	public final void setRunTimeTicks(Long value)
	{
		privateRunTimeTicks = value;
	}

	private TranscodeSeekInfo privateTranscodeSeekInfo = getTranscodeSeekInfo().values()[0];
	public final TranscodeSeekInfo getTranscodeSeekInfo()
	{
		return privateTranscodeSeekInfo;
	}
	public final void setTranscodeSeekInfo(TranscodeSeekInfo value)
	{
		privateTranscodeSeekInfo = value;
	}

	private boolean privateEstimateContentLength;
	public final boolean getEstimateContentLength()
	{
		return privateEstimateContentLength;
	}
	public final void setEstimateContentLength(boolean value)
	{
		privateEstimateContentLength = value;
	}

	private MediaSourceInfo privateMediaSource;
	public final MediaSourceInfo getMediaSource()
	{
		return privateMediaSource;
	}
	public final void setMediaSource(MediaSourceInfo value)
	{
		privateMediaSource = value;
	}

	public final String getMediaSourceId()
	{
		return getMediaSource() == null ? null : getMediaSource().getId();
	}

	public final String ToUrl(String baseUrl)
	{
		return ToDlnaUrl(baseUrl);
	}

	public final String ToDlnaUrl(String baseUrl)
	{
		if (tangible.DotNetToJavaStringHelper.isNullOrEmpty(baseUrl))
		{
			throw new IllegalArgumentException(baseUrl);
		}

		String dlnaCommand = BuildDlnaParam(this);

		String extension = tangible.DotNetToJavaStringHelper.isNullOrEmpty(getContainer()) ? "" : "." + getContainer();

		baseUrl = tangible.DotNetToJavaStringHelper.trimEnd(baseUrl, '/');

		if (getMediaType() == DlnaProfileType.Audio)
		{
			return String.format("%1$s/audio/%2$s/stream%3$s?%4$s", baseUrl, getItemId(), extension, dlnaCommand);
		}

		if (StringHelper.EqualsIgnoreCase(getProtocol(), "hls"))
		{
			return String.format("%1$s/videos/%2$s/stream.m3u8?%3$s", baseUrl, getItemId(), dlnaCommand);
		}

		return String.format("%1$s/videos/%2$s/stream%3$s?%4$s", baseUrl, getItemId(), extension, dlnaCommand);
	}

	private static String BuildDlnaParam(StreamInfo item)
	{
		String tempVar = item.getDeviceProfileId();
		String tempVar2 = item.getDeviceId();
		String tempVar3 = item.getMediaSourceId();
		String tempVar4 = item.getVideoCodec();
		String tempVar5 = item.getAudioCodec();
		java.util.ArrayList<String> list = new java.util.ArrayList<String>(java.util.Arrays.asList(new String[] {(tempVar != null) ? tempVar : "", (tempVar2 != null) ? tempVar2 : "", (tempVar3 != null) ? tempVar3 : "", (new Boolean(item.getIsDirectStream())).toString().toLowerCase(), (tempVar4 != null) ? tempVar4 : "", (tempVar5 != null) ? tempVar5 : "", item.getAudioStreamIndex() != null ? StringHelper.ToStringCultureInvariant(item.getAudioStreamIndex()) : "", item.getSubtitleStreamIndex() != null ? StringHelper.ToStringCultureInvariant(item.getSubtitleStreamIndex()) : "", item.getVideoBitrate() != null ? StringHelper.ToStringCultureInvariant(item.getVideoBitrate()) : "", item.getAudioBitrate() != null ? StringHelper.ToStringCultureInvariant(item.getAudioBitrate()) : "", item.getMaxAudioChannels() != null ? StringHelper.ToStringCultureInvariant(item.getMaxAudioChannels()) : "", item.getMaxFramerate() != null ? StringHelper.ToStringCultureInvariant(item.getMaxFramerate()) : "", item.getMaxWidth() != null ? StringHelper.ToStringCultureInvariant(item.getMaxWidth()) : "", item.getMaxHeight() != null ? StringHelper.ToStringCultureInvariant(item.getMaxHeight()) : "", StringHelper.ToStringCultureInvariant(item.getStartPositionTicks()), item.getVideoLevel() != null ? StringHelper.ToStringCultureInvariant(item.getVideoLevel()) : ""}));

		return String.format("Params=%1$s", tangible.DotNetToJavaStringHelper.join(";", list.toArray(new String[0])));
	}

	/** 
	 Returns the audio stream that will be used
	*/
	public final MediaStream getTargetAudioStream()
	{
		if (getMediaSource() != null)
		{
			if (getAudioStreamIndex() != null)
			{
				for (MediaStream i : getMediaSource().getMediaStreams())
				{
					if (i.getIndex() == getAudioStreamIndex() && i.getType() == MediaStreamType.Audio)
					{
						return i;
					}
				}
				return null;
			}

			return getMediaSource().getDefaultAudioStream();
		}

		return null;
	}

	/** 
	 Returns the video stream that will be used
	*/
	public final MediaStream getTargetVideoStream()
	{
		if (getMediaSource() != null)
		{
			return getMediaSource().getVideoStream();
		}

		return null;
	}

	/** 
	 Predicts the audio sample rate that will be in the output stream
	*/
	public final Integer getTargetAudioSampleRate()
	{
		MediaStream stream = getTargetAudioStream();
		return stream == null ? null : stream.getSampleRate();
	}

	/** 
	 Predicts the audio sample rate that will be in the output stream
	*/
	public final Integer getTargetVideoBitDepth()
	{
		MediaStream stream = getTargetVideoStream();
		return stream == null || !getIsDirectStream() ? null : stream.getBitDepth();
	}

	/** 
	 Predicts the audio sample rate that will be in the output stream
	*/
	public final Float getTargetFramerate()
	{
		MediaStream stream = getTargetVideoStream();
		Float tempVar = stream.getAverageFrameRate();
		return getMaxFramerate() != null && !getIsDirectStream() ? getMaxFramerate() : stream == null ? null : (tempVar != null) ? tempVar : stream.getRealFrameRate();
	}

	/** 
	 Predicts the audio sample rate that will be in the output stream
	*/
	public final Double getTargetVideoLevel()
	{
		MediaStream stream = getTargetVideoStream();
		return getVideoLevel() != null && !getIsDirectStream() ? getVideoLevel() : stream == null ? null : stream.getLevel();
	}

	/** 
	 Predicts the audio sample rate that will be in the output stream
	*/
	public final Integer getTargetPacketLength()
	{
		MediaStream stream = getTargetVideoStream();
		return !getIsDirectStream() ? null : stream == null ? null : stream.getPacketLength();
	}

	/** 
	 Predicts the audio sample rate that will be in the output stream
	*/
	public final String getTargetVideoProfile()
	{
		MediaStream stream = getTargetVideoStream();
		return !tangible.DotNetToJavaStringHelper.isNullOrEmpty(getVideoProfile()) && !getIsDirectStream() ? getVideoProfile() : stream == null ? null : stream.getProfile();
	}

	/** 
	 Predicts the audio bitrate that will be in the output stream
	*/
	public final Integer getTargetAudioBitrate()
	{
		MediaStream stream = getTargetAudioStream();
		return getAudioBitrate() != null && !getIsDirectStream() ? getAudioBitrate() : stream == null ? null : stream.getBitRate();
	}

	/** 
	 Predicts the audio channels that will be in the output stream
	*/
	public final Integer getTargetAudioChannels()
	{
		MediaStream stream = getTargetAudioStream();
		Integer streamChannels = stream == null ? null : stream.getChannels();

		return getMaxAudioChannels() != null && !getIsDirectStream() ? (streamChannels != null ? Math.min(getMaxAudioChannels(), streamChannels) : getMaxAudioChannels()) : stream == null ? null : streamChannels;
	}

	/** 
	 Predicts the audio codec that will be in the output stream
	*/
	public final String getTargetAudioCodec()
	{
		MediaStream stream = getTargetAudioStream();

		return getIsDirectStream() ? (stream == null ? null : stream.getCodec()) : getAudioCodec();
	}

	/** 
	 Predicts the audio channels that will be in the output stream
	*/
	public final Long getTargetSize()
	{
		if (getIsDirectStream())
		{
			return getMediaSource().getSize();
		}

		if (getRunTimeTicks() != null)
		{
			Integer totalBitrate = getTargetTotalBitrate();

			double totalSeconds = getRunTimeTicks();
				// Convert to ms
			totalSeconds /= 10000;
				// Convert to seconds
			totalSeconds /= 1000;

			return totalBitrate != null ? java.lang.Math.round(totalBitrate * totalSeconds) : (Long)null;
		}

		return null;
	}

	public final Integer getTargetVideoBitrate()
	{
		MediaStream stream = getTargetVideoStream();

		return getVideoBitrate() != null && !getIsDirectStream() ? getVideoBitrate() : stream == null ? null : stream.getBitRate();
	}

	public final TransportStreamTimestamp getTargetTimestamp()
	{
		TransportStreamTimestamp defaultValue = StringHelper.EqualsIgnoreCase(getContainer(), "m2ts") ? TransportStreamTimestamp.Valid : TransportStreamTimestamp.None;

		TransportStreamTimestamp tempVar = getMediaSource().getTimestamp();
		return !getIsDirectStream() ? defaultValue : getMediaSource() == null ? defaultValue : (tempVar != null) ? tempVar : TransportStreamTimestamp.None;
	}

	public final Integer getTargetTotalBitrate()
	{
		Integer tempVar = getTargetAudioBitrate();
		Integer tempVar2 = getTargetVideoBitrate();
		return ((tempVar != null) ? tempVar : 0) + ((tempVar2 != null) ? tempVar2 : 0);
	}

	public final Boolean getIsTargetAnamorphic()
	{
		if (getIsDirectStream())
		{
			return getTargetVideoStream() == null ? null : getTargetVideoStream().getIsAnamorphic();
		}

		return false;
	}

	public final Integer getTargetWidth()
	{
		MediaStream videoStream = getTargetVideoStream();

		if (videoStream != null && videoStream.getWidth() != null && videoStream.getHeight() != null)
		{
			ImageSize tempVar = new ImageSize();
			tempVar.setWidth(videoStream.getWidth());
			tempVar.setHeight(videoStream.getHeight());
			ImageSize size = tempVar;

			Double maxWidth = getMaxWidth() != null ? (double)getMaxWidth() : (Double)null;
			Double maxHeight = getMaxHeight() != null ? (double)getMaxHeight() : (Double)null;

			ImageSize newSize = DrawingUtils.Resize(size.clone(), null, null, maxWidth, maxHeight);

			return (int)newSize.getWidth();
		}

		return getMaxWidth();
	}

	public final Integer getTargetHeight()
	{
		MediaStream videoStream = getTargetVideoStream();

		if (videoStream != null && videoStream.getWidth() != null && videoStream.getHeight() != null)
		{
			ImageSize tempVar = new ImageSize();
			tempVar.setWidth(videoStream.getWidth());
			tempVar.setHeight(videoStream.getHeight());
			ImageSize size = tempVar;

			Double maxWidth = getMaxWidth() != null ? (double)getMaxWidth() : (Double)null;
			Double maxHeight = getMaxHeight() != null ? (double)getMaxHeight() : (Double)null;

			ImageSize newSize = DrawingUtils.Resize(size.clone(), null, null, maxWidth, maxHeight);

			return (int)newSize.getHeight();
		}

		return getMaxHeight();
	}
}