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
	 Gets or sets the name of the two letter ISO language.
	 
	 <value>The name of the two letter ISO language.</value>
	*/
	private String privateTwoLetterISOLanguageName;
	public final String getTwoLetterISOLanguageName()
	{
		return privateTwoLetterISOLanguageName;
	}
	public final void setTwoLetterISOLanguageName(String value)
	{
		privateTwoLetterISOLanguageName = value;
	}

	/** 
	 Gets or sets the name of the three letter ISO language.
	 
	 <value>The name of the three letter ISO language.</value>
	*/
	private String privateThreeLetterISOLanguageName;
	public final String getThreeLetterISOLanguageName()
	{
		return privateThreeLetterISOLanguageName;
	}
	public final void setThreeLetterISOLanguageName(String value)
	{
		privateThreeLetterISOLanguageName = value;
	}
}