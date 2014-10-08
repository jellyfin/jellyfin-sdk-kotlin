package MediaBrowser.Model.ApiClient;

import MediaBrowser.Model.Session.*;

/** 
 Class SystemCommandEventArgs
*/
public class GeneralCommandEventArgs
{
	/** 
	 Gets or sets the command.
	 
	 <value>The command.</value>
	*/
	private GeneralCommand Command;
	public final GeneralCommand getCommand()
	{
		return Command;
	}
	public final void setCommand(GeneralCommand value)
	{
		Command = value;
	}

	/** 
	 Gets or sets the type of the known command.
	 
	 <value>The type of the known command.</value>
	*/
	private GeneralCommandType KnownCommandType;
	public final GeneralCommandType getKnownCommandType()
	{
		return KnownCommandType;
	}
	public final void setKnownCommandType(GeneralCommandType value)
	{
		KnownCommandType = value;
	}
}