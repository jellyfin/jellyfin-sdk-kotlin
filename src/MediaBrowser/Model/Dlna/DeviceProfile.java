package MediaBrowser.Model.Dlna;

import MediaBrowser.Model.Extensions.*;
import MediaBrowser.Model.MediaInfo.*;

//C# TO JAVA CONVERTER TODO TASK: Java annotations will not correspond to .NET attributes:
//ORIGINAL LINE: [XmlRoot("Profile")] public class DeviceProfile
public class DeviceProfile
{
	/** 
	 Gets or sets the name.
	 
	 <value>The name.</value>
	*/
	private String privateName;
	public final String getName()
	{
		return privateName;
	}
	public final void setName(String value)
	{
		privateName = value;
	}

//C# TO JAVA CONVERTER TODO TASK: Java annotations will not correspond to .NET attributes:
//ORIGINAL LINE: [XmlIgnore] public string Id {get;set;}
	private String privateId;
	public final String getId()
	{
		return privateId;
	}
	public final void setId(String value)
	{
		privateId = value;
	}

//C# TO JAVA CONVERTER TODO TASK: Java annotations will not correspond to .NET attributes:
//ORIGINAL LINE: [XmlIgnore] public DeviceProfileType ProfileType {get;set;}
	private DeviceProfileType privateProfileType = DeviceProfileType.values()[0];
	public final DeviceProfileType getProfileType()
	{
		return privateProfileType;
	}
	public final void setProfileType(DeviceProfileType value)
	{
		privateProfileType = value;
	}

	/** 
	 Gets or sets the identification.
	 
	 <value>The identification.</value>
	*/
	private DeviceIdentification privateIdentification;
	public final DeviceIdentification getIdentification()
	{
		return privateIdentification;
	}
	public final void setIdentification(DeviceIdentification value)
	{
		privateIdentification = value;
	}

	private String privateFriendlyName;
	public final String getFriendlyName()
	{
		return privateFriendlyName;
	}
	public final void setFriendlyName(String value)
	{
		privateFriendlyName = value;
	}
	private String privateManufacturer;
	public final String getManufacturer()
	{
		return privateManufacturer;
	}
	public final void setManufacturer(String value)
	{
		privateManufacturer = value;
	}
	private String privateManufacturerUrl;
	public final String getManufacturerUrl()
	{
		return privateManufacturerUrl;
	}
	public final void setManufacturerUrl(String value)
	{
		privateManufacturerUrl = value;
	}
	private String privateModelName;
	public final String getModelName()
	{
		return privateModelName;
	}
	public final void setModelName(String value)
	{
		privateModelName = value;
	}
	private String privateModelDescription;
	public final String getModelDescription()
	{
		return privateModelDescription;
	}
	public final void setModelDescription(String value)
	{
		privateModelDescription = value;
	}
	private String privateModelNumber;
	public final String getModelNumber()
	{
		return privateModelNumber;
	}
	public final void setModelNumber(String value)
	{
		privateModelNumber = value;
	}
	private String privateModelUrl;
	public final String getModelUrl()
	{
		return privateModelUrl;
	}
	public final void setModelUrl(String value)
	{
		privateModelUrl = value;
	}
	private String privateSerialNumber;
	public final String getSerialNumber()
	{
		return privateSerialNumber;
	}
	public final void setSerialNumber(String value)
	{
		privateSerialNumber = value;
	}
	private boolean privateIgnoreTranscodeByteRangeRequests;
	public final boolean getIgnoreTranscodeByteRangeRequests()
	{
		return privateIgnoreTranscodeByteRangeRequests;
	}
	public final void setIgnoreTranscodeByteRangeRequests(boolean value)
	{
		privateIgnoreTranscodeByteRangeRequests = value;
	}
	private boolean privateEnableAlbumArtInDidl;
	public final boolean getEnableAlbumArtInDidl()
	{
		return privateEnableAlbumArtInDidl;
	}
	public final void setEnableAlbumArtInDidl(boolean value)
	{
		privateEnableAlbumArtInDidl = value;
	}

	private String privateSupportedMediaTypes;
	public final String getSupportedMediaTypes()
	{
		return privateSupportedMediaTypes;
	}
	public final void setSupportedMediaTypes(String value)
	{
		privateSupportedMediaTypes = value;
	}

	private String privateUserId;
	public final String getUserId()
	{
		return privateUserId;
	}
	public final void setUserId(String value)
	{
		privateUserId = value;
	}

	private String privateAlbumArtPn;
	public final String getAlbumArtPn()
	{
		return privateAlbumArtPn;
	}
	public final void setAlbumArtPn(String value)
	{
		privateAlbumArtPn = value;
	}

	private int privateMaxAlbumArtWidth;
	public final int getMaxAlbumArtWidth()
	{
		return privateMaxAlbumArtWidth;
	}
	public final void setMaxAlbumArtWidth(int value)
	{
		privateMaxAlbumArtWidth = value;
	}
	private int privateMaxAlbumArtHeight;
	public final int getMaxAlbumArtHeight()
	{
		return privateMaxAlbumArtHeight;
	}
	public final void setMaxAlbumArtHeight(int value)
	{
		privateMaxAlbumArtHeight = value;
	}

	private Integer privateMaxIconWidth = null;
	public final Integer getMaxIconWidth()
	{
		return privateMaxIconWidth;
	}
	public final void setMaxIconWidth(Integer value)
	{
		privateMaxIconWidth = value;
	}
	private Integer privateMaxIconHeight = null;
	public final Integer getMaxIconHeight()
	{
		return privateMaxIconHeight;
	}
	public final void setMaxIconHeight(Integer value)
	{
		privateMaxIconHeight = value;
	}

	private Integer privateMaxStreamingBitrate = null;
	public final Integer getMaxStreamingBitrate()
	{
		return privateMaxStreamingBitrate;
	}
	public final void setMaxStreamingBitrate(Integer value)
	{
		privateMaxStreamingBitrate = value;
	}
	private Integer privateMaxStaticBitrate = null;
	public final Integer getMaxStaticBitrate()
	{
		return privateMaxStaticBitrate;
	}
	public final void setMaxStaticBitrate(Integer value)
	{
		privateMaxStaticBitrate = value;
	}

	private Integer privateMusicStreamingTranscodingBitrate = null;
	public final Integer getMusicStreamingTranscodingBitrate()
	{
		return privateMusicStreamingTranscodingBitrate;
	}
	public final void setMusicStreamingTranscodingBitrate(Integer value)
	{
		privateMusicStreamingTranscodingBitrate = value;
	}
	private Integer privateMusicSyncBitrate = null;
	public final Integer getMusicSyncBitrate()
	{
		return privateMusicSyncBitrate;
	}
	public final void setMusicSyncBitrate(Integer value)
	{
		privateMusicSyncBitrate = value;
	}

	/** 
	 Controls the content of the X_DLNADOC element in the urn:schemas-dlna-org:device-1-0 namespace.
	*/
	private String privateXDlnaDoc;
	public final String getXDlnaDoc()
	{
		return privateXDlnaDoc;
	}
	public final void setXDlnaDoc(String value)
	{
		privateXDlnaDoc = value;
	}
	/** 
	 Controls the content of the X_DLNACAP element in the urn:schemas-dlna-org:device-1-0 namespace.
	*/
	private String privateXDlnaCap;
	public final String getXDlnaCap()
	{
		return privateXDlnaCap;
	}
	public final void setXDlnaCap(String value)
	{
		privateXDlnaCap = value;
	}
	/** 
	 Controls the content of the aggregationFlags element in the urn:schemas-sonycom:av namespace.
	*/
	private String privateSonyAggregationFlags;
	public final String getSonyAggregationFlags()
	{
		return privateSonyAggregationFlags;
	}
	public final void setSonyAggregationFlags(String value)
	{
		privateSonyAggregationFlags = value;
	}

	private String privateProtocolInfo;
	public final String getProtocolInfo()
	{
		return privateProtocolInfo;
	}
	public final void setProtocolInfo(String value)
	{
		privateProtocolInfo = value;
	}

	private int privateTimelineOffsetSeconds;
	public final int getTimelineOffsetSeconds()
	{
		return privateTimelineOffsetSeconds;
	}
	public final void setTimelineOffsetSeconds(int value)
	{
		privateTimelineOffsetSeconds = value;
	}
	private boolean privateRequiresPlainVideoItems;
	public final boolean getRequiresPlainVideoItems()
	{
		return privateRequiresPlainVideoItems;
	}
	public final void setRequiresPlainVideoItems(boolean value)
	{
		privateRequiresPlainVideoItems = value;
	}
	private boolean privateRequiresPlainFolders;
	public final boolean getRequiresPlainFolders()
	{
		return privateRequiresPlainFolders;
	}
	public final void setRequiresPlainFolders(boolean value)
	{
		privateRequiresPlainFolders = value;
	}

	private XmlAttribute[] privateXmlRootAttributes;
	public final XmlAttribute[] getXmlRootAttributes()
	{
		return privateXmlRootAttributes;
	}
	public final void setXmlRootAttributes(XmlAttribute[] value)
	{
		privateXmlRootAttributes = value;
	}

	/** 
	 Gets or sets the direct play profiles.
	 
	 <value>The direct play profiles.</value>
	*/
	private DirectPlayProfile[] privateDirectPlayProfiles;
	public final DirectPlayProfile[] getDirectPlayProfiles()
	{
		return privateDirectPlayProfiles;
	}
	public final void setDirectPlayProfiles(DirectPlayProfile[] value)
	{
		privateDirectPlayProfiles = value;
	}

	/** 
	 Gets or sets the transcoding profiles.
	 
	 <value>The transcoding profiles.</value>
	*/
	private TranscodingProfile[] privateTranscodingProfiles;
	public final TranscodingProfile[] getTranscodingProfiles()
	{
		return privateTranscodingProfiles;
	}
	public final void setTranscodingProfiles(TranscodingProfile[] value)
	{
		privateTranscodingProfiles = value;
	}

	private ContainerProfile[] privateContainerProfiles;
	public final ContainerProfile[] getContainerProfiles()
	{
		return privateContainerProfiles;
	}
	public final void setContainerProfiles(ContainerProfile[] value)
	{
		privateContainerProfiles = value;
	}

	private CodecProfile[] privateCodecProfiles;
	public final CodecProfile[] getCodecProfiles()
	{
		return privateCodecProfiles;
	}
	public final void setCodecProfiles(CodecProfile[] value)
	{
		privateCodecProfiles = value;
	}
	private ResponseProfile[] privateResponseProfiles;
	public final ResponseProfile[] getResponseProfiles()
	{
		return privateResponseProfiles;
	}
	public final void setResponseProfiles(ResponseProfile[] value)
	{
		privateResponseProfiles = value;
	}

	private SubtitleProfile[] privateSubtitleProfiles;
	public final SubtitleProfile[] getSubtitleProfiles()
	{
		return privateSubtitleProfiles;
	}
	public final void setSubtitleProfiles(SubtitleProfile[] value)
	{
		privateSubtitleProfiles = value;
	}

	public DeviceProfile()
	{
		setDirectPlayProfiles(new DirectPlayProfile[] { });
		setTranscodingProfiles(new TranscodingProfile[] { });
		setResponseProfiles(new ResponseProfile[] { });
		setCodecProfiles(new CodecProfile[] { });
		setContainerProfiles(new ContainerProfile[] { });
		setSubtitleProfiles(new SubtitleProfile[] { });

		setXmlRootAttributes(new XmlAttribute[] { });

		setSupportedMediaTypes("Audio,Photo,Video");
	}

	public final java.util.ArrayList<String> GetSupportedMediaTypes()
	{
		java.util.ArrayList<String> list = new java.util.ArrayList<String>();
		for (String i : ((getSupportedMediaTypes() != null) ? getSupportedMediaTypes() : "").split("[,]", -1))
		{
			if (!tangible.DotNetToJavaStringHelper.isNullOrEmpty(i))
			{
				list.add(i);
			}
		}
		return list;
	}

	public final TranscodingProfile GetAudioTranscodingProfile(String container, String audioCodec)
	{
		container = StringHelper.TrimStart(((container != null) ? container : ""), '.');

		for (TranscodingProfile i : getTranscodingProfiles())
		{
			if (i.getType() != DlnaProfileType.Audio)
			{
				continue;
			}

			if (!StringHelper.EqualsIgnoreCase(container, i.getContainer()))
			{
				continue;
			}

			if (!ListHelper.ContainsIgnoreCase(i.GetAudioCodecs(), (audioCodec != null) ? audioCodec : ""))
			{
				continue;
			}

			return i;
		}
		return null;
	}

	public final TranscodingProfile GetVideoTranscodingProfile(String container, String audioCodec, String videoCodec)
	{
		container = StringHelper.TrimStart(((container != null) ? container : ""), '.');

		for (TranscodingProfile i : getTranscodingProfiles())
		{
			if (i.getType() != DlnaProfileType.Video)
			{
				continue;
			}

			if (!StringHelper.EqualsIgnoreCase(container, i.getContainer()))
			{
				continue;
			}

			if (!ListHelper.ContainsIgnoreCase(i.GetAudioCodecs(), (audioCodec != null) ? audioCodec : ""))
			{
				continue;
			}

			String tempVar = i.getVideoCodec();
			if (!StringHelper.EqualsIgnoreCase(videoCodec, (tempVar != null) ? tempVar : ""))
			{
				continue;
			}

			return i;
		}
		return null;
	}

	public final ResponseProfile GetAudioMediaProfile(String container, String audioCodec, Integer audioChannels, Integer audioBitrate)
	{
		container = StringHelper.TrimStart(((container != null) ? container : ""), '.');

		for (ResponseProfile i : getResponseProfiles())
		{
			if (i.getType() != DlnaProfileType.Audio)
			{
				continue;
			}

			java.util.ArrayList<String> containers = i.GetContainers();
			if (containers.size() > 0 && !ListHelper.ContainsIgnoreCase(containers, container))
			{
				continue;
			}

			java.util.ArrayList<String> audioCodecs = i.GetAudioCodecs();
			if (audioCodecs.size() > 0 && !ListHelper.ContainsIgnoreCase(audioCodecs, (audioCodec != null) ? audioCodec : ""))
			{
				continue;
			}

			ConditionProcessor conditionProcessor = new ConditionProcessor();

			boolean anyOff = false;
			for (ProfileCondition c : i.getConditions())
			{
				if (!conditionProcessor.IsAudioConditionSatisfied(c, audioChannels, audioBitrate))
				{
					anyOff = true;
					break;
				}
			}

			if (anyOff)
			{
				continue;
			}

			return i;
		}
		return null;
	}

	public final ResponseProfile GetImageMediaProfile(String container, Integer width, Integer height)
	{
		container = StringHelper.TrimStart(((container != null) ? container : ""), '.');

		for (ResponseProfile i : getResponseProfiles())
		{
			if (i.getType() != DlnaProfileType.Photo)
			{
				continue;
			}

			java.util.ArrayList<String> containers = i.GetContainers();
			if (containers.size() > 0 && !ListHelper.ContainsIgnoreCase(containers, container))
			{
				continue;
			}

			ConditionProcessor conditionProcessor = new ConditionProcessor();

			boolean anyOff = false;
			for (ProfileCondition c : i.getConditions())
			{
				if (!conditionProcessor.IsImageConditionSatisfied(c, width, height))
				{
					anyOff = true;
					break;
				}
			}

			if (anyOff)
			{
				continue;
			}

			return i;
		}
		return null;
	}

	public final ResponseProfile GetVideoMediaProfile(String container, String audioCodec, String videoCodec, Integer audioBitrate, Integer audioChannels, Integer width, Integer height, Integer bitDepth, Integer videoBitrate, String videoProfile, Double videoLevel, Float videoFramerate, Integer packetLength, TransportStreamTimestamp timestamp, Boolean isAnamorphic, Integer refFrames)
	{
		container = StringHelper.TrimStart(((container != null) ? container : ""), '.');

		for (ResponseProfile i : getResponseProfiles())
		{
			if (i.getType() != DlnaProfileType.Video)
			{
				continue;
			}

			java.util.ArrayList<String> containers = i.GetContainers();
			if (containers.size() > 0 && !ListHelper.ContainsIgnoreCase(containers, (container != null) ? container : ""))
			{
				continue;
			}

			java.util.ArrayList<String> audioCodecs = i.GetAudioCodecs();
			if (audioCodecs.size() > 0 && !ListHelper.ContainsIgnoreCase(audioCodecs, (audioCodec != null) ? audioCodec : ""))
			{
				continue;
			}

			java.util.ArrayList<String> videoCodecs = i.GetVideoCodecs();
			if (videoCodecs.size() > 0 && !ListHelper.ContainsIgnoreCase(videoCodecs, (videoCodec != null) ? videoCodec : ""))
			{
				continue;
			}

			ConditionProcessor conditionProcessor = new ConditionProcessor();

			boolean anyOff = false;
			for (ProfileCondition c : i.getConditions())
			{
				if (!conditionProcessor.IsVideoConditionSatisfied(c, audioBitrate, audioChannels, width, height, bitDepth, videoBitrate, videoProfile, videoLevel, videoFramerate, packetLength, timestamp, isAnamorphic, refFrames))
				{
					anyOff = true;
					break;
				}
			}

			if (anyOff)
			{
				continue;
			}

			return i;
		}
		return null;
	}
}