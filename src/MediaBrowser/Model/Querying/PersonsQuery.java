package MediaBrowser.Model.Querying;

/** 
 Class PersonsQuery
*/
public class PersonsQuery extends ItemsByNameQuery
{
	/** 
	 Gets or sets the person types.
	 
	 <value>The person types.</value>
	*/
	private String[] PersonTypes;
	public final String[] getPersonTypes()
	{
		return PersonTypes;
	}
	public final void setPersonTypes(String[] value)
	{
		PersonTypes = value;
	}

	/** 
	 Initializes a new instance of the <see cref="PersonsQuery"/> class.
	*/
	public PersonsQuery()
	{
		setPersonTypes(new String[] { });
	}
}