package mediabrowser.model.sync;

import mediabrowser.model.dto.*;

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
	private java.util.ArrayList<NameValuePair> QualityOptions;
	public final java.util.ArrayList<NameValuePair> getQualityOptions()
	{
		return QualityOptions;
	}
	public final void setQualityOptions(java.util.ArrayList<NameValuePair> value)
	{
		QualityOptions = value;
	}

	public SyncDialogOptions()
	{
		setTargets(new java.util.ArrayList<SyncTarget>());
		setOptions(new java.util.ArrayList<SyncJobOption>());
		NameValuePair tempVar = new NameValuePair();
		tempVar.setName(SyncQuality.Original.toString());
		tempVar.setValue(SyncQuality.Original.toString());
		NameValuePair tempVar2 = new NameValuePair();
		tempVar2.setName(SyncQuality.High.toString());
		tempVar2.setValue(SyncQuality.High.toString());
		NameValuePair tempVar3 = new NameValuePair();
		tempVar3.setName(SyncQuality.Medium.toString());
		tempVar3.setValue(SyncQuality.Medium.toString());
		NameValuePair tempVar4 = new NameValuePair();
		tempVar4.setName(SyncQuality.Low.toString());
		tempVar4.setValue(SyncQuality.Low.toString());
		setQualityOptions(new java.util.ArrayList<NameValuePair>(java.util.Arrays.asList(new NameValuePair[] {tempVar, tempVar2, tempVar3, tempVar4})));
	}
}