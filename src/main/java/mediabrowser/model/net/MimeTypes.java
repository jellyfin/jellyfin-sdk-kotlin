package mediabrowser.model.net;

import com.google.common.io.Files;
import mediabrowser.model.extensions.StringHelper;

/**
 Class MimeTypes
 */
public final class MimeTypes
{
	/**
	 Any extension in this list is considered a video file - can be added to at runtime for extensibility
	 */
	private static final java.util.ArrayList<String> VideoFileExtensions = new java.util.ArrayList<String>(java.util.Arrays.asList(new String[] {".mkv", ".m2t", ".m2ts", ".img", ".iso", ".mk3d", ".ts", ".rmvb", ".mov", ".avi", ".mpg", ".mpeg", ".wmv", ".mp4", ".divx", ".dvr-ms", ".wtv", ".ogm", ".ogv", ".asf", ".m4v", ".flv", ".f4v", ".3gp", ".webm", ".mts", ".m2v", ".rec"}));

	private static java.util.HashMap<String, String> GetVideoFileExtensionsDictionary()
	{
		java.util.HashMap<String, String> dict = new java.util.HashMap<String, String>();

		for (String ext : VideoFileExtensions)
		{
			dict.put(ext, ext);
		}

		return dict;
	}

	private static final java.util.HashMap<String, String> VideoFileExtensionsDictionary = GetVideoFileExtensionsDictionary();

	// http://en.wikipedia.org/wiki/Internet_media_type
	// Add more as needed

	private static java.util.HashMap<String, String> GetMimeTypeLookup()
	{
		java.util.HashMap<String, String> dict = new java.util.HashMap<String, String>();

		dict.put(".jpg", "image/jpeg");
		dict.put(".jpeg", "image/jpeg");
		dict.put(".tbn", "image/jpeg");
		dict.put(".png", "image/png");
		dict.put(".gif", "image/gif");
		dict.put(".webp", "image/webp");
		dict.put(".ico", "image/vnd.microsoft.icon");
		dict.put(".mpg", "video/mpeg");
		dict.put(".mpeg", "video/mpeg");
		dict.put(".ogv", "video/ogg");
		dict.put(".mov", "video/quicktime");
		dict.put(".webm", "video/webm");
		dict.put(".mkv", "video/x-matroska");
		dict.put(".wmv", "video/x-ms-wmv");
		dict.put(".flv", "video/x-flv");
		dict.put(".avi", "video/x-msvideo");
		dict.put(".asf", "video/x-ms-asf");
		dict.put(".m4v", "video/x-m4v");

		return dict;
	}

	private static final java.util.HashMap<String, String> MimeTypeLookup = GetMimeTypeLookup();

	/**
	 Gets the type of the MIME.

	 @param path The path.
	 @return System.String.
	 */
	public static String GetMimeType(String path)
	{
		if (tangible.DotNetToJavaStringHelper.isNullOrEmpty(path))
		{
			throw new IllegalArgumentException("path");
		}

		String ext = Files.getFileExtension(path);

		String result = null;
		if (MimeTypeLookup.containsKey(ext) ? (result = MimeTypeLookup.get(ext)).equals(result) : false)
		{
			return result;
		}

		// Type video
		if (StringHelper.EqualsIgnoreCase(ext, ".3gp"))
		{
			return "video/3gpp";
		}
		if (StringHelper.EqualsIgnoreCase(ext, ".3g2"))
		{
			return "video/3gpp2";
		}
		if (StringHelper.EqualsIgnoreCase(ext, ".ts"))
		{
			return "video/mp2t";
		}
		if (StringHelper.EqualsIgnoreCase(ext, ".mpd"))
		{
			return "video/vnd.mpeg.dash.mpd";
		}

		// Catch-all for all video types that don't require specific mime types
		if (VideoFileExtensionsDictionary.containsKey(ext))
		{
			return "video/" + ext.substring(1).toLowerCase();
		}

		// Type text
		if (StringHelper.EqualsIgnoreCase(ext, ".css"))
		{
			return "text/css";
		}
		if (StringHelper.EqualsIgnoreCase(ext, ".csv"))
		{
			return "text/csv";
		}
		if (StringHelper.EqualsIgnoreCase(ext, ".html"))
		{
			return "text/html; charset=UTF-8";
		}
		if (StringHelper.EqualsIgnoreCase(ext, ".htm"))
		{
			return "text/html; charset=UTF-8";
		}
		if (StringHelper.EqualsIgnoreCase(ext, ".txt"))
		{
			return "text/plain";
		}
		if (StringHelper.EqualsIgnoreCase(ext, ".xml"))
		{
			return "application/xml";
		}

		// Type document
		if (StringHelper.EqualsIgnoreCase(ext, ".pdf"))
		{
			return "application/pdf";
		}
		if (StringHelper.EqualsIgnoreCase(ext, ".mobi"))
		{
			return "application/x-mobipocket-ebook";
		}
		if (StringHelper.EqualsIgnoreCase(ext, ".epub"))
		{
			return "application/epub+zip";
		}
		if (StringHelper.EqualsIgnoreCase(ext, ".cbz"))
		{
			return "application/epub+zip";
		}
		if (StringHelper.EqualsIgnoreCase(ext, ".cbr"))
		{
			return "application/epub+zip";
		}

		// Type audio
		if (StringHelper.EqualsIgnoreCase(ext, ".mp3"))
		{
			return "audio/mpeg";
		}
		if (StringHelper.EqualsIgnoreCase(ext, ".m4a"))
		{
			return "audio/mp4";
		}
		if (StringHelper.EqualsIgnoreCase(ext, ".aac"))
		{
			return "audio/mp4";
		}
		if (StringHelper.EqualsIgnoreCase(ext, ".webma"))
		{
			return "audio/webm";
		}
		if (StringHelper.EqualsIgnoreCase(ext, ".wav"))
		{
			return "audio/wav";
		}
		if (StringHelper.EqualsIgnoreCase(ext, ".wma"))
		{
			return "audio/x-ms-wma";
		}
		if (StringHelper.EqualsIgnoreCase(ext, ".flac"))
		{
			return "audio/flac";
		}
		if (StringHelper.EqualsIgnoreCase(ext, ".aac"))
		{
			return "audio/x-aac";
		}
		if (StringHelper.EqualsIgnoreCase(ext, ".ogg"))
		{
			return "audio/ogg";
		}
		if (StringHelper.EqualsIgnoreCase(ext, ".oga"))
		{
			return "audio/ogg";
		}

		// Playlists
		if (StringHelper.EqualsIgnoreCase(ext, ".m3u8"))
		{
			return "application/x-mpegURL";
		}

		// Misc
		if (StringHelper.EqualsIgnoreCase(ext, ".dll"))
		{
			return "application/octet-stream";
		}

		// Web
		if (StringHelper.EqualsIgnoreCase(ext, ".js"))
		{
			return "application/x-javascript";
		}
		if (StringHelper.EqualsIgnoreCase(ext, ".json"))
		{
			return "application/json";
		}
		if (StringHelper.EqualsIgnoreCase(ext, ".map"))
		{
			return "application/x-javascript";
		}

		if (StringHelper.EqualsIgnoreCase(ext, ".woff"))
		{
			return "font/woff";
		}

		if (StringHelper.EqualsIgnoreCase(ext, ".ttf"))
		{
			return "font/ttf";
		}
		if (StringHelper.EqualsIgnoreCase(ext, ".eot"))
		{
			return "application/vnd.ms-fontobject";
		}
		if (StringHelper.EqualsIgnoreCase(ext, ".svg"))
		{
			return "image/svg+xml";
		}
		if (StringHelper.EqualsIgnoreCase(ext, ".svgz"))
		{
			return "image/svg+xml";
		}

		if (StringHelper.EqualsIgnoreCase(ext, ".srt"))
		{
			return "text/plain";
		}

		if (StringHelper.EqualsIgnoreCase(ext, ".vtt"))
		{
			return "text/vtt";
		}

		if (StringHelper.EqualsIgnoreCase(ext, ".ttml"))
		{
			return "application/ttml+xml";
		}

		return "application/octet-stream";
	}
}