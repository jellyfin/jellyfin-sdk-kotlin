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
}