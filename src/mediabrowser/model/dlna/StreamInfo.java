package mediabrowser.model.dlna;

import mediabrowser.model.drawing.*;
import mediabrowser.model.dto.*;
import mediabrowser.model.entities.*;
import mediabrowser.model.extensions.*;
import mediabrowser.model.mediainfo.*;
import mediabrowser.model.session.*;

/** 
 Class StreamInfo.
*/
public class StreamInfo
{
	private String ItemId;
	public final String getItemId()
	{
		return ItemId;
	}
	public final void setItemId(String value)
	{
		ItemId = value;
	}

	private PlayMethod PlayMethod = getPlayMethod().values()[0];
	public final PlayMethod getPlayMethod()
	{
		return PlayMethod;
	}
	public final void setPlayMethod(PlayMethod value)
	{
		PlayMethod = value;
	}

	private DlnaProfileType MediaType = DlnaProfileType.values()[0];
	public final DlnaProfileType getMediaType()
	{
		return MediaType;
	}
	public final void setMediaType(DlnaProfileType value)
	{
		MediaType = value;
	}

	private String Container;
	public final String getContainer()
	{
		return Container;
	}
	public final void setContainer(String value)
	{
		Container = value;
	}

	private String Protocol;
	public final String getProtocol()
	{
		return Protocol;
	}
	public final void setProtocol(String value)
	{
		Protocol = value;
	}

	private long StartPositionTicks;
	public final long getStartPositionTicks()
	{
		return StartPositionTicks;
	}
	public final void setStartPositionTicks(long value)
	{
		StartPositionTicks = value;
	}

	private String VideoCodec;
	public final String getVideoCodec()
	{
		return VideoCodec;
	}
	public final void setVideoCodec(String value)
	{
		VideoCodec = value;
	}
	private String VideoProfile;
	public final String getVideoProfile()
	{
		return VideoProfile;
	}
	public final void setVideoProfile(String value)
	{
		VideoProfile = value;
	}

	private Boolean Cabac = null;
	public final Boolean getCabac()
	{
		return Cabac;
	}
	public final void setCabac(Boolean value)
	{
		Cabac = value;
	}
	private String AudioCodec;
	public final String getAudioCodec()
	{
		return AudioCodec;
	}
	public final void setAudioCodec(String value)
	{
		AudioCodec = value;
	}

	private Integer AudioStreamIndex = null;
	public final Integer getAudioStreamIndex()
	{
		return AudioStreamIndex;
	}
	public final void setAudioStreamIndex(Integer value)
	{
		AudioStreamIndex = value;
	}

	private Integer SubtitleStreamIndex = null;
	public final Integer getSubtitleStreamIndex()
	{
		return SubtitleStreamIndex;
	}
	public final void setSubtitleStreamIndex(Integer value)
	{
		SubtitleStreamIndex = value;
	}

	private Integer MaxAudioChannels = null;
	public final Integer getMaxAudioChannels()
	{
		return MaxAudioChannels;
	}
	public final void setMaxAudioChannels(Integer value)
	{
		MaxAudioChannels = value;
	}

	private Integer AudioBitrate = null;
	public final Integer getAudioBitrate()
	{
		return AudioBitrate;
	}
	public final void setAudioBitrate(Integer value)
	{
		AudioBitrate = value;
	}

	private Integer VideoBitrate = null;
	public final Integer getVideoBitrate()
	{
		return VideoBitrate;
	}
	public final void setVideoBitrate(Integer value)
	{
		VideoBitrate = value;
	}

	private Integer VideoLevel = null;
	public final Integer getVideoLevel()
	{
		return VideoLevel;
	}
	public final void setVideoLevel(Integer value)
	{
		VideoLevel = value;
	}

	private Integer MaxWidth = null;
	public final Integer getMaxWidth()
	{
		return MaxWidth;
	}
	public final void setMaxWidth(Integer value)
	{
		MaxWidth = value;
	}
	private Integer MaxHeight = null;
	public final Integer getMaxHeight()
	{
		return MaxHeight;
	}
	public final void setMaxHeight(Integer value)
	{
		MaxHeight = value;
	}

	private Integer MaxVideoBitDepth = null;
	public final Integer getMaxVideoBitDepth()
	{
		return MaxVideoBitDepth;
	}
	public final void setMaxVideoBitDepth(Integer value)
	{
		MaxVideoBitDepth = value;
	}
	private Integer MaxRefFrames = null;
	public final Integer getMaxRefFrames()
	{
		return MaxRefFrames;
	}
	public final void setMaxRefFrames(Integer value)
	{
		MaxRefFrames = value;
	}

	private Float MaxFramerate = null;
	public final Float getMaxFramerate()
	{
		return MaxFramerate;
	}
	public final void setMaxFramerate(Float value)
	{
		MaxFramerate = value;
	}

	private String DeviceProfileId;
	public final String getDeviceProfileId()
	{
		return DeviceProfileId;
	}
	public final void setDeviceProfileId(String value)
	{
		DeviceProfileId = value;
	}
	private String DeviceId;
	public final String getDeviceId()
	{
		return DeviceId;
	}
	public final void setDeviceId(String value)
	{
		DeviceId = value;
	}

	private Long RunTimeTicks = null;
	public final Long getRunTimeTicks()
	{
		return RunTimeTicks;
	}
	public final void setRunTimeTicks(Long value)
	{
		RunTimeTicks = value;
	}

	private TranscodeSeekInfo TranscodeSeekInfo = getTranscodeSeekInfo().values()[0];
	public final TranscodeSeekInfo getTranscodeSeekInfo()
	{
		return TranscodeSeekInfo;
	}
	public final void setTranscodeSeekInfo(TranscodeSeekInfo value)
	{
		TranscodeSeekInfo = value;
	}

	private boolean EstimateContentLength;
	public final boolean getEstimateContentLength()
	{
		return EstimateContentLength;
	}
	public final void setEstimateContentLength(boolean value)
	{
		EstimateContentLength = value;
	}

	private MediaSourceInfo MediaSource;
	public final MediaSourceInfo getMediaSource()
	{
		return MediaSource;
	}
	public final void setMediaSource(MediaSourceInfo value)
	{
		MediaSource = value;
	}

	private SubtitleDeliveryMethod SubtitleDeliveryMethod = getSubtitleDeliveryMethod().values()[0];
	public final SubtitleDeliveryMethod getSubtitleDeliveryMethod()
	{
		return SubtitleDeliveryMethod;
	}
	public final void setSubtitleDeliveryMethod(SubtitleDeliveryMethod value)
	{
		SubtitleDeliveryMethod = value;
	}
	private String SubtitleFormat;
	public final String getSubtitleFormat()
	{
		return SubtitleFormat;
	}
	public final void setSubtitleFormat(String value)
	{
		SubtitleFormat = value;
	}

	public final String getMediaSourceId()
	{
		return getMediaSource() == null ? null : getMediaSource().getId();
	}

	public final boolean getIsDirectStream()
	{
		return getPlayMethod() == PlayMethod.DirectStream || getPlayMethod() == PlayMethod.DirectPlay;
	}

	public final String ToUrl(String baseUrl)
	{
		return ToDlnaUrl(baseUrl);
	}

	public final String ToDlnaUrl(String baseUrl)
	{
		if (getPlayMethod() == PlayMethod.DirectPlay)
		{
			return getMediaSource().getPath();
		}

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
			return String.format("%1$s/videos/%2$s/master.m3u8?%3$s", baseUrl, getItemId(), dlnaCommand);
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
		java.util.ArrayList<String> list = new java.util.ArrayList<String>(java.util.Arrays.asList(new String[] {(tempVar != null) ? tempVar : "", (tempVar2 != null) ? tempVar2 : "", (tempVar3 != null) ? tempVar3 : "", (new Boolean(item.getIsDirectStream())).toString().toLowerCase(), (tempVar4 != null) ? tempVar4 : "", (tempVar5 != null) ? tempVar5 : "", item.getAudioStreamIndex() != null ? StringHelper.ToStringCultureInvariant(item.getAudioStreamIndex()) : "", item.getSubtitleStreamIndex() != null && item.getSubtitleDeliveryMethod() != mediabrowser.model.dlna.SubtitleDeliveryMethod.External ? StringHelper.ToStringCultureInvariant(item.getSubtitleStreamIndex()) : "", item.getVideoBitrate() != null ? StringHelper.ToStringCultureInvariant(item.getVideoBitrate()) : "", item.getAudioBitrate() != null ? StringHelper.ToStringCultureInvariant(item.getAudioBitrate()) : "", item.getMaxAudioChannels() != null ? StringHelper.ToStringCultureInvariant(item.getMaxAudioChannels()) : "", item.getMaxFramerate() != null ? StringHelper.ToStringCultureInvariant(item.getMaxFramerate()) : "", item.getMaxWidth() != null ? StringHelper.ToStringCultureInvariant(item.getMaxWidth()) : "", item.getMaxHeight() != null ? StringHelper.ToStringCultureInvariant(item.getMaxHeight()) : "", StringHelper.ToStringCultureInvariant(item.getStartPositionTicks()), item.getVideoLevel() != null ? StringHelper.ToStringCultureInvariant(item.getVideoLevel()) : ""}));

        list.add(item.getIsDirectStream() ? "" : String.valueOf(new java.util.Date().getTime()));
        list.add(item.getMaxRefFrames() != null ? StringHelper.ToStringCultureInvariant(item.getMaxRefFrames()) : "");
		list.add(item.getMaxVideoBitDepth() != null ? StringHelper.ToStringCultureInvariant(item.getMaxVideoBitDepth()) : "");
		String tempVar6 = item.getVideoProfile();
		list.add((tempVar6 != null) ? tempVar6 : "");
		list.add(item.getCabac() != null ? item.getCabac().toString() : "");

		return String.format("Params=%1$s", tangible.DotNetToJavaStringHelper.join(";", list.toArray(new String[0])));
	}

	public final java.util.ArrayList<SubtitleStreamInfo> GetExternalSubtitles(String baseUrl)
	{
		if (tangible.DotNetToJavaStringHelper.isNullOrEmpty(baseUrl))
		{
			throw new IllegalArgumentException(baseUrl);
		}

		java.util.ArrayList<SubtitleStreamInfo> list = new java.util.ArrayList<SubtitleStreamInfo>();

		if (getSubtitleDeliveryMethod() != SubtitleDeliveryMethod.External)
		{
			return list;
		}

		if (getSubtitleStreamIndex() == null)
		{
			return list;
		}

		// HLS will preserve timestamps so we can just grab the full subtitle stream
		long startPositionTicks = StringHelper.EqualsIgnoreCase(getProtocol(), "hls") ? 0 : getStartPositionTicks();

		String url = String.format("%1$s/Videos/%2$s/%3$s/Subtitles/%4$s/%5$s/Stream.%6$s", baseUrl, getItemId(), getMediaSourceId(), StringHelper.ToStringCultureInvariant(getSubtitleStreamIndex()), StringHelper.ToStringCultureInvariant(startPositionTicks), getSubtitleFormat());

		for (MediaStream stream : getMediaSource().getMediaStreams())
		{
			if (stream.getType() == MediaStreamType.Subtitle && stream.getIndex() == getSubtitleStreamIndex())
			{
				String tempVar = stream.getLanguage();
				SubtitleStreamInfo tempVar2 = new SubtitleStreamInfo();
				tempVar2.setUrl(url);
				tempVar2.setIsForced(stream.getIsForced());
				tempVar2.setLanguage(stream.getLanguage());
				tempVar2.setName((tempVar != null) ? tempVar : "Unknown");
				tempVar2.setFormat(getSubtitleFormat());
				list.add(tempVar2);
			}
		}

		return list;
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
	 Gets the target reference frames.
	 
	 <value>The target reference frames.</value>
	*/
	public final Integer getTargetRefFrames()
	{
		MediaStream stream = getTargetVideoStream();
		return stream == null || !getIsDirectStream() ? null : stream.getRefFrames();
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

		return getMaxAudioChannels() != null && !getIsDirectStream() ? (streamChannels != null ? Math.min(getMaxAudioChannels(), streamChannels) : getMaxAudioChannels()) : streamChannels;
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

	public final Boolean getIsTargetCabac()
	{
		if (getIsDirectStream())
		{
			return getTargetVideoStream() == null ? null : getTargetVideoStream().getIsCabac();
		}

		return true;
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