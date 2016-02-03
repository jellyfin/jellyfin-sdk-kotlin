package mediabrowser.model.dto;

import mediabrowser.model.sync.*;

public interface IHasSyncInfo
{
	String getId();
	Boolean getSupportsSync();
	void setSupportsSync(Boolean value);
	Boolean getHasSyncJob();
	void setHasSyncJob(Boolean value);
	Boolean getIsSynced();
	void setIsSynced(Boolean value);
	SyncJobItemStatus getSyncStatus();
	void setSyncStatus(SyncJobItemStatus value);
}