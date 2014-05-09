package MediaBrowser.Model.Dlna;

import MediaBrowser.Model.MediaInfo.*;

public class MediaFormatProfileResolver
{
	public final Iterable<MediaFormatProfile> ResolveVideoFormat(String container, String videoCodec, String audioCodec, Integer width, Integer height, TransportStreamTimestamp timestampType)
	{
		if (String.equals(container, "asf", StringComparison.OrdinalIgnoreCase))
		{
			MediaFormatProfile val = ResolveVideoASFFormat(videoCodec, audioCodec, width, height);
			return val != null ? new java.util.ArrayList<MediaFormatProfile>(java.util.Arrays.asList(new MediaFormatProfile[] {val})) : new java.util.ArrayList<MediaFormatProfile>();
		}

		if (String.equals(container, "mp4", StringComparison.OrdinalIgnoreCase))
		{
			MediaFormatProfile val = ResolveVideoMP4Format(videoCodec, audioCodec, width, height);
			return val != null ? new java.util.ArrayList<MediaFormatProfile>(java.util.Arrays.asList(new MediaFormatProfile[] {val})) : new java.util.ArrayList<MediaFormatProfile>();
		}

		if (String.equals(container, "avi", StringComparison.OrdinalIgnoreCase))
		{
			return new Object[] {MediaFormatProfile.AVI};
		}

		if (String.equals(container, "mkv", StringComparison.OrdinalIgnoreCase))
		{
			return new Object[] {MediaFormatProfile.MATROSKA};
		}

		if (String.equals(container, "mpeg2ps", StringComparison.OrdinalIgnoreCase) || String.equals(container, "ts", StringComparison.OrdinalIgnoreCase))

		{
			return new Object[] {MediaFormatProfile.MPEG_PS_NTSC, MediaFormatProfile.MPEG_PS_PAL};
		}

		if (String.equals(container, "mpeg1video", StringComparison.OrdinalIgnoreCase))
		{
			return new Object[] {MediaFormatProfile.MPEG1};
		}

		if (String.equals(container, "mpeg2ts", StringComparison.OrdinalIgnoreCase) || String.equals(container, "mpegts", StringComparison.OrdinalIgnoreCase) || String.equals(container, "m2ts", StringComparison.OrdinalIgnoreCase))
		{

			return ResolveVideoMPEG2TSFormat(videoCodec, audioCodec, width, height, timestampType);
		}

		if (String.equals(container, "flv", StringComparison.OrdinalIgnoreCase))
		{
			return new Object[] {MediaFormatProfile.FLV};
		}

		if (String.equals(container, "wtv", StringComparison.OrdinalIgnoreCase))
		{
			return new Object[] {MediaFormatProfile.WTV};
		}

		if (String.equals(container, "3gp", StringComparison.OrdinalIgnoreCase))
		{
			MediaFormatProfile val = ResolveVideo3GPFormat(videoCodec, audioCodec);
			return val != null ? new java.util.ArrayList<MediaFormatProfile>(java.util.Arrays.asList(new MediaFormatProfile[] {val})) : new java.util.ArrayList<MediaFormatProfile>();
		}

		if (String.equals(container, "ogv", StringComparison.OrdinalIgnoreCase) || String.equals(container, "ogg", StringComparison.OrdinalIgnoreCase))
		{
			return new Object[] {MediaFormatProfile.OGV};
		}

		return new java.util.ArrayList<MediaFormatProfile>();
	}

	private Iterable<MediaFormatProfile> ResolveVideoMPEG2TSFormat(String videoCodec, String audioCodec, Integer width, Integer height, TransportStreamTimestamp timestampType)
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

		if (String.equals(videoCodec, "mpeg2video", StringComparison.OrdinalIgnoreCase))
		{
			java.util.ArrayList<MediaFormatProfile> list = new java.util.ArrayList<MediaFormatProfile>();

			list.add(ValueOf("MPEG_TS_SD_NA" + suffix));
			list.add(ValueOf("MPEG_TS_SD_EU" + suffix));
			list.add(ValueOf("MPEG_TS_SD_KO" + suffix));

			if ((timestampType == TransportStreamTimestamp.Valid) && String.equals(audioCodec, "aac", StringComparison.OrdinalIgnoreCase))
			{
				list.add(MediaFormatProfile.MPEG_TS_JP_T);
			}
			return list;
		}
		if (String.equals(videoCodec, "h264", StringComparison.OrdinalIgnoreCase))
		{
			if (String.equals(audioCodec, "lpcm", StringComparison.OrdinalIgnoreCase))
			{
				return new Object[] {MediaFormatProfile.AVC_TS_HD_50_LPCM_T};
			}

			if (String.equals(audioCodec, "dts", StringComparison.OrdinalIgnoreCase))
			{
				if (timestampType == TransportStreamTimestamp.None)
				{
					return new Object[] {MediaFormatProfile.AVC_TS_HD_DTS_ISO};
				}
				return new Object[] {MediaFormatProfile.AVC_TS_HD_DTS_T};
			}

			if (String.equals(audioCodec, "mp3", StringComparison.OrdinalIgnoreCase))
			{
				if (timestampType == TransportStreamTimestamp.None)
				{
					return new MediaFormatProfile[] {ValueOf(String.format("AVC_TS_HP_%1$sD_MPEG1_L2_ISO", resolution))};
				}

				return new MediaFormatProfile[] {ValueOf(String.format("AVC_TS_HP_%1$sD_MPEG1_L2_T", resolution))};
			}

			if (String.equals(audioCodec, "aac", StringComparison.OrdinalIgnoreCase))
			{
				return new MediaFormatProfile[] {ValueOf(String.format("AVC_TS_MP_%1$sD_AAC_MULT5%2$s", resolution, suffix))};
			}

			if (String.equals(audioCodec, "mp3", StringComparison.OrdinalIgnoreCase))
			{
				return new MediaFormatProfile[] {ValueOf(String.format("AVC_TS_MP_%1$sD_MPEG1_L3%2$s", resolution, suffix))};
			}

			if (tangible.DotNetToJavaStringHelper.isNullOrEmpty(audioCodec) || String.equals(audioCodec, "ac3", StringComparison.OrdinalIgnoreCase))
			{
				return new MediaFormatProfile[] {ValueOf(String.format("AVC_TS_MP_%1$sD_AC3%2$s", resolution, suffix))};
			}
		}
		else if (String.equals(videoCodec, "vc1", StringComparison.OrdinalIgnoreCase))
		{
			if (tangible.DotNetToJavaStringHelper.isNullOrEmpty(audioCodec) || String.equals(audioCodec, "ac3", StringComparison.OrdinalIgnoreCase))
			{
				if ((width != null && width > 720) || (height != null && height > 576))
				{
					return new Object[] {MediaFormatProfile.VC1_TS_AP_L2_AC3_ISO};
				}
				return new Object[] {MediaFormatProfile.VC1_TS_AP_L1_AC3_ISO};
			}
			if (String.equals(audioCodec, "dts", StringComparison.OrdinalIgnoreCase))
			{
				suffix = String.equals(suffix, "_ISO") ? suffix : "_T";

				return new MediaFormatProfile[] {ValueOf(String.format("VC1_TS_HD_DTS%1$s", suffix))};
			}

		}
		else if (String.equals(videoCodec, "mpeg4", StringComparison.OrdinalIgnoreCase) || String.equals(videoCodec, "msmpeg4", StringComparison.OrdinalIgnoreCase))
		{
			if (String.equals(audioCodec, "aac", StringComparison.OrdinalIgnoreCase))
			{
				return new MediaFormatProfile[] {ValueOf(String.format("MPEG4_P2_TS_ASP_AAC%1$s", suffix))};
			}
			if (String.equals(audioCodec, "mp3", StringComparison.OrdinalIgnoreCase))
			{
				return new MediaFormatProfile[] {ValueOf(String.format("MPEG4_P2_TS_ASP_MPEG1_L3%1$s", suffix))};
			}
			if (String.equals(audioCodec, "mp2", StringComparison.OrdinalIgnoreCase))
			{
				return new MediaFormatProfile[] {ValueOf(String.format("MPEG4_P2_TS_ASP_MPEG2_L2%1$s", suffix))};
			}
			if (String.equals(audioCodec, "ac3", StringComparison.OrdinalIgnoreCase))
			{
				return new MediaFormatProfile[] {ValueOf(String.format("MPEG4_P2_TS_ASP_AC3%1$s", suffix))};
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
		if (String.equals(videoCodec, "h264", StringComparison.OrdinalIgnoreCase))
		{
			if (String.equals(audioCodec, "lpcm", StringComparison.OrdinalIgnoreCase))
			{
				return MediaFormatProfile.AVC_MP4_LPCM;
			}
			if (tangible.DotNetToJavaStringHelper.isNullOrEmpty(audioCodec) || String.equals(audioCodec, "ac3", StringComparison.OrdinalIgnoreCase))
			{
				return MediaFormatProfile.AVC_MP4_MP_SD_AC3;
			}
			if (String.equals(audioCodec, "mp3", StringComparison.OrdinalIgnoreCase))
			{
				return MediaFormatProfile.AVC_MP4_MP_SD_MPEG1_L3;
			}
			if (width != null && height != null)
			{
				if ((width <= 720) && (height <= 576))
				{
					if (String.equals(audioCodec, "aac", StringComparison.OrdinalIgnoreCase))
					{
						return MediaFormatProfile.AVC_MP4_MP_SD_AAC_MULT5;
					}
				}
				else if ((width <= 1280) && (height <= 720))
				{
					if (String.equals(audioCodec, "aac", StringComparison.OrdinalIgnoreCase))
					{
						return MediaFormatProfile.AVC_MP4_MP_HD_720p_AAC;
					}
				}
				else if ((width <= 1920) && (height <= 1080))
				{
					if (String.equals(audioCodec, "aac", StringComparison.OrdinalIgnoreCase))
					{
						return MediaFormatProfile.AVC_MP4_MP_HD_1080i_AAC;
					}
				}
			}
		}
		else if (String.equals(videoCodec, "mpeg4", StringComparison.OrdinalIgnoreCase) || String.equals(videoCodec, "msmpeg4", StringComparison.OrdinalIgnoreCase))
		{
			if (width != null && height != null && width <= 720 && height <= 576)
			{
				if (tangible.DotNetToJavaStringHelper.isNullOrEmpty(audioCodec) || String.equals(audioCodec, "aac", StringComparison.OrdinalIgnoreCase))
				{
					return MediaFormatProfile.MPEG4_P2_MP4_ASP_AAC;
				}
				if (String.equals(audioCodec, "ac3", StringComparison.OrdinalIgnoreCase) || String.equals(audioCodec, "mp3", StringComparison.OrdinalIgnoreCase))
				{
					return MediaFormatProfile.MPEG4_P2_MP4_NDSD;
				}
			}
			else if (tangible.DotNetToJavaStringHelper.isNullOrEmpty(audioCodec) || String.equals(audioCodec, "aac", StringComparison.OrdinalIgnoreCase))
			{
				return MediaFormatProfile.MPEG4_P2_MP4_SP_L6_AAC;
			}
		}
		else if (String.equals(videoCodec, "h263", StringComparison.OrdinalIgnoreCase) && String.equals(audioCodec, "aac", StringComparison.OrdinalIgnoreCase))
		{
			return MediaFormatProfile.MPEG4_H263_MP4_P0_L10_AAC;
		}

		return null;
	}

	private MediaFormatProfile ResolveVideo3GPFormat(String videoCodec, String audioCodec)
	{
		if (String.equals(videoCodec, "h264", StringComparison.OrdinalIgnoreCase))
		{
			if (tangible.DotNetToJavaStringHelper.isNullOrEmpty(audioCodec) || String.equals(audioCodec, "aac", StringComparison.OrdinalIgnoreCase))
			{
				return MediaFormatProfile.AVC_3GPP_BL_QCIF15_AAC;
			}
		}
		else if (String.equals(videoCodec, "mpeg4", StringComparison.OrdinalIgnoreCase) || String.equals(videoCodec, "msmpeg4", StringComparison.OrdinalIgnoreCase))
		{
			if (tangible.DotNetToJavaStringHelper.isNullOrEmpty(audioCodec) || String.equals(audioCodec, "wma", StringComparison.OrdinalIgnoreCase))
			{
				return MediaFormatProfile.MPEG4_P2_3GPP_SP_L0B_AAC;
			}
			if (String.equals(audioCodec, "amrnb", StringComparison.OrdinalIgnoreCase))
			{
				return MediaFormatProfile.MPEG4_P2_3GPP_SP_L0B_AMR;
			}
		}
		else if (String.equals(videoCodec, "h263", StringComparison.OrdinalIgnoreCase) && String.equals(audioCodec, "amrnb", StringComparison.OrdinalIgnoreCase))
		{
			return MediaFormatProfile.MPEG4_H263_3GPP_P0_L10_AMR;
		}

		return null;
	}

	private MediaFormatProfile ResolveVideoASFFormat(String videoCodec, String audioCodec, Integer width, Integer height)
	{
		if (String.equals(videoCodec, "wmv", StringComparison.OrdinalIgnoreCase) && (tangible.DotNetToJavaStringHelper.isNullOrEmpty(audioCodec) || String.equals(audioCodec, "wma", StringComparison.OrdinalIgnoreCase) || String.equals(videoCodec, "wmapro", StringComparison.OrdinalIgnoreCase)))
		{

			if (width != null && height != null)
			{
				if ((width <= 720) && (height <= 576))
				{
					if (tangible.DotNetToJavaStringHelper.isNullOrEmpty(audioCodec) || String.equals(audioCodec, "wma", StringComparison.OrdinalIgnoreCase))
					{
						return MediaFormatProfile.WMVMED_FULL;
					}
					return MediaFormatProfile.WMVMED_PRO;
				}
			}

			if (tangible.DotNetToJavaStringHelper.isNullOrEmpty(audioCodec) || String.equals(audioCodec, "wma", StringComparison.OrdinalIgnoreCase))
			{
				return MediaFormatProfile.WMVHIGH_FULL;
			}
			return MediaFormatProfile.WMVHIGH_PRO;
		}

		if (String.equals(videoCodec, "vc1", StringComparison.OrdinalIgnoreCase))
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
		else if (String.equals(videoCodec, "mpeg2video", StringComparison.OrdinalIgnoreCase))
		{
			return MediaFormatProfile.DVR_MS;
		}

		return null;
	}

	public final MediaFormatProfile ResolveAudioFormat(String container, Integer bitrate, Integer frequency, Integer channels)
	{
		if (String.equals(container, "asf", StringComparison.OrdinalIgnoreCase))
		{
			return ResolveAudioASFFormat(bitrate);
		}

		if (String.equals(container, "mp3", StringComparison.OrdinalIgnoreCase))
		{
			return MediaFormatProfile.MP3;
		}

		if (String.equals(container, "lpcm", StringComparison.OrdinalIgnoreCase))
		{
			return ResolveAudioLPCMFormat(frequency, channels);
		}

		if (String.equals(container, "mp4", StringComparison.OrdinalIgnoreCase) || String.equals(container, "aac", StringComparison.OrdinalIgnoreCase))
		{
			return ResolveAudioMP4Format(bitrate);
		}

		if (String.equals(container, "adts", StringComparison.OrdinalIgnoreCase))
		{
			return ResolveAudioADTSFormat(bitrate);
		}

		if (String.equals(container, "flac", StringComparison.OrdinalIgnoreCase))
		{
			return MediaFormatProfile.FLAC;
		}

		if (String.equals(container, "oga", StringComparison.OrdinalIgnoreCase) || String.equals(container, "ogg", StringComparison.OrdinalIgnoreCase))
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
		if (String.equals(container, "jpeg", StringComparison.OrdinalIgnoreCase) || String.equals(container, "jpg", StringComparison.OrdinalIgnoreCase))
		{
			return ResolveImageJPGFormat(width, height);
		}

		if (String.equals(container, "png", StringComparison.OrdinalIgnoreCase))
		{
			return MediaFormatProfile.PNG_LRG;
		}

		if (String.equals(container, "gif", StringComparison.OrdinalIgnoreCase))
		{
			return MediaFormatProfile.GIF_LRG;
		}

		if (String.equals(container, "raw", StringComparison.OrdinalIgnoreCase))
		{
			return MediaFormatProfile.RAW;
		}

		return null;
	}

	private MediaFormatProfile ResolveImageJPGFormat(Integer width, Integer height)
	{
		if (width != null && height != null)
		{
			if ((width <= 640) && (height <= 480))
			{
				return MediaFormatProfile.JPEG_SM;
			}

			if ((width <= 1024) && (height <= 768))
			{
				return MediaFormatProfile.JPEG_MED;
			}
		}

		return MediaFormatProfile.JPEG_LRG;
	}
}