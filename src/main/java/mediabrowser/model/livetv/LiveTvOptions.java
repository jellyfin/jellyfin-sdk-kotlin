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
	private String MovieRecordingPath;
	public final String getMovieRecordingPath()
	{
		return MovieRecordingPath;
	}
	public final void setMovieRecordingPath(String value)
	{
		MovieRecordingPath = value;
	}
	private String SeriesRecordingPath;
	public final String getSeriesRecordingPath()
	{
		return SeriesRecordingPath;
	}
	public final void setSeriesRecordingPath(String value)
	{
		SeriesRecordingPath = value;
	}
	private boolean EnableAutoOrganize;
	public final boolean getEnableAutoOrganize()
	{
		return EnableAutoOrganize;
	}
	public final void setEnableAutoOrganize(boolean value)
	{
		EnableAutoOrganize = value;
	}
	private boolean EnableRecordingEncoding;
	public final boolean getEnableRecordingEncoding()
	{
		return EnableRecordingEncoding;
	}
	public final void setEnableRecordingEncoding(boolean value)
	{
		EnableRecordingEncoding = value;
	}
	private boolean EnableRecordingSubfolders;
	public final boolean getEnableRecordingSubfolders()
	{
		return EnableRecordingSubfolders;
	}
	public final void setEnableRecordingSubfolders(boolean value)
	{
		EnableRecordingSubfolders = value;
	}
	private boolean EnableOriginalAudioWithEncodedRecordings;
	public final boolean getEnableOriginalAudioWithEncodedRecordings()
	{
		return EnableOriginalAudioWithEncodedRecordings;
	}
	public final void setEnableOriginalAudioWithEncodedRecordings(boolean value)
	{
		EnableOriginalAudioWithEncodedRecordings = value;
	}

	private java.util.ArrayList<TunerHostInfo> TunerHosts;
	public final java.util.ArrayList<TunerHostInfo> getTunerHosts()
	{
		return TunerHosts;
	}
	public final void setTunerHosts(java.util.ArrayList<TunerHostInfo> value)
	{
		TunerHosts = value;
	}
	private java.util.ArrayList<ListingsProviderInfo> ListingProviders;
	public final java.util.ArrayList<ListingsProviderInfo> getListingProviders()
	{
		return ListingProviders;
	}
	public final void setListingProviders(java.util.ArrayList<ListingsProviderInfo> value)
	{
		ListingProviders = value;
	}

	private int PrePaddingSeconds;
	public final int getPrePaddingSeconds()
	{
		return PrePaddingSeconds;
	}
	public final void setPrePaddingSeconds(int value)
	{
		PrePaddingSeconds = value;
	}
	private int PostPaddingSeconds;
	public final int getPostPaddingSeconds()
	{
		return PostPaddingSeconds;
	}
	public final void setPostPaddingSeconds(int value)
	{
		PostPaddingSeconds = value;
	}

	public LiveTvOptions()
	{
		setEnableMovieProviders(true);
		setEnableRecordingSubfolders(true);
		setTunerHosts(new java.util.ArrayList<TunerHostInfo>());
		setListingProviders(new java.util.ArrayList<ListingsProviderInfo>());
	}
}