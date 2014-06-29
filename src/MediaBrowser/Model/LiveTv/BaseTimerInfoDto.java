package MediaBrowser.Model.LiveTv;

import MediaBrowser.Model.Extensions.*;

public class BaseTimerInfoDto implements IHasPropertyChangedEvent
{
	/** 
	 Occurs when a property value changes.
	*/
//C# TO JAVA CONVERTER TODO TASK: Events are not available in Java:
//	public event PropertyChangedEventHandler PropertyChanged;

	/** 
	 Id of the recording.
	*/
	private String privateId;
	public final String getId()
	{
		return privateId;
	}
	public final void setId(String value)
	{
		privateId = value;
	}

	/** 
	 Gets or sets the external identifier.
	 
	 <value>The external identifier.</value>
	*/
	private String privateExternalId;
	public final String getExternalId()
	{
		return privateExternalId;
	}
	public final void setExternalId(String value)
	{
		privateExternalId = value;
	}

	/** 
	 ChannelId of the recording.
	*/
	private String privateChannelId;
	public final String getChannelId()
	{
		return privateChannelId;
	}
	public final void setChannelId(String value)
	{
		privateChannelId = value;
	}

	/** 
	 Gets or sets the external channel identifier.
	 
	 <value>The external channel identifier.</value>
	*/
	private String privateExternalChannelId;
	public final String getExternalChannelId()
	{
		return privateExternalChannelId;
	}
	public final void setExternalChannelId(String value)
	{
		privateExternalChannelId = value;
	}

	/** 
	 ChannelName of the recording.
	*/
	private String privateChannelName;
	public final String getChannelName()
	{
		return privateChannelName;
	}
	public final void setChannelName(String value)
	{
		privateChannelName = value;
	}

	/** 
	 Gets or sets the program identifier.
	 
	 <value>The program identifier.</value>
	*/
	private String privateProgramId;
	public final String getProgramId()
	{
		return privateProgramId;
	}
	public final void setProgramId(String value)
	{
		privateProgramId = value;
	}

	/** 
	 Gets or sets the external program identifier.
	 
	 <value>The external program identifier.</value>
	*/
	private String privateExternalProgramId;
	public final String getExternalProgramId()
	{
		return privateExternalProgramId;
	}
	public final void setExternalProgramId(String value)
	{
		privateExternalProgramId = value;
	}

	/** 
	 Name of the recording.
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
	 Description of the recording.
	*/
	private String privateOverview;
	public final String getOverview()
	{
		return privateOverview;
	}
	public final void setOverview(String value)
	{
		privateOverview = value;
	}

	/** 
	 The start date of the recording, in UTC.
	*/
	private java.util.Date privateStartDate = new java.util.Date(0);
	public final java.util.Date getStartDate()
	{
		return privateStartDate;
	}
	public final void setStartDate(java.util.Date value)
	{
		privateStartDate = value;
	}

	/** 
	 The end date of the recording, in UTC.
	*/
	private java.util.Date privateEndDate = new java.util.Date(0);
	public final java.util.Date getEndDate()
	{
		return privateEndDate;
	}
	public final void setEndDate(java.util.Date value)
	{
		privateEndDate = value;
	}

	/** 
	 Gets or sets the name of the service.
	 
	 <value>The name of the service.</value>
	*/
	private String privateServiceName;
	public final String getServiceName()
	{
		return privateServiceName;
	}
	public final void setServiceName(String value)
	{
		privateServiceName = value;
	}

	/** 
	 Gets or sets the priority.
	 
	 <value>The priority.</value>
	*/
	private int privatePriority;
	public final int getPriority()
	{
		return privatePriority;
	}
	public final void setPriority(int value)
	{
		privatePriority = value;
	}

	/** 
	 Gets or sets the pre padding seconds.
	 
	 <value>The pre padding seconds.</value>
	*/
	private int privatePrePaddingSeconds;
	public final int getPrePaddingSeconds()
	{
		return privatePrePaddingSeconds;
	}
	public final void setPrePaddingSeconds(int value)
	{
		privatePrePaddingSeconds = value;
	}

	/** 
	 Gets or sets the post padding seconds.
	 
	 <value>The post padding seconds.</value>
	*/
	private int privatePostPaddingSeconds;
	public final int getPostPaddingSeconds()
	{
		return privatePostPaddingSeconds;
	}
	public final void setPostPaddingSeconds(int value)
	{
		privatePostPaddingSeconds = value;
	}

	/** 
	 Gets or sets a value indicating whether this instance is pre padding required.
	 
	 <value><c>true</c> if this instance is pre padding required; otherwise, <c>false</c>.</value>
	*/
	private boolean privateIsPrePaddingRequired;
	public final boolean getIsPrePaddingRequired()
	{
		return privateIsPrePaddingRequired;
	}
	public final void setIsPrePaddingRequired(boolean value)
	{
		privateIsPrePaddingRequired = value;
	}

	/** 
	 Gets or sets a value indicating whether this instance is post padding required.
	 
	 <value><c>true</c> if this instance is post padding required; otherwise, <c>false</c>.</value>
	*/
	private boolean privateIsPostPaddingRequired;
	public final boolean getIsPostPaddingRequired()
	{
		return privateIsPostPaddingRequired;
	}
	public final void setIsPostPaddingRequired(boolean value)
	{
		privateIsPostPaddingRequired = value;
	}
}