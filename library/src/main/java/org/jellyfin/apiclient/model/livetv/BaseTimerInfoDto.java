package org.jellyfin.apiclient.model.livetv;

import org.jellyfin.apiclient.model.dto.*;

public class BaseTimerInfoDto implements IHasServerId
{
	/** 
	 Id of the recording.
	*/
	private String Id;
	public final String getId()
	{
		return Id;
	}
	public final void setId(String value)
	{
		Id = value;
	}

	private String Type;
	public final String getType()
	{
		return Type;
	}
	public final void setType(String value)
	{
		Type = value;
	}

	/** 
	 Gets or sets the server identifier.
	 
	 <value>The server identifier.</value>
	*/
	private String ServerId;
	public final String getServerId()
	{
		return ServerId;
	}
	public final void setServerId(String value)
	{
		ServerId = value;
	}

	/** 
	 Gets or sets the external identifier.
	 
	 <value>The external identifier.</value>
	*/
	private String ExternalId;
	public final String getExternalId()
	{
		return ExternalId;
	}
	public final void setExternalId(String value)
	{
		ExternalId = value;
	}

	/** 
	 ChannelId of the recording.
	*/
	private String ChannelId;
	public final String getChannelId()
	{
		return ChannelId;
	}
	public final void setChannelId(String value)
	{
		ChannelId = value;
	}

	/** 
	 Gets or sets the external channel identifier.
	 
	 <value>The external channel identifier.</value>
	*/
	private String ExternalChannelId;
	public final String getExternalChannelId()
	{
		return ExternalChannelId;
	}
	public final void setExternalChannelId(String value)
	{
		ExternalChannelId = value;
	}

	/** 
	 ChannelName of the recording.
	*/
	private String ChannelName;
	public final String getChannelName()
	{
		return ChannelName;
	}
	public final void setChannelName(String value)
	{
		ChannelName = value;
	}

	/** 
	 Gets or sets the program identifier.
	 
	 <value>The program identifier.</value>
	*/
	private String ProgramId;
	public final String getProgramId()
	{
		return ProgramId;
	}
	public final void setProgramId(String value)
	{
		ProgramId = value;
	}

	/** 
	 Gets or sets the external program identifier.
	 
	 <value>The external program identifier.</value>
	*/
	private String ExternalProgramId;
	public final String getExternalProgramId()
	{
		return ExternalProgramId;
	}
	public final void setExternalProgramId(String value)
	{
		ExternalProgramId = value;
	}

	/** 
	 Name of the recording.
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
	 Description of the recording.
	*/
	private String Overview;
	public final String getOverview()
	{
		return Overview;
	}
	public final void setOverview(String value)
	{
		Overview = value;
	}

	/** 
	 The start date of the recording, in UTC.
	*/
	private java.util.Date StartDate = new java.util.Date(0);
	public final java.util.Date getStartDate()
	{
		return StartDate;
	}
	public final void setStartDate(java.util.Date value)
	{
		StartDate = value;
	}

	/** 
	 The end date of the recording, in UTC.
	*/
	private java.util.Date EndDate = new java.util.Date(0);
	public final java.util.Date getEndDate()
	{
		return EndDate;
	}
	public final void setEndDate(java.util.Date value)
	{
		EndDate = value;
	}

	/** 
	 Gets or sets the name of the service.
	 
	 <value>The name of the service.</value>
	*/
	private String ServiceName;
	public final String getServiceName()
	{
		return ServiceName;
	}
	public final void setServiceName(String value)
	{
		ServiceName = value;
	}

	/** 
	 Gets or sets the priority.
	 
	 <value>The priority.</value>
	*/
	private int Priority;
	public final int getPriority()
	{
		return Priority;
	}
	public final void setPriority(int value)
	{
		Priority = value;
	}

	/** 
	 Gets or sets the pre padding seconds.
	 
	 <value>The pre padding seconds.</value>
	*/
	private int PrePaddingSeconds;
	public final int getPrePaddingSeconds()
	{
		return PrePaddingSeconds;
	}
	public final void setPrePaddingSeconds(int value)
	{
		PrePaddingSeconds = value;
	}

	/** 
	 Gets or sets the post padding seconds.
	 
	 <value>The post padding seconds.</value>
	*/
	private int PostPaddingSeconds;
	public final int getPostPaddingSeconds()
	{
		return PostPaddingSeconds;
	}
	public final void setPostPaddingSeconds(int value)
	{
		PostPaddingSeconds = value;
	}

	/** 
	 Gets or sets a value indicating whether this instance is pre padding required.
	 
	 <value><c>true</c> if this instance is pre padding required; otherwise, <c>false</c>.</value>
	*/
	private boolean IsPrePaddingRequired;
	public final boolean getIsPrePaddingRequired()
	{
		return IsPrePaddingRequired;
	}
	public final void setIsPrePaddingRequired(boolean value)
	{
		IsPrePaddingRequired = value;
	}

	/** 
	 If the item does not have any backdrops, this will hold the Id of the Parent that has one.
	 
	 <value>The parent backdrop item id.</value>
	*/
	private String ParentBackdropItemId;
	public final String getParentBackdropItemId()
	{
		return ParentBackdropItemId;
	}
	public final void setParentBackdropItemId(String value)
	{
		ParentBackdropItemId = value;
	}

	/** 
	 Gets or sets the parent backdrop image tags.
	 
	 <value>The parent backdrop image tags.</value>
	*/
	private java.util.ArrayList<String> ParentBackdropImageTags;
	public final java.util.ArrayList<String> getParentBackdropImageTags()
	{
		return ParentBackdropImageTags;
	}
	public final void setParentBackdropImageTags(java.util.ArrayList<String> value)
	{
		ParentBackdropImageTags = value;
	}

	/** 
	 Gets or sets a value indicating whether this instance is post padding required.
	 
	 <value><c>true</c> if this instance is post padding required; otherwise, <c>false</c>.</value>
	*/
	private boolean IsPostPaddingRequired;
	public final boolean getIsPostPaddingRequired()
	{
		return IsPostPaddingRequired;
	}
	public final void setIsPostPaddingRequired(boolean value)
	{
		IsPostPaddingRequired = value;
	}
	private KeepUntil KeepUntil = getKeepUntil().values()[0];
	public final KeepUntil getKeepUntil()
	{
		return KeepUntil;
	}
	public final void setKeepUntil(KeepUntil value)
	{
		KeepUntil = value;
	}
}