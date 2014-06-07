package MediaBrowser.Model.Globalization;

/** 
 Class CountryInfo
*/
public class CountryInfo
{
	/** 
	 Gets or sets the name.
	 
	 <value>The name.</value>
	*/
	private String privateName;
	public final String getName()
	{
		return privateName;
	}
	public final void setName(String value)
	{
		privateName = value;
	}

	/** 
	 Gets or sets the display name.
	 
	 <value>The display name.</value>
	*/
	private String privateDisplayName;
	public final String getDisplayName()
	{
		return privateDisplayName;
	}
	public final void setDisplayName(String value)
	{
		privateDisplayName = value;
	}

	/** 
	 Gets or sets the name of the two letter ISO region.
	 
	 <value>The name of the two letter ISO region.</value>
	*/
	private String privateTwoLetterISORegionName;
	public final String getTwoLetterISORegionName()
	{
		return privateTwoLetterISORegionName;
	}
	public final void setTwoLetterISORegionName(String value)
	{
		privateTwoLetterISORegionName = value;
	}

	/** 
	 Gets or sets the name of the three letter ISO region.
	 
	 <value>The name of the three letter ISO region.</value>
	*/
	private String privateThreeLetterISORegionName;
	public final String getThreeLetterISORegionName()
	{
		return privateThreeLetterISORegionName;
	}
	public final void setThreeLetterISORegionName(String value)
	{
		privateThreeLetterISORegionName = value;
	}
}