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
	private java.util.ArrayList<SyncJobOption> Options;
	public final java.util.ArrayList<SyncJobOption> getOptions()
	{
		return Options;
	}
	public final void setOptions(java.util.ArrayList<SyncJobOption> value)
	{
		Options = value;
	}
	/** 
	 Gets or sets the quality options.
	 
	 <value>The quality options.</value>
	*/
	private java.util.ArrayList<SyncQualityOption> QualityOptions;
	public final java.util.ArrayList<SyncQualityOption> getQualityOptions()
	{
		return QualityOptions;
	}
	public final void setQualityOptions(java.util.ArrayList<SyncQualityOption> value)
	{
		QualityOptions = value;
	}
	/** 
	 Gets or sets the profile options.
	 
	 <value>The profile options.</value>
	*/
	private java.util.ArrayList<SyncProfileOption> ProfileOptions;
	public final java.util.ArrayList<SyncProfileOption> getProfileOptions()
	{
		return ProfileOptions;
	}
	public final void setProfileOptions(java.util.ArrayList<SyncProfileOption> value)
	{
		ProfileOptions = value;
	}

	public SyncDialogOptions()
	{
		setTargets(new java.util.ArrayList<SyncTarget>());
		setOptions(new java.util.ArrayList<SyncJobOption>());
		setQualityOptions(new java.util.ArrayList<SyncQualityOption>());
		setProfileOptions(new java.util.ArrayList<SyncProfileOption>());
	}
}