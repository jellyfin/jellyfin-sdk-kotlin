package org.jellyfin.apiclient.model.sync;

import org.jellyfin.apiclient.model.dto.*;

public class SyncJobItem
{
	/** 
	 Gets or sets the identifier.
	 
	 <value>The identifier.</value>
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

	/** 
	 Gets or sets the job identifier.
	 
	 <value>The job identifier.</value>
	*/
	private String JobId;
	public final String getJobId()
	{
		return JobId;
	}
	public final void setJobId(String value)
	{
		JobId = value;
	}

	/** 
	 Gets or sets the item identifier.
	 
	 <value>The item identifier.</value>
	*/
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
	 Gets or sets the name of the item.
	 
	 <value>The name of the item.</value>
	*/
	private String ItemName;
	public final String getItemName()
	{
		return ItemName;
	}
	public final void setItemName(String value)
	{
		ItemName = value;
	}

	/** 
	 Gets or sets the media source identifier.
	 
	 <value>The media source identifier.</value>
	*/
	private String MediaSourceId;
	public final String getMediaSourceId()
	{
		return MediaSourceId;
	}
	public final void setMediaSourceId(String value)
	{
		MediaSourceId = value;
	}

	/** 
	 Gets or sets the media source.
	 
	 <value>The media source.</value>
	*/
	private MediaSourceInfo MediaSource;
	public final MediaSourceInfo getMediaSource()
	{
		return MediaSource;
	}
	public final void setMediaSource(MediaSourceInfo value)
	{
		MediaSource = value;
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
	 Gets or sets the output path.
	 
	 <value>The output path.</value>
	*/
	private String OutputPath;
	public final String getOutputPath()
	{
		return OutputPath;
	}
	public final void setOutputPath(String value)
	{
		OutputPath = value;
	}

	/** 
	 Gets or sets the status.
	 
	 <value>The status.</value>
	*/
	private SyncJobItemStatus Status = SyncJobItemStatus.values()[0];
	public final SyncJobItemStatus getStatus()
	{
		return Status;
	}
	public final void setStatus(SyncJobItemStatus value)
	{
		Status = value;
	}

	/** 
	 Gets or sets the current progress.
	 
	 <value>The current progress.</value>
	*/
	private Double Progress = null;
	public final Double getProgress()
	{
		return Progress;
	}
	public final void setProgress(Double value)
	{
		Progress = value;
	}

	/** 
	 Gets or sets the date created.
	 
	 <value>The date created.</value>
	*/
	private java.util.Date DateCreated = new java.util.Date(0);
	public final java.util.Date getDateCreated()
	{
		return DateCreated;
	}
	public final void setDateCreated(java.util.Date value)
	{
		DateCreated = value;
	}
	/** 
	 Gets or sets the primary image item identifier.
	 
	 <value>The primary image item identifier.</value>
	*/
	private String PrimaryImageItemId;
	public final String getPrimaryImageItemId()
	{
		return PrimaryImageItemId;
	}
	public final void setPrimaryImageItemId(String value)
	{
		PrimaryImageItemId = value;
	}
	/** 
	 Gets or sets the primary image tag.
	 
	 <value>The primary image tag.</value>
	*/
	private String PrimaryImageTag;
	public final String getPrimaryImageTag()
	{
		return PrimaryImageTag;
	}
	public final void setPrimaryImageTag(String value)
	{
		PrimaryImageTag = value;
	}
	/** 
	 Gets or sets the temporary path.
	 
	 <value>The temporary path.</value>
	*/
	private String TemporaryPath;
	public final String getTemporaryPath()
	{
		return TemporaryPath;
	}
	public final void setTemporaryPath(String value)
	{
		TemporaryPath = value;
	}
	/** 
	 Gets or sets the additional files.
	 
	 <value>The additional files.</value>
	*/
	private java.util.ArrayList<ItemFileInfo> AdditionalFiles;
	public final java.util.ArrayList<ItemFileInfo> getAdditionalFiles()
	{
		return AdditionalFiles;
	}
	public final void setAdditionalFiles(java.util.ArrayList<ItemFileInfo> value)
	{
		AdditionalFiles = value;
	}
	/** 
	 Gets or sets a value indicating whether this instance is marked for removal.
	 
	 <value><c>true</c> if this instance is marked for removal; otherwise, <c>false</c>.</value>
	*/
	private boolean IsMarkedForRemoval;
	public final boolean getIsMarkedForRemoval()
	{
		return IsMarkedForRemoval;
	}
	public final void setIsMarkedForRemoval(boolean value)
	{
		IsMarkedForRemoval = value;
	}
	/** 
	 Gets or sets the index of the job item.
	 
	 <value>The index of the job item.</value>
	*/
	private int JobItemIndex;
	public final int getJobItemIndex()
	{
		return JobItemIndex;
	}
	public final void setJobItemIndex(int value)
	{
		JobItemIndex = value;
	}

	private long ItemDateModifiedTicks;
	public final long getItemDateModifiedTicks()
	{
		return ItemDateModifiedTicks;
	}
	public final void setItemDateModifiedTicks(long value)
	{
		ItemDateModifiedTicks = value;
	}

	public SyncJobItem()
	{
		setAdditionalFiles(new java.util.ArrayList<ItemFileInfo>());
	}
}