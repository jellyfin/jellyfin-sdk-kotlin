package mediabrowser.model.sync;

public class SyncOptions
{
	private String TemporaryPath;
	public final String getTemporaryPath()
	{
		return TemporaryPath;
	}
	public final void setTemporaryPath(String value)
	{
		TemporaryPath = value;
	}
	private long UploadSpeedLimitBytes;
	public final long getUploadSpeedLimitBytes()
	{
		return UploadSpeedLimitBytes;
	}
	public final void setUploadSpeedLimitBytes(long value)
	{
		UploadSpeedLimitBytes = value;
	}
	private int TranscodingCpuCoreLimit;
	public final int getTranscodingCpuCoreLimit()
	{
		return TranscodingCpuCoreLimit;
	}
	public final void setTranscodingCpuCoreLimit(int value)
	{
		TranscodingCpuCoreLimit = value;
	}
	private boolean EnableFullSpeedTranscoding;
	public final boolean getEnableFullSpeedTranscoding()
	{
		return EnableFullSpeedTranscoding;
	}
	public final void setEnableFullSpeedTranscoding(boolean value)
	{
		EnableFullSpeedTranscoding = value;
	}

	public SyncOptions()
	{
		setTranscodingCpuCoreLimit(1);
	}
}