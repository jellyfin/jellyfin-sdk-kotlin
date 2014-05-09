package MediaBrowser.Model.Dto;

/** 
 Class GameSystemSummary
*/
public class GameSystemSummary
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
	 Gets or sets the name.
	 
	 <value>The name.</value>
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
	 Gets or sets the game count.
	 
	 <value>The game count.</value>
	*/
	private int privateGameCount;
	public final int getGameCount()
	{
		return privateGameCount;
	}
	public final void setGameCount(int value)
	{
		privateGameCount = value;
	}

	/** 
	 Gets or sets the game extensions.
	 
	 <value>The game extensions.</value>
	*/
	private java.util.ArrayList<String> privateGameFileExtensions;
	public final java.util.ArrayList<String> getGameFileExtensions()
	{
		return privateGameFileExtensions;
	}
	public final void setGameFileExtensions(java.util.ArrayList<String> value)
	{
		privateGameFileExtensions = value;
	}

	/** 
	 Gets or sets the client installed game count.
	 
	 <value>The client installed game count.</value>
	*/
	private int privateClientInstalledGameCount;
	public final int getClientInstalledGameCount()
	{
		return privateClientInstalledGameCount;
	}
	public final void setClientInstalledGameCount(int value)
	{
		privateClientInstalledGameCount = value;
	}

	/** 
	 Initializes a new instance of the <see cref="GameSystemSummary"/> class.
	*/
	public GameSystemSummary()
	{
		setGameFileExtensions(new java.util.ArrayList<String>());
	}
}