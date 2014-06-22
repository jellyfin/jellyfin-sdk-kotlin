package MediaBrowser.Model.Entities;

import MediaBrowser.Model.Extensions.*;

/** 
 Class MediaStream
*/
//C# TO JAVA CONVERTER TODO TASK: Java annotations will not correspond to .NET attributes:
//ORIGINAL LINE: [DebuggerDisplay("StreamType = {Type}")] public class MediaStream
public class MediaStream
{
	/** 
	 Gets or sets the codec.
	 
	 <value>The codec.</value>
	*/
	private String privateCodec;
	public final String getCodec()
	{
		return privateCodec;
	}
	public final void setCodec(String value)
	{
		privateCodec = value;
	}

	/** 
	 Gets or sets the language.
	 
	 <value>The language.</value>
	*/
	private String privateLanguage;
	public final String getLanguage()
	{
		return privateLanguage;
	}
	public final void setLanguage(String value)
	{
		privateLanguage = value;
	}

	/** 
	 Gets or sets a value indicating whether this instance is interlaced.
	 
	 <value><c>true</c> if this instance is interlaced; otherwise, <c>false</c>.</value>
	*/
	private boolean privateIsInterlaced;
	public final boolean getIsInterlaced()
	{
		return privateIsInterlaced;
	}
	public final void setIsInterlaced(boolean value)
	{
		privateIsInterlaced = value;
	}

	/** 
	 Gets or sets the channel layout.
	 
	 <value>The channel layout.</value>
	*/
	private String privateChannelLayout;
	public final String getChannelLayout()
	{
		return privateChannelLayout;
	}
	public final void setChannelLayout(String value)
	{
		privateChannelLayout = value;
	}

	/** 
	 Gets or sets the bit rate.
	 
	 <value>The bit rate.</value>
	*/
	private Integer privateBitRate = new Integer();
	public final Integer getBitRate()
	{
		return privateBitRate;
	}
	public final void setBitRate(Integer value)
	{
		privateBitRate = value;
	}

	/** 
	 Gets or sets the bit depth.
	 
	 <value>The bit depth.</value>
	*/
	private Integer privateBitDepth = new Integer();
	public final Integer getBitDepth()
	{
		return privateBitDepth;
	}
	public final void setBitDepth(Integer value)
	{
		privateBitDepth = value;
	}

	/** 
	 Gets or sets the length of the packet.
	 
	 <value>The length of the packet.</value>
	*/
	private Integer privatePacketLength = new Integer();
	public final Integer getPacketLength()
	{
		return privatePacketLength;
	}
	public final void setPacketLength(Integer value)
	{
		privatePacketLength = value;
	}

	/** 
	 Gets or sets the channels.
	 
	 <value>The channels.</value>
	*/
	private Integer privateChannels = new Integer();
	public final Integer getChannels()
	{
		return privateChannels;
	}
	public final void setChannels(Integer value)
	{
		privateChannels = value;
	}

	/** 
	 Gets or sets the sample rate.
	 
	 <value>The sample rate.</value>
	*/
	private Integer privateSampleRate = new Integer();
	public final Integer getSampleRate()
	{
		return privateSampleRate;
	}
	public final void setSampleRate(Integer value)
	{
		privateSampleRate = value;
	}

	/** 
	 Gets or sets a value indicating whether this instance is default.
	 
	 <value><c>true</c> if this instance is default; otherwise, <c>false</c>.</value>
	*/
	private boolean privateIsDefault;
	public final boolean getIsDefault()
	{
		return privateIsDefault;
	}
	public final void setIsDefault(boolean value)
	{
		privateIsDefault = value;
	}

	/** 
	 Gets or sets a value indicating whether this instance is forced.
	 
	 <value><c>true</c> if this instance is forced; otherwise, <c>false</c>.</value>
	*/
	private boolean privateIsForced;
	public final boolean getIsForced()
	{
		return privateIsForced;
	}
	public final void setIsForced(boolean value)
	{
		privateIsForced = value;
	}

	/** 
	 Gets or sets the height.
	 
	 <value>The height.</value>
	*/
	private Integer privateHeight = new Integer();
	public final Integer getHeight()
	{
		return privateHeight;
	}
	public final void setHeight(Integer value)
	{
		privateHeight = value;
	}

	/** 
	 Gets or sets the width.
	 
	 <value>The width.</value>
	*/
	private Integer privateWidth = new Integer();
	public final Integer getWidth()
	{
		return privateWidth;
	}
	public final void setWidth(Integer value)
	{
		privateWidth = value;
	}

	/** 
	 Gets or sets the average frame rate.
	 
	 <value>The average frame rate.</value>
	*/
	private Float privateAverageFrameRate = new Float();
	public final Float getAverageFrameRate()
	{
		return privateAverageFrameRate;
	}
	public final void setAverageFrameRate(Float value)
	{
		privateAverageFrameRate = value;
	}

	/** 
	 Gets or sets the real frame rate.
	 
	 <value>The real frame rate.</value>
	*/
	private Float privateRealFrameRate = new Float();
	public final Float getRealFrameRate()
	{
		return privateRealFrameRate;
	}
	public final void setRealFrameRate(Float value)
	{
		privateRealFrameRate = value;
	}

	/** 
	 Gets or sets the profile.
	 
	 <value>The profile.</value>
	*/
	private String privateProfile;
	public final String getProfile()
	{
		return privateProfile;
	}
	public final void setProfile(String value)
	{
		privateProfile = value;
	}

	/** 
	 Gets or sets the type.
	 
	 <value>The type.</value>
	*/
	private MediaStreamType privateType = MediaStreamType.values()[0];
	public final MediaStreamType getType()
	{
		return privateType;
	}
	public final void setType(MediaStreamType value)
	{
		privateType = value;
	}

	/** 
	 Gets or sets the aspect ratio.
	 
	 <value>The aspect ratio.</value>
	*/
	private String privateAspectRatio;
	public final String getAspectRatio()
	{
		return privateAspectRatio;
	}
	public final void setAspectRatio(String value)
	{
		privateAspectRatio = value;
	}

	/** 
	 Gets or sets the index.
	 
	 <value>The index.</value>
	*/
	private int privateIndex;
	public final int getIndex()
	{
		return privateIndex;
	}
	public final void setIndex(int value)
	{
		privateIndex = value;
	}

	/** 
	 Gets or sets a value indicating whether this instance is external.
	 
	 <value><c>true</c> if this instance is external; otherwise, <c>false</c>.</value>
	*/
	private boolean privateIsExternal;
	public final boolean getIsExternal()
	{
		return privateIsExternal;
	}
	public final void setIsExternal(boolean value)
	{
		privateIsExternal = value;
	}

	public final boolean getIsTextSubtitleStream()
	{
		if (getType() != MediaStreamType.Subtitle)
		{
			return false;
		}

		String tempVar = getCodec();
//C# TO JAVA CONVERTER TODO TASK: There is no equivalent to implicit typing in Java:
		var codec = (tempVar != null) ? tempVar : "";

		return StringHelper.IndexOfIgnoreCase(codec, "pgs") == -1 && StringHelper.IndexOfIgnoreCase(codec, "dvd") == -1;
	}


	/** 
	 Gets or sets the filename.
	 
	 <value>The filename.</value>
	*/
	private String privatePath;
	public final String getPath()
	{
		return privatePath;
	}
	public final void setPath(String value)
	{
		privatePath = value;
	}

	/** 
	 Gets or sets the pixel format.
	 
	 <value>The pixel format.</value>
	*/
	private String privatePixelFormat;
	public final String getPixelFormat()
	{
		return privatePixelFormat;
	}
	public final void setPixelFormat(String value)
	{
		privatePixelFormat = value;
	}

	/** 
	 Gets or sets the level.
	 
	 <value>The level.</value>
	*/
	private Double privateLevel = new Double();
	public final Double getLevel()
	{
		return privateLevel;
	}
	public final void setLevel(Double value)
	{
		privateLevel = value;
	}

	/** 
	 Gets a value indicating whether this instance is anamorphic.
	 
	 <value><c>true</c> if this instance is anamorphic; otherwise, <c>false</c>.</value>
	*/
	private Boolean privateIsAnamorphic = new Boolean();
	public final Boolean getIsAnamorphic()
	{
		return privateIsAnamorphic;
	}
	public final void setIsAnamorphic(Boolean value)
	{
		privateIsAnamorphic = value;
	}
}