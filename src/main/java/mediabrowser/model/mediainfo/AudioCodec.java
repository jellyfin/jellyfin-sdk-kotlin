package mediabrowser.model.mediainfo;

public class AudioCodec
{
	public static final String AAC = "aac";
	public static final String MP3 = "mp3";
	public static final String AC3 = "ac3";

	public static String GetFriendlyName(String codec)
	{
		if (tangible.DotNetToJavaStringHelper.isNullOrEmpty(codec))
		{
			return "";
		}

		switch (codec.toLowerCase())
		{
			case "ac3":
				return "Dolby Digital";
			case "eac3":
				return "Dolby Digital+";
			case "dca":
				return "DTS";
			default:
				return codec.toUpperCase();
		}
	}
}