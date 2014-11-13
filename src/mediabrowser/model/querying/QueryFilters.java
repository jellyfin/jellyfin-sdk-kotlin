package mediabrowser.model.querying;

public class QueryFilters
{
	private String[] Genres;
	public final String[] getGenres()
	{
		return Genres;
	}
	public final void setGenres(String[] value)
	{
		Genres = value;
	}
	private String[] Tags;
	public final String[] getTags()
	{
		return Tags;
	}
	public final void setTags(String[] value)
	{
		Tags = value;
	}
	private String[] OfficialRatings;
	public final String[] getOfficialRatings()
	{
		return OfficialRatings;
	}
	public final void setOfficialRatings(String[] value)
	{
		OfficialRatings = value;
	}
	private int[] Years;
	public final int[] getYears()
	{
		return Years;
	}
	public final void setYears(int[] value)
	{
		Years = value;
	}

	public QueryFilters()
	{
		setGenres(new String[] { });
		setTags(new String[] { });
		setOfficialRatings(new String[] { });
		setYears(new int[] { });
	}
}