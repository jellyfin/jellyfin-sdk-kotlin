package mediabrowser.model.dto;

/** 
 Class GameSystemSummary
*/
public class GameSystemSummary
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
	 Gets or sets the name.
	 
	 <value>The name.</value>
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
	 Gets or sets the game count.
	 
	 <value>The game count.</value>
	*/
	private int GameCount;
	public final int getGameCount()
	{
		return GameCount;
	}
	public final void setGameCount(int value)
	{
		GameCount = value;
	}

	/** 
	 Gets or sets the game extensions.
	 
	 <value>The game extensions.</value>
	*/
	private java.util.ArrayList<String> GameFileExtensions;
	public final java.util.ArrayList<String> getGameFileExtensions()
	{
		return GameFileExtensions;
	}
	public final void setGameFileExtensions(java.util.ArrayList<String> value)
	{
		GameFileExtensions = value;
	}

	/** 
	 Gets or sets the client installed game count.
	 
	 <value>The client installed game count.</value>
	*/
	private int ClientInstalledGameCount;
	public final int getClientInstalledGameCount()
	{
		return ClientInstalledGameCount;
	}
	public final void setClientInstalledGameCount(int value)
	{
		ClientInstalledGameCount = value;
	}

	/** 
	 Initializes a new instance of the <see cref="GameSystemSummary"/> class.
	*/
	public GameSystemSummary()
	{
		setGameFileExtensions(new java.util.ArrayList<String>());
	}
}