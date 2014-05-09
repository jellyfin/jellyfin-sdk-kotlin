package MediaBrowser.Model.Entities;

/** 
 Class ParentalRating
*/
public class ParentalRating
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
	 Gets or sets the value.
	 
	 <value>The value.</value>
	*/
	private int privateValue;
	public final int getValue()
	{
		return privateValue;
	}
	public final void setValue(int value)
	{
		privateValue = value;
	}
}