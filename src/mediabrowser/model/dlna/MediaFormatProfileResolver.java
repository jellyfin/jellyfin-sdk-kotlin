package mediabrowser.model.dlna;

import mediabrowser.model.extensions.*;
import mediabrowser.model.mediainfo.*;

public class MediaFormatProfileResolver
{
	public final java.util.ArrayList<MediaFormatProfile> ResolveVideoFormat(String container, String videoCodec, String audioCodec, Integer width, Integer height, TransportStreamTimestamp timestampType)
	{
		if (StringHelper.EqualsIgnoreCase(container, "asf"))
		{
			MediaFormatProfile val = ResolveVideoASFFormat(videoCodec, audioCodec, width, height);
			return val != null ? new java.util.ArrayList<MediaFormatProfile>(java.util.Arrays.asList(new MediaFormatProfile[] {val})) : new java.util.ArrayList<MediaFormatProfile>();
		}

		if (StringHelper.EqualsIgnoreCase(container, "mp4"))
		{
			MediaFormatProfile val = ResolveVideoMP4Format(videoCodec, audioCodec, width, height);
			return val != null ? new java.util.ArrayList<MediaFormatProfile>(java.util.Arrays.asList(new MediaFormatProfile[] {val})) : new java.util.ArrayList<MediaFormatProfile>();
		}

		if (StringHelper.EqualsIgnoreCase(container, "avi"))
		{
			return new java.util.ArrayList<MediaFormatProfile>(java.util.Arrays.asList(new MediaFormatProfile[] {MediaFormatProfile.AVI}));
		}

		if (StringHelper.EqualsIgnoreCase(container, "mkv"))
		{
			return new java.util.ArrayList<MediaFormatProfile>(java.util.Arrays.asList(new MediaFormatProfile[] {MediaFormatProfile.MATROSKA}));
		}

		if (StringHelper.EqualsIgnoreCase(container, "mpeg2ps") || StringHelper.EqualsIgnoreCase(container, "ts"))

		{
			return new java.util.ArrayList<MediaFormatProfile>(java.util.Arrays.asList(new MediaFormatProfile[] {MediaFormatProfile.MPEG_PS_NTSC, MediaFormatProfile.MPEG_PS_PAL}));
		}

		if (StringHelper.EqualsIgnoreCase(container, "mpeg1video"))
		{
			return new java.util.ArrayList<MediaFormatProfile>(java.util.Arrays.asList(new MediaFormatProfile[] {MediaFormatProfile.MPEG1}));
		}

		if (StringHelper.EqualsIgnoreCase(container, "mpeg2ts") || StringHelper.EqualsIgnoreCase(container, "mpegts") || StringHelper.EqualsIgnoreCase(container, "m2ts"))
		{

			return ResolveVideoMPEG2TSFormat(videoCodec, audioCodec, width, height, timestampType);
		}

		if (StringHelper.EqualsIgnoreCase(container, "flv"))
		{
			return new java.util.ArrayList<MediaFormatProfile>(java.util.Arrays.asList(new MediaFormatProfile[] {MediaFormatProfile.FLV}));
		}

		if (StringHelper.EqualsIgnoreCase(container, "wtv"))
		{
			return new java.util.ArrayList<MediaFormatProfile>(java.util.Arrays.asList(new MediaFormatProfile[] {MediaFormatProfile.WTV}));
		}

		if (StringHelper.EqualsIgnoreCase(container, "3gp"))
		{
			MediaFormatProfile val = ResolveVideo3GPFormat(videoCodec, audioCodec);
			return val != null ? new java.util.ArrayList<MediaFormatProfile>(java.util.Arrays.asList(new MediaFormatProfile[] {val})) : new java.util.ArrayList<MediaFormatProfile>();
		}

		if (StringHelper.EqualsIgnoreCase(container, "ogv") || StringHelper.EqualsIgnoreCase(container, "ogg"))
		{
			return new java.util.ArrayList<MediaFormatProfile>(java.util.Arrays.asList(new MediaFormatProfile[] {MediaFormatProfile.OGV}));
		}

		return new java.util.ArrayList<MediaFormatProfile>();
	}

	private java.util.ArrayList<MediaFormatProfile> ResolveVideoMPEG2TSFormat(String videoCodec, String audioCodec, Integer width, Integer height, TransportStreamTimestamp timestampType)
	{
		String suffix = "";

		switch (timestampType)
		{
			case None:
				suffix = "_ISO";
				break;
			case Valid:
				suffix = "_T";
				break;
		}

		String resolution = "S";
		if ((width != null && width > 720) || (height != null && height > 576))
		{
			resolution = "H";
		}

		if (StringHelper.EqualsIgnoreCase(videoCodec, "mpeg2video"))
		{
			java.util.ArrayList<MediaFormatProfile> list = new java.util.ArrayList<MediaFormatProfile>();

			list.add(ValueOf("MPEG_TS_SD_NA" + suffix));
			list.add(ValueOf("MPEG_TS_SD_EU" + suffix));
			list.add(ValueOf("MPEG_TS_SD_KO" + suffix));

			if ((timestampType == TransportStreamTimestamp.Valid) && StringHelper.EqualsIgnoreCase(audioCodec, "aac"))
			{
				list.add(MediaFormatProfile.MPEG_TS_JP_T);
			}
			return list;
		}
		if (StringHelper.EqualsIgnoreCase(videoCodec, "h264"))
		{
			if (StringHelper.EqualsIgnoreCase(audioCodec, "lpcm"))
			{
				return new java.util.ArrayList<MediaFormatProfile>(java.util.Arrays.asList(new MediaFormatProfile[] {MediaFormatProfile.AVC_TS_HD_50_LPCM_T}));
			}

			if (StringHelper.EqualsIgnoreCase(audioCodec, "dts"))
			{
				if (timestampType == TransportStreamTimestamp.None)
				{
					return new java.util.ArrayList<MediaFormatProfile>(java.util.Arrays.asList(new MediaFormatProfile[] {MediaFormatProfile.AVC_TS_HD_DTS_ISO}));
				}
				return new java.util.ArrayList<MediaFormatProfile>(java.util.Arrays.asList(new MediaFormatProfile[] {MediaFormatProfile.AVC_TS_HD_DTS_T}));
			}

			if (StringHelper.EqualsIgnoreCase(audioCodec, "mp3"))
			{
				if (timestampType == TransportStreamTimestamp.None)
				{
					return new java.util.ArrayList<MediaFormatProfile>(java.util.Arrays.asList(new MediaFormatProfile[] {ValueOf(String.format("AVC_TS_HP_%1$sD_MPEG1_L2_ISO", resolution))}));
				}

				return new java.util.ArrayList<MediaFormatProfile>(java.util.Arrays.asList(new MediaFormatProfile[] {ValueOf(String.format("AVC_TS_HP_%1$sD_MPEG1_L2_T", resolution))}));
			}

			if (StringHelper.EqualsIgnoreCase(audioCodec, "aac"))
			{
				return new java.util.ArrayList<MediaFormatProfile>(java.util.Arrays.asList(new MediaFormatProfile[] {ValueOf(String.format("AVC_TS_MP_%1$sD_AAC_MULT5%2$s", resolution, suffix))}));
			}

			if (StringHelper.EqualsIgnoreCase(audioCodec, "mp3"))
			{
				return new java.util.ArrayList<MediaFormatProfile>(java.util.Arrays.asList(new MediaFormatProfile[] {ValueOf(String.format("AVC_TS_MP_%1$sD_MPEG1_L3%2$s", resolution, suffix))}));
			}

			if (tangible.DotNetToJavaStringHelper.isNullOrEmpty(audioCodec) || StringHelper.EqualsIgnoreCase(audioCodec, "ac3"))
			{
				return new java.util.ArrayList<MediaFormatProfile>(java.util.Arrays.asList(new MediaFormatProfile[] {ValueOf(String.format("AVC_TS_MP_%1$sD_AC3%2$s", resolution, suffix))}));
			}
		}
		else if (StringHelper.EqualsIgnoreCase(videoCodec, "vc1"))
		{
			if (tangible.DotNetToJavaStringHelper.isNullOrEmpty(audioCodec) || StringHelper.EqualsIgnoreCase(audioCodec, "ac3"))
			{
				if ((width != null && width > 720) || (height != null && height > 576))
				{
					return new java.util.ArrayList<MediaFormatProfile>(java.util.Arrays.asList(new MediaFormatProfile[] {MediaFormatProfile.VC1_TS_AP_L2_AC3_ISO}));
				}
				return new java.util.ArrayList<MediaFormatProfile>(java.util.Arrays.asList(new MediaFormatProfile[] {MediaFormatProfile.VC1_TS_AP_L1_AC3_ISO}));
			}
			if (StringHelper.EqualsIgnoreCase(audioCodec, "dts"))
			{
				suffix = StringHelper.EqualsIgnoreCase(suffix, "_ISO") ? suffix : "_T";

				return new java.util.ArrayList<MediaFormatProfile>(java.util.Arrays.asList(new MediaFormatProfile[] {ValueOf(String.format("VC1_TS_HD_DTS%1$s", suffix))}));
			}

		}
		else if (StringHelper.EqualsIgnoreCase(videoCodec, "mpeg4") || StringHelper.EqualsIgnoreCase(videoCodec, "msmpeg4"))
		{
			if (StringHelper.EqualsIgnoreCase(audioCodec, "aac"))
			{
				return new java.util.ArrayList<MediaFormatProfile>(java.util.Arrays.asList(new MediaFormatProfile[] {ValueOf(String.format("MPEG4_P2_TS_ASP_AAC%1$s", suffix))}));
			}
			if (StringHelper.EqualsIgnoreCase(audioCodec, "mp3"))
			{
				return new java.util.ArrayList<MediaFormatProfile>(java.util.Arrays.asList(new MediaFormatProfile[] {ValueOf(String.format("MPEG4_P2_TS_ASP_MPEG1_L3%1$s", suffix))}));
			}
			if (StringHelper.EqualsIgnoreCase(audioCodec, "mp2"))
			{
				return new java.util.ArrayList<MediaFormatProfile>(java.util.Arrays.asList(new MediaFormatProfile[] {ValueOf(String.format("MPEG4_P2_TS_ASP_MPEG2_L2%1$s", suffix))}));
			}
			if (StringHelper.EqualsIgnoreCase(audioCodec, "ac3"))
			{
				return new java.util.ArrayList<MediaFormatProfile>(java.util.Arrays.asList(new MediaFormatProfile[] {ValueOf(String.format("MPEG4_P2_TS_ASP_AC3%1$s", suffix))}));
			}
		}

		return new java.util.ArrayList<MediaFormatProfile>();
	}

	private MediaFormatProfile ValueOf(String value)
	{
//C# TO JAVA CONVERTER WARNING: Java does not have an 'ignoreCase' parameter for the static 'valueOf' method of enum types:
//ORIGINAL LINE: return (MediaFormatProfile)Enum.Parse(typeof(MediaFormatProfile), value, true);
		return MediaFormatProfile.valueOf(value);
	}

	private MediaFormatProfile ResolveVideoMP4Format(String videoCodec, String audioCodec, Integer width, Integer height)
	{
		if (StringHelper.EqualsIgnoreCase(videoCodec, "h264"))
		{
			if (StringHelper.EqualsIgnoreCase(audioCodec, "lpcm"))
			{
				return MediaFormatProfile.AVC_MP4_LPCM;
			}
			if (tangible.DotNetToJavaStringHelper.isNullOrEmpty(audioCodec) || StringHelper.EqualsIgnoreCase(audioCodec, "ac3"))
			{
				return MediaFormatProfile.AVC_MP4_MP_SD_AC3;
			}
			if (StringHelper.EqualsIgnoreCase(audioCodec, "mp3"))
			{
				return MediaFormatProfile.AVC_MP4_MP_SD_MPEG1_L3;
			}
			if (width != null && height != null)
			{
				if ((width <= 720) && (height <= 576))
				{
					if (StringHelper.EqualsIgnoreCase(audioCodec, "aac"))
					{
						return MediaFormatProfile.AVC_MP4_MP_SD_AAC_MULT5;
					}
				}
				else if ((width <= 1280) && (height <= 720))
				{
					if (StringHelper.EqualsIgnoreCase(audioCodec, "aac"))
					{
						return MediaFormatProfile.AVC_MP4_MP_HD_720p_AAC;
					}
				}
				else if ((width <= 1920) && (height <= 1080))
				{
					if (StringHelper.EqualsIgnoreCase(audioCodec, "aac"))
					{
						return MediaFormatProfile.AVC_MP4_MP_HD_1080i_AAC;
					}
				}
			}
		}
		else if (StringHelper.EqualsIgnoreCase(videoCodec, "mpeg4") || StringHelper.EqualsIgnoreCase(videoCodec, "msmpeg4"))
		{
			if (width != null && height != null && width <= 720 && height <= 576)
			{
				if (tangible.DotNetToJavaStringHelper.isNullOrEmpty(audioCodec) || StringHelper.EqualsIgnoreCase(audioCodec, "aac"))
				{
					return MediaFormatProfile.MPEG4_P2_MP4_ASP_AAC;
				}
				if (StringHelper.EqualsIgnoreCase(audioCodec, "ac3") || StringHelper.EqualsIgnoreCase(audioCodec, "mp3"))
				{
					return MediaFormatProfile.MPEG4_P2_MP4_NDSD;
				}
			}
			else if (tangible.DotNetToJavaStringHelper.isNullOrEmpty(audioCodec) || StringHelper.EqualsIgnoreCase(audioCodec, "aac"))
			{
				return MediaFormatProfile.MPEG4_P2_MP4_SP_L6_AAC;
			}
		}
		else if (StringHelper.EqualsIgnoreCase(videoCodec, "h263") && StringHelper.EqualsIgnoreCase(audioCodec, "aac"))
		{
			return MediaFormatProfile.MPEG4_H263_MP4_P0_L10_AAC;
		}

		return null;
	}

	private MediaFormatProfile ResolveVideo3GPFormat(String videoCodec, String audioCodec)
	{
		if (StringHelper.EqualsIgnoreCase(videoCodec, "h264"))
		{
			if (tangible.DotNetToJavaStringHelper.isNullOrEmpty(audioCodec) || StringHelper.EqualsIgnoreCase(audioCodec, "aac"))
			{
				return MediaFormatProfile.AVC_3GPP_BL_QCIF15_AAC;
			}
		}
		else if (StringHelper.EqualsIgnoreCase(videoCodec, "mpeg4") || StringHelper.EqualsIgnoreCase(videoCodec, "msmpeg4"))
		{
			if (tangible.DotNetToJavaStringHelper.isNullOrEmpty(audioCodec) || StringHelper.EqualsIgnoreCase(audioCodec, "wma"))
			{
				return MediaFormatProfile.MPEG4_P2_3GPP_SP_L0B_AAC;
			}
			if (StringHelper.EqualsIgnoreCase(audioCodec, "amrnb"))
			{
				return MediaFormatProfile.MPEG4_P2_3GPP_SP_L0B_AMR;
			}
		}
		else if (StringHelper.EqualsIgnoreCase(videoCodec, "h263") && StringHelper.EqualsIgnoreCase(audioCodec, "amrnb"))
		{
			return MediaFormatProfile.MPEG4_H263_3GPP_P0_L10_AMR;
		}

		return null;
	}

	private MediaFormatProfile ResolveVideoASFFormat(String videoCodec, String audioCodec, Integer width, Integer height)
	{
		if (StringHelper.EqualsIgnoreCase(videoCodec, "wmv") && (tangible.DotNetToJavaStringHelper.isNullOrEmpty(audioCodec) || StringHelper.EqualsIgnoreCase(audioCodec, "wma") || StringHelper.EqualsIgnoreCase(videoCodec, "wmapro")))
		{

			if (width != null && height != null)
			{
				if ((width <= 720) && (height <= 576))
				{
					if (tangible.DotNetToJavaStringHelper.isNullOrEmpty(audioCodec) || StringHelper.EqualsIgnoreCase(audioCodec, "wma"))
					{
						return MediaFormatProfile.WMVMED_FULL;
					}
					return MediaFormatProfile.WMVMED_PRO;
				}
			}

			if (tangible.DotNetToJavaStringHelper.isNullOrEmpty(audioCodec) || StringHelper.EqualsIgnoreCase(audioCodec, "wma"))
			{
				return MediaFormatProfile.WMVHIGH_FULL;
			}
			return MediaFormatProfile.WMVHIGH_PRO;
		}

		if (StringHelper.EqualsIgnoreCase(videoCodec, "vc1"))
		{
			if (width != null && height != null)
			{
				if ((width <= 720) && (height <= 576))
				{
					return MediaFormatProfile.VC1_ASF_AP_L1_WMA;
				}
				if ((width <= 1280) && (height <= 720))
				{
					return MediaFormatProfile.VC1_ASF_AP_L2_WMA;
				}
				if ((width <= 1920) && (height <= 1080))
				{
					return MediaFormatProfile.VC1_ASF_AP_L3_WMA;
				}
			}
		}
		else if (StringHelper.EqualsIgnoreCase(videoCodec, "mpeg2video"))
		{
			return MediaFormatProfile.DVR_MS;
		}

		return null;
	}

	public final MediaFormatProfile ResolveAudioFormat(String container, Integer bitrate, Integer frequency, Integer channels)
	{
		if (StringHelper.EqualsIgnoreCase(container, "asf"))
		{
			return ResolveAudioASFFormat(bitrate);
		}

		if (StringHelper.EqualsIgnoreCase(container, "mp3"))
		{
			return MediaFormatProfile.MP3;
		}

		if (StringHelper.EqualsIgnoreCase(container, "lpcm"))
		{
			return ResolveAudioLPCMFormat(frequency, channels);
		}

		if (StringHelper.EqualsIgnoreCase(container, "mp4") || StringHelper.EqualsIgnoreCase(container, "aac"))
		{
			return ResolveAudioMP4Format(bitrate);
		}

		if (StringHelper.EqualsIgnoreCase(container, "adts"))
		{
			return ResolveAudioADTSFormat(bitrate);
		}

		if (StringHelper.EqualsIgnoreCase(container, "flac"))
		{
			return MediaFormatProfile.FLAC;
		}

		if (StringHelper.EqualsIgnoreCase(container, "oga") || StringHelper.EqualsIgnoreCase(container, "ogg"))
		{
			return MediaFormatProfile.OGG;
		}

		return null;
	}

	private MediaFormatProfile ResolveAudioASFFormat(Integer bitrate)
	{
		if (bitrate != null && bitrate <= 193)
		{
			return MediaFormatProfile.WMA_BASE;
		}
		return MediaFormatProfile.WMA_FULL;
	}

	private MediaFormatProfile ResolveAudioLPCMFormat(Integer frequency, Integer channels)
	{
		if (frequency != null && channels != null)
		{
			if (frequency == 44100 && channels == 1)
			{
				return MediaFormatProfile.LPCM16_44_MONO;
			}
			if (frequency == 44100 && channels == 2)
			{
				return MediaFormatProfile.LPCM16_44_STEREO;
			}
			if (frequency == 48000 && channels == 1)
			{
				return MediaFormatProfile.LPCM16_48_MONO;
			}
			if (frequency == 48000 && channels == 1)
			{
				return MediaFormatProfile.LPCM16_48_STEREO;
			}

			return null;
		}

		return MediaFormatProfile.LPCM16_48_STEREO;
	}

	private MediaFormatProfile ResolveAudioMP4Format(Integer bitrate)
	{
		if (bitrate != null && bitrate <= 320)
		{
			return MediaFormatProfile.AAC_ISO_320;
		}
		return MediaFormatProfile.AAC_ISO;
	}

	private MediaFormatProfile ResolveAudioADTSFormat(Integer bitrate)
	{
		if (bitrate != null && bitrate <= 320)
		{
			return MediaFormatProfile.AAC_ADTS_320;
		}
		return MediaFormatProfile.AAC_ADTS;
	}

	public final MediaFormatProfile ResolveImageFormat(String container, Integer width, Integer height)
	{
		if (StringHelper.EqualsIgnoreCase(container, "jpeg") || StringHelper.EqualsIgnoreCase(container, "jpg"))
		{
			return ResolveImageJPGFormat(width, height);
		}

		if (StringHelper.EqualsIgnoreCase(container, "png"))
		{
			return ResolveImagePNGFormat(width, height);
		}

		if (StringHelper.EqualsIgnoreCase(container, "gif"))
		{
			return MediaFormatProfile.GIF_LRG;
		}

		if (StringHelper.EqualsIgnoreCase(container, "raw"))
		{
			return MediaFormatProfile.RAW;
		}

		return null;
	}

	private MediaFormatProfile ResolveImageJPGFormat(Integer width, Integer height)
	{
		if (width != null && height != null)
		{
			if ((width <= 160) && (height <= 160))
			{
				return MediaFormatProfile.JPEG_TN;
			}

			if ((width <= 640) && (height <= 480))
			{
				return MediaFormatProfile.JPEG_SM;
			}

			if ((width <= 1024) && (height <= 768))
			{
				return MediaFormatProfile.JPEG_MED;
			}

			return MediaFormatProfile.JPEG_LRG;
		}

		return MediaFormatProfile.JPEG_SM;
	}

	private MediaFormatProfile ResolveImagePNGFormat(Integer width, Integer height)
	{
		if (width != null && height != null)
		{
			if ((width <= 160) && (height <= 160))
			{
				return MediaFormatProfile.PNG_TN;
			}
		}

		return MediaFormatProfile.PNG_LRG;
	}
}