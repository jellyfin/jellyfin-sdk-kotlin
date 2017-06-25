package mediabrowser.model.globalization;

/** 
 Class CountryInfo
*/
public class CountryInfo
{
	/** 
	 Gets or sets the name.
	 
	 <value>The name.</value>
	*/
	private String Name;
	public final String getName()
	{
		return Name;
	}
	public final void setName(String value)
	{
		Name = value;
	}

	/** 
	 Gets or sets the display name.
	 
	 <value>The display name.</value>
	*/
	private String DisplayName;
	public final String getDisplayName()
	{
		return DisplayName;
	}
	public final void setDisplayName(String value)
	{
		DisplayName = value;
	}

	/** 
	 Gets or sets the name of the two letter ISO region.
	 
	 <value>The name of the two letter ISO region.</value>
	*/
	private String TwoLetterISORegionName;
	public final String getTwoLetterISORegionName()
	{
		return TwoLetterISORegionName;
	}
	public final void setTwoLetterISORegionName(String value)
	{
		TwoLetterISORegionName = value;
	}

	/** 
	 Gets or sets the name of the three letter ISO region.
	 
	 <value>The name of the three letter ISO region.</value>
	*/
	private String ThreeLetterISORegionName;
	public final String getThreeLetterISORegionName()
	{
		return ThreeLetterISORegionName;
	}
	public final void setThreeLetterISORegionName(String value)
	{
		ThreeLetterISORegionName = value;
	}
}