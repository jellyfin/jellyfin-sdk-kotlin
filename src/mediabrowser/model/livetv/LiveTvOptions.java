package mediabrowser.model.livetv;

public class LiveTvOptions
{
	private Integer GuideDays = null;
	public final Integer getGuideDays()
	{
		return GuideDays;
	}
	public final void setGuideDays(Integer value)
	{
		GuideDays = value;
	}
	private boolean EnableMovieProviders;
	public final boolean getEnableMovieProviders()
	{
		return EnableMovieProviders;
	}
	public final void setEnableMovieProviders(boolean value)
	{
		EnableMovieProviders = value;
	}
	private String RecordingPath;
	public final String getRecordingPath()
	{
		return RecordingPath;
	}
	public final void setRecordingPath(String value)
	{
		RecordingPath = value;
	}
}