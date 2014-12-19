package mediabrowser.model.sync;

public class SyncDialogOptions
{
	/** 
	 Gets or sets the targets.
	 
	 <value>The targets.</value>
	*/
	private java.util.ArrayList<SyncTarget> Targets;
	public final java.util.ArrayList<SyncTarget> getTargets()
	{
		return Targets;
	}
	public final void setTargets(java.util.ArrayList<SyncTarget> value)
	{
		Targets = value;
	}
	/** 
	 Gets or sets the options.
	 
	 <value>The options.</value>
	*/
	private java.util.ArrayList<SyncOptions> Options;
	public final java.util.ArrayList<SyncOptions> getOptions()
	{
		return Options;
	}
	public final void setOptions(java.util.ArrayList<SyncOptions> value)
	{
		Options = value;
	}

	public SyncDialogOptions()
	{
		setTargets(new java.util.ArrayList<SyncTarget>());
		setOptions(new java.util.ArrayList<SyncOptions>());
	}
}