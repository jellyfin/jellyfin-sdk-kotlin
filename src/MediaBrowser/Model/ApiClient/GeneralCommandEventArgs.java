package MediaBrowser.Model.ApiClient;

import MediaBrowser.Model.Session.*;

/** 
 Class SystemCommandEventArgs
*/
public class GeneralCommandEventArgs extends EventArgs
{
	/** 
	 Gets or sets the command.
	 
	 <value>The command.</value>
	*/
	private GeneralCommand privateCommand;
	public final GeneralCommand getCommand()
	{
		return privateCommand;
	}
	public final void setCommand(GeneralCommand value)
	{
		privateCommand = value;
	}

	/** 
	 Gets or sets the type of the known command.
	 
	 <value>The type of the known command.</value>
	*/
	private GeneralCommandType privateKnownCommandType = new GeneralCommandType();
	public final GeneralCommandType getKnownCommandType()
	{
		return privateKnownCommandType;
	}
	public final void setKnownCommandType(GeneralCommandType value)
	{
		privateKnownCommandType = value;
	}
}