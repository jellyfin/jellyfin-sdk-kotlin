package org.jellyfin.apiclient.model.sync;

public class SyncJobQuery
{
	/** 
	 Gets or sets the start index.
	 
	 <value>The start index.</value>
	*/
	private Integer StartIndex;
	public final Integer getStartIndex()
	{
		return StartIndex;
	}
	public final void setStartIndex(Integer value)
	{
		StartIndex = value;
	}
	/** 
	 Gets or sets the limit.
	 
	 <value>The limit.</value>
	*/
	private Integer Limit;
	public final Integer getLimit()
	{
		return Limit;
	}
	public final void setLimit(Integer value)
	{
		Limit = value;
	}
	/** 
	 Gets or sets the target identifier.
	 
	 <value>The target identifier.</value>
	*/
	private String TargetId;
	public final String getTargetId()
	{
		return TargetId;
	}
	public final void setTargetId(String value)
	{
		TargetId = value;
	}
	/** 
	 Gets or sets the user identifier.
	 
	 <value>The user identifier.</value>
	*/
	private String UserId;
	public final String getUserId()
	{
		return UserId;
	}
	public final void setUserId(String value)
	{
		UserId = value;
	}
	private String ExcludeTargetIds;
	public final String getExcludeTargetIds()
	{
		return ExcludeTargetIds;
	}
	public final void setExcludeTargetIds(String value)
	{
		ExcludeTargetIds = value;
	}
	private String ItemId;
	public final String getItemId()
	{
		return ItemId;
	}
	public final void setItemId(String value)
	{
		ItemId = value;
	}
	/** 
	 Gets or sets the status.
	 
	 <value>The status.</value>
	*/
	private SyncJobStatus[] Statuses;
	public final SyncJobStatus[] getStatuses()
	{
		return Statuses;
	}
	public final void setStatuses(SyncJobStatus[] value)
	{
		Statuses = value;
	}
	/** 
	 Gets or sets a value indicating whether [synchronize new content].
	 
	 <value><c>null</c> if [synchronize new content] contains no value, <c>true</c> if [synchronize new content]; otherwise, <c>false</c>.</value>
	*/
	private Boolean SyncNewContent;
	public final Boolean getSyncNewContent()
	{
		return SyncNewContent;
	}
	public final void setSyncNewContent(Boolean value)
	{
		SyncNewContent = value;
	}

	public SyncJobQuery()
	{
		setStatuses(new SyncJobStatus[] { });
	}
}