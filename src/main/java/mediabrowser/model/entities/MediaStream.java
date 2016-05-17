package mediabrowser.model.entities;

import mediabrowser.model.dlna.*;
import mediabrowser.model.extensions.*;

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
	private String Codec;
	public final String getCodec()
	{
		return Codec;
	}
	public final void setCodec(String value)
	{
		Codec = value;
	}

	/** 
	 Gets or sets the codec tag.
	 
	 <value>The codec tag.</value>
	*/
	private String CodecTag;
	public final String getCodecTag()
	{
		return CodecTag;
	}
	public final void setCodecTag(String value)
	{
		CodecTag = value;
	}

	/** 
	 Gets or sets the language.
	 
	 <value>The language.</value>
	*/
	private String Language;
	public final String getLanguage()
	{
		return Language;
	}
	public final void setLanguage(String value)
	{
		Language = value;
	}

	/** 
	 Gets or sets the comment.
	 
	 <value>The comment.</value>
	*/
	private String Comment;
	public final String getComment()
	{
		return Comment;
	}
	public final void setComment(String value)
	{
		Comment = value;
	}

	private String Title;
	public final String getTitle()
	{
		return Title;
	}
	public final void setTitle(String value)
	{
		Title = value;
	}

	public final String getDisplayTitle()
	{
		if (!tangible.DotNetToJavaStringHelper.isNullOrEmpty(getTitle()))
		{
			return getTitle();
		}

		if (getType() == MediaStreamType.Audio)
		{
			java.util.ArrayList<String> attributes = new java.util.ArrayList<String>();

			if (!tangible.DotNetToJavaStringHelper.isNullOrEmpty(getLanguage()))
			{
				attributes.add(getLanguage());
			}
			if (!tangible.DotNetToJavaStringHelper.isNullOrEmpty(getCodec()) && !StringHelper.EqualsIgnoreCase(getCodec(), "dca"))
			{
				attributes.add(getCodec());
			}
			if (!tangible.DotNetToJavaStringHelper.isNullOrEmpty(getProfile()) && !StringHelper.EqualsIgnoreCase(getProfile(), "lc"))
			{
				attributes.add(getProfile());
			}

			if (getChannels() != null)
			{
				attributes.add(StringHelper.ToStringCultureInvariant(getChannels()) + " ch");
			}

			String name = tangible.DotNetToJavaStringHelper.join(" ", attributes.toArray(new String[0]));

			if (getIsDefault())
			{
				name += " (D)";
			}

			return name;
		}

		if (getType() == MediaStreamType.Subtitle)
		{
			java.util.ArrayList<String> attributes = new java.util.ArrayList<String>();

			if (!tangible.DotNetToJavaStringHelper.isNullOrEmpty(getLanguage()))
			{
				attributes.add(getLanguage());
			}
			if (!tangible.DotNetToJavaStringHelper.isNullOrEmpty(getCodec()))
			{
				attributes.add(getCodec());
			}

			String name = tangible.DotNetToJavaStringHelper.join(" ", attributes.toArray(new String[0]));

			if (getIsDefault())
			{
				name += " (D)";
			}

			if (getIsForced())
			{
				name += " (F)";
			}

			if (getIsExternal())
			{
				name += " (EXT)";
			}

			return name;
		}

		if (getType() == MediaStreamType.Video)
		{

		}

		return null;
	}

	private String NalLengthSize;
	public final String getNalLengthSize()
	{
		return NalLengthSize;
	}
	public final void setNalLengthSize(String value)
	{
		NalLengthSize = value;
	}

	/** 
	 Gets or sets a value indicating whether this instance is interlaced.
	 
	 <value><c>true</c> if this instance is interlaced; otherwise, <c>false</c>.</value>
	*/
	private boolean IsInterlaced;
	public final boolean getIsInterlaced()
	{
		return IsInterlaced;
	}
	public final void setIsInterlaced(boolean value)
	{
		IsInterlaced = value;
	}

	private Boolean IsAVC = null;
	public final Boolean getIsAVC()
	{
		return IsAVC;
	}
	public final void setIsAVC(Boolean value)
	{
		IsAVC = value;
	}

	/** 
	 Gets or sets the channel layout.
	 
	 <value>The channel layout.</value>
	*/
	private String ChannelLayout;
	public final String getChannelLayout()
	{
		return ChannelLayout;
	}
	public final void setChannelLayout(String value)
	{
		ChannelLayout = value;
	}

	/** 
	 Gets or sets the bit rate.
	 
	 <value>The bit rate.</value>
	*/
	private Integer BitRate = null;
	public final Integer getBitRate()
	{
		return BitRate;
	}
	public final void setBitRate(Integer value)
	{
		BitRate = value;
	}

	/** 
	 Gets or sets the bit depth.
	 
	 <value>The bit depth.</value>
	*/
	private Integer BitDepth = null;
	public final Integer getBitDepth()
	{
		return BitDepth;
	}
	public final void setBitDepth(Integer value)
	{
		BitDepth = value;
	}

	/** 
	 Gets or sets the reference frames.
	 
	 <value>The reference frames.</value>
	*/
	private Integer RefFrames = null;
	public final Integer getRefFrames()
	{
		return RefFrames;
	}
	public final void setRefFrames(Integer value)
	{
		RefFrames = value;
	}

	/** 
	 Gets or sets the length of the packet.
	 
	 <value>The length of the packet.</value>
	*/
	private Integer PacketLength = null;
	public final Integer getPacketLength()
	{
		return PacketLength;
	}
	public final void setPacketLength(Integer value)
	{
		PacketLength = value;
	}

	/** 
	 Gets or sets the channels.
	 
	 <value>The channels.</value>
	*/
	private Integer Channels = null;
	public final Integer getChannels()
	{
		return Channels;
	}
	public final void setChannels(Integer value)
	{
		Channels = value;
	}

	/** 
	 Gets or sets the sample rate.
	 
	 <value>The sample rate.</value>
	*/
	private Integer SampleRate = null;
	public final Integer getSampleRate()
	{
		return SampleRate;
	}
	public final void setSampleRate(Integer value)
	{
		SampleRate = value;
	}

	/** 
	 Gets or sets a value indicating whether this instance is default.
	 
	 <value><c>true</c> if this instance is default; otherwise, <c>false</c>.</value>
	*/
	private boolean IsDefault;
	public final boolean getIsDefault()
	{
		return IsDefault;
	}
	public final void setIsDefault(boolean value)
	{
		IsDefault = value;
	}

	/** 
	 Gets or sets a value indicating whether this instance is forced.
	 
	 <value><c>true</c> if this instance is forced; otherwise, <c>false</c>.</value>
	*/
	private boolean IsForced;
	public final boolean getIsForced()
	{
		return IsForced;
	}
	public final void setIsForced(boolean value)
	{
		IsForced = value;
	}

	/** 
	 Gets or sets the height.
	 
	 <value>The height.</value>
	*/
	private Integer Height = null;
	public final Integer getHeight()
	{
		return Height;
	}
	public final void setHeight(Integer value)
	{
		Height = value;
	}

	/** 
	 Gets or sets the width.
	 
	 <value>The width.</value>
	*/
	private Integer Width = null;
	public final Integer getWidth()
	{
		return Width;
	}
	public final void setWidth(Integer value)
	{
		Width = value;
	}

	/** 
	 Gets or sets the average frame rate.
	 
	 <value>The average frame rate.</value>
	*/
	private Float AverageFrameRate = null;
	public final Float getAverageFrameRate()
	{
		return AverageFrameRate;
	}
	public final void setAverageFrameRate(Float value)
	{
		AverageFrameRate = value;
	}

	/** 
	 Gets or sets the real frame rate.
	 
	 <value>The real frame rate.</value>
	*/
	private Float RealFrameRate = null;
	public final Float getRealFrameRate()
	{
		return RealFrameRate;
	}
	public final void setRealFrameRate(Float value)
	{
		RealFrameRate = value;
	}

	/** 
	 Gets or sets the profile.
	 
	 <value>The profile.</value>
	*/
	private String Profile;
	public final String getProfile()
	{
		return Profile;
	}
	public final void setProfile(String value)
	{
		Profile = value;
	}

	/** 
	 Gets or sets the type.
	 
	 <value>The type.</value>
	*/
	private MediaStreamType Type = MediaStreamType.values()[0];
	public final MediaStreamType getType()
	{
		return Type;
	}
	public final void setType(MediaStreamType value)
	{
		Type = value;
	}

	/** 
	 Gets or sets the aspect ratio.
	 
	 <value>The aspect ratio.</value>
	*/
	private String AspectRatio;
	public final String getAspectRatio()
	{
		return AspectRatio;
	}
	public final void setAspectRatio(String value)
	{
		AspectRatio = value;
	}

	/** 
	 Gets or sets the index.
	 
	 <value>The index.</value>
	*/
	private int Index;
	public final int getIndex()
	{
		return Index;
	}
	public final void setIndex(int value)
	{
		Index = value;
	}

	/** 
	 Gets or sets the score.
	 
	 <value>The score.</value>
	*/
	private Integer Score = null;
	public final Integer getScore()
	{
		return Score;
	}
	public final void setScore(Integer value)
	{
		Score = value;
	}

	/** 
	 Gets or sets a value indicating whether this instance is external.
	 
	 <value><c>true</c> if this instance is external; otherwise, <c>false</c>.</value>
	*/
	private boolean IsExternal;
	public final boolean getIsExternal()
	{
		return IsExternal;
	}
	public final void setIsExternal(boolean value)
	{
		IsExternal = value;
	}

	/** 
	 Gets or sets the method.
	 
	 <value>The method.</value>
	*/
	private SubtitleDeliveryMethod DeliveryMethod = null;
	public final SubtitleDeliveryMethod getDeliveryMethod()
	{
		return DeliveryMethod;
	}
	public final void setDeliveryMethod(SubtitleDeliveryMethod value)
	{
		DeliveryMethod = value;
	}
	/** 
	 Gets or sets the delivery URL.
	 
	 <value>The delivery URL.</value>
	*/
	private String DeliveryUrl;
	public final String getDeliveryUrl()
	{
		return DeliveryUrl;
	}
	public final void setDeliveryUrl(String value)
	{
		DeliveryUrl = value;
	}
	/** 
	 Gets or sets a value indicating whether this instance is external URL.
	 
	 <value><c>null</c> if [is external URL] contains no value, <c>true</c> if [is external URL]; otherwise, <c>false</c>.</value>
	*/
	private Boolean IsExternalUrl = null;
	public final Boolean getIsExternalUrl()
	{
		return IsExternalUrl;
	}
	public final void setIsExternalUrl(Boolean value)
	{
		IsExternalUrl = value;
	}

	public final boolean getIsTextSubtitleStream()
	{
		if (getType() != MediaStreamType.Subtitle)
		{
			return false;
		}

		if (tangible.DotNetToJavaStringHelper.isNullOrEmpty(getCodec()) && !getIsExternal())
		{
			return false;
		}

		return IsTextFormat(getCodec());
	}

	public static boolean IsTextFormat(String format)
	{
		String codec = (format != null) ? format : "";

		// sub = external .sub file

		return StringHelper.IndexOfIgnoreCase(codec, "pgs") == -1 && StringHelper.IndexOfIgnoreCase(codec, "dvd") == -1 && StringHelper.IndexOfIgnoreCase(codec, "dvbsub") == -1 && !StringHelper.EqualsIgnoreCase(codec, "sub");
	}

	/** 
	 Gets or sets a value indicating whether [supports external stream].
	 
	 <value><c>true</c> if [supports external stream]; otherwise, <c>false</c>.</value>
	*/
	private boolean SupportsExternalStream;
	public final boolean getSupportsExternalStream()
	{
		return SupportsExternalStream;
	}
	public final void setSupportsExternalStream(boolean value)
	{
		SupportsExternalStream = value;
	}

	/** 
	 Gets or sets the filename.
	 
	 <value>The filename.</value>
	*/
	private String Path;
	public final String getPath()
	{
		return Path;
	}
	public final void setPath(String value)
	{
		Path = value;
	}

	/** 
	 Gets or sets the external identifier.
	 
	 <value>The external identifier.</value>
	*/
	private String ExternalId;
	public final String getExternalId()
	{
		return ExternalId;
	}
	public final void setExternalId(String value)
	{
		ExternalId = value;
	}

	/** 
	 Gets or sets the pixel format.
	 
	 <value>The pixel format.</value>
	*/
	private String PixelFormat;
	public final String getPixelFormat()
	{
		return PixelFormat;
	}
	public final void setPixelFormat(String value)
	{
		PixelFormat = value;
	}

	/** 
	 Gets or sets the level.
	 
	 <value>The level.</value>
	*/
	private Double Level = null;
	public final Double getLevel()
	{
		return Level;
	}
	public final void setLevel(Double value)
	{
		Level = value;
	}

	/** 
	 Gets a value indicating whether this instance is anamorphic.
	 
	 <value><c>true</c> if this instance is anamorphic; otherwise, <c>false</c>.</value>
	*/
	private Boolean IsAnamorphic = null;
	public final Boolean getIsAnamorphic()
	{
		return IsAnamorphic;
	}
	public final void setIsAnamorphic(Boolean value)
	{
		IsAnamorphic = value;
	}
}