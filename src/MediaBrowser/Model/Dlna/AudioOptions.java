package MediaBrowser.Model.Dlna;

import MediaBrowser.Model.Dto.*;

/** 
 Class AudioOptions.
*/
public class AudioOptions
{
	public AudioOptions()
	{
		setContext(EncodingContext.Streaming);
	}

	private String privateItemId;
	public final String getItemId()
	{
		return privateItemId;
	}
	public final void setItemId(String value)
	{
		privateItemId = value;
	}
	private java.util.ArrayList<MediaSourceInfo> privateMediaSources;
	public final java.util.ArrayList<MediaSourceInfo> getMediaSources()
	{
		return privateMediaSources;
	}
	public final void setMediaSources(java.util.ArrayList<MediaSourceInfo> value)
	{
		privateMediaSources = value;
	}
	private DeviceProfile privateProfile;
	public final DeviceProfile getProfile()
	{
		return privateProfile;
	}
	public final void setProfile(DeviceProfile value)
	{
		privateProfile = value;
	}

	/** 
	 Optional. Only needed if a specific AudioStreamIndex or SubtitleStreamIndex are requested.
	*/
	private String privateMediaSourceId;
	public final String getMediaSourceId()
	{
		return privateMediaSourceId;
	}
	public final void setMediaSourceId(String value)
	{
		privateMediaSourceId = value;
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

	/** 
	 Allows an override of supported number of audio channels
	 Example: DeviceProfile supports five channel, but user only has stereo speakers
	*/
	private Integer privateMaxAudioChannels = null;
	public final Integer getMaxAudioChannels()
	{
		return privateMaxAudioChannels;
	}
	public final void setMaxAudioChannels(Integer value)
	{
		privateMaxAudioChannels = value;
	}

	/** 
	 The application's configured quality setting
	*/
	private Integer privateMaxBitrate = null;
	public final Integer getMaxBitrate()
	{
		return privateMaxBitrate;
	}
	public final void setMaxBitrate(Integer value)
	{
		privateMaxBitrate = value;
	}

	/** 
	 Gets or sets the context.
	 
	 <value>The context.</value>
	*/
	private EncodingContext privateContext = EncodingContext.values()[0];
	public final EncodingContext getContext()
	{
		return privateContext;
	}
	public final void setContext(EncodingContext value)
	{
		privateContext = value;
	}

	/** 
	 Gets or sets the audio transcoding bitrate.
	 
	 <value>The audio transcoding bitrate.</value>
	*/
	private Integer privateAudioTranscodingBitrate = null;
	public final Integer getAudioTranscodingBitrate()
	{
		return privateAudioTranscodingBitrate;
	}
	public final void setAudioTranscodingBitrate(Integer value)
	{
		privateAudioTranscodingBitrate = value;
	}

	/** 
	 Gets the maximum bitrate.
	 
	 @return System.Nullable&lt;System.Int32&gt;.
	*/
	public final Integer GetMaxBitrate()
	{
		if (getMaxBitrate() != null)
		{
			return getMaxBitrate();
		}

		if (getProfile() != null)
		{
			if (getContext() == EncodingContext.Static)
			{
				return getProfile().getMaxStaticBitrate();
			}

			return getProfile().getMaxStreamingBitrate();
		}

		return null;
	}
}