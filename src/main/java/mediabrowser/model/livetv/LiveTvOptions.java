package mediabrowser.model.livetv;

import mediabrowser.model.dto.*;
import mediabrowser.model.extensions.*;

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
	private boolean EnableRecordingEncoding;
	public final boolean getEnableRecordingEncoding()
	{
		return EnableRecordingEncoding;
	}
	public final void setEnableRecordingEncoding(boolean value)
	{
		EnableRecordingEncoding = value;
	}
	private String RecordingEncodingFormat;
	public final String getRecordingEncodingFormat()
	{
		return RecordingEncodingFormat;
	}
	public final void setRecordingEncodingFormat(String value)
	{
		RecordingEncodingFormat = value;
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
	private String RecordedVideoCodec;
	public final String getRecordedVideoCodec()
	{
		return RecordedVideoCodec;
	}
	public final void setRecordedVideoCodec(String value)
	{
		RecordedVideoCodec = value;
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

	private String[] MediaLocationsCreated;
	public final String[] getMediaLocationsCreated()
	{
		return MediaLocationsCreated;
	}
	public final void setMediaLocationsCreated(String[] value)
	{
		MediaLocationsCreated = value;
	}

	private String RecordingPostProcessor;
	public final String getRecordingPostProcessor()
	{
		return RecordingPostProcessor;
	}
	public final void setRecordingPostProcessor(String value)
	{
		RecordingPostProcessor = value;
	}
	private String RecordingPostProcessorArguments;
	public final String getRecordingPostProcessorArguments()
	{
		return RecordingPostProcessorArguments;
	}
	public final void setRecordingPostProcessorArguments(String value)
	{
		RecordingPostProcessorArguments = value;
	}

	public LiveTvOptions()
	{
		setEnableMovieProviders(true);
		setTunerHosts(new java.util.ArrayList<TunerHostInfo>());
		setListingProviders(new java.util.ArrayList<ListingsProviderInfo>());
		setMediaLocationsCreated(new String[] { });
		setRecordingEncodingFormat("mp4");
		setRecordingPostProcessorArguments("\"{path}\"");
		setEnableRecordingEncoding(true);
	}
}