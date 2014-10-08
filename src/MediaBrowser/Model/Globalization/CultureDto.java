package MediaBrowser.Model.Globalization;

/** 
 Class CultureDto
*/
public class CultureDto
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
	 Gets or sets the name of the two letter ISO language.
	 
	 <value>The name of the two letter ISO language.</value>
	*/
	private String TwoLetterISOLanguageName;
	public final String getTwoLetterISOLanguageName()
	{
		return TwoLetterISOLanguageName;
	}
	public final void setTwoLetterISOLanguageName(String value)
	{
		TwoLetterISOLanguageName = value;
	}

	/** 
	 Gets or sets the name of the three letter ISO language.
	 
	 <value>The name of the three letter ISO language.</value>
	*/
	private String ThreeLetterISOLanguageName;
	public final String getThreeLetterISOLanguageName()
	{
		return ThreeLetterISOLanguageName;
	}
	public final void setThreeLetterISOLanguageName(String value)
	{
		ThreeLetterISOLanguageName = value;
	}
}