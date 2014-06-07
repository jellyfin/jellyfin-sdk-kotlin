package MediaBrowser.Model.FileOrganization;

public class EpisodeFileOrganizationRequest
{
	private String privateResultId;
	public final String getResultId()
	{
		return privateResultId;
	}
	public final void setResultId(String value)
	{
		privateResultId = value;
	}

	private String privateSeriesId;
	public final String getSeriesId()
	{
		return privateSeriesId;
	}
	public final void setSeriesId(String value)
	{
		privateSeriesId = value;
	}

	private int privateSeasonNumber;
	public final int getSeasonNumber()
	{
		return privateSeasonNumber;
	}
	public final void setSeasonNumber(int value)
	{
		privateSeasonNumber = value;
	}

	private int privateEpisodeNumber;
	public final int getEpisodeNumber()
	{
		return privateEpisodeNumber;
	}
	public final void setEpisodeNumber(int value)
	{
		privateEpisodeNumber = value;
	}

	private Integer privateEndingEpisodeNumber;
	public final Integer getEndingEpisodeNumber()
	{
		return privateEndingEpisodeNumber;
	}
	public final void setEndingEpisodeNumber(Integer value)
	{
		privateEndingEpisodeNumber = value;
	}

	private boolean privateRememberCorrection;
	public final boolean getRememberCorrection()
	{
		return privateRememberCorrection;
	}
	public final void setRememberCorrection(boolean value)
	{
		privateRememberCorrection = value;
	}
}