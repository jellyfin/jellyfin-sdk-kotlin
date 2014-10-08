package MediaBrowser.Model.FileOrganization;

public class EpisodeFileOrganizationRequest
{
	private String ResultId;
	public final String getResultId()
	{
		return ResultId;
	}
	public final void setResultId(String value)
	{
		ResultId = value;
	}

	private String SeriesId;
	public final String getSeriesId()
	{
		return SeriesId;
	}
	public final void setSeriesId(String value)
	{
		SeriesId = value;
	}

	private int SeasonNumber;
	public final int getSeasonNumber()
	{
		return SeasonNumber;
	}
	public final void setSeasonNumber(int value)
	{
		SeasonNumber = value;
	}

	private int EpisodeNumber;
	public final int getEpisodeNumber()
	{
		return EpisodeNumber;
	}
	public final void setEpisodeNumber(int value)
	{
		EpisodeNumber = value;
	}

	private Integer EndingEpisodeNumber;
	public final Integer getEndingEpisodeNumber()
	{
		return EndingEpisodeNumber;
	}
	public final void setEndingEpisodeNumber(Integer value)
	{
		EndingEpisodeNumber = value;
	}

	private boolean RememberCorrection;
	public final boolean getRememberCorrection()
	{
		return RememberCorrection;
	}
	public final void setRememberCorrection(boolean value)
	{
		RememberCorrection = value;
	}
}