package mediabrowser.model.entities;

/** 
 Class ParentalRating
*/
public class ParentalRating
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
	 Gets or sets the value.
	 
	 <value>The value.</value>
	*/
	private int Value;
	public final int getValue()
	{
		return Value;
	}
	public final void setValue(int value)
	{
		Value = value;
	}
}