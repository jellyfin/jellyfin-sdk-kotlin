package MediaBrowser.Model.Dlna;

public class DlnaMaps
{
	public static final String DefaultStreaming = FlagsToString(DlnaFlags.StreamingTransferMode | DlnaFlags.BackgroundTransferMode | DlnaFlags.ConnectionStall | DlnaFlags.ByteBasedSeek | DlnaFlags.DlnaV15);

	public static final String DefaultInteractive = FlagsToString(DlnaFlags.InteractiveTransferMode | DlnaFlags.BackgroundTransferMode | DlnaFlags.ConnectionStall | DlnaFlags.ByteBasedSeek | DlnaFlags.DlnaV15);

	public static String FlagsToString(DlnaFlags flags)
	{
//C# TO JAVA CONVERTER WARNING: Unsigned integer types have no direct equivalent in Java:
//ORIGINAL LINE: return string.Format("{0:X8}{1:D24}", (ulong)flags, 0);
		return String.format("%08X%1.24d", (long)flags, 0);
	}

	public static String GetOrgOpValue(boolean hasKnownRuntime, boolean isDirectStream, TranscodeSeekInfo profileTranscodeSeekInfo)
	{
		if (hasKnownRuntime)
		{
			String orgOp = "";

			// Time-based seeking currently only possible when transcoding
			orgOp += isDirectStream ? "0" : "1";

			// Byte-based seeking only possible when not transcoding
			orgOp += isDirectStream || profileTranscodeSeekInfo == TranscodeSeekInfo.Bytes ? "1" : "0";

			return orgOp;
		}

		// No seeking is available if we don't know the content runtime
		return "00";
	}

	public static String GetImageOrgOpValue()
	{
		String orgOp = "";

		// Time-based seeking currently only possible when transcoding
		orgOp += "0";

		// Byte-based seeking only possible when not transcoding
		orgOp += "1";

		return orgOp;
	}
}