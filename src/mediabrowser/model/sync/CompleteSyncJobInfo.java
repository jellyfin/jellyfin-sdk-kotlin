package mediabrowser.model.sync;

public class CompleteSyncJobInfo
{
	private SyncJob Job;
	public final SyncJob getJob()
	{
		return Job;
	}
	public final void setJob(SyncJob value)
	{
		Job = value;
	}
	private java.util.ArrayList<SyncJobItem> JobItems;
	public final java.util.ArrayList<SyncJobItem> getJobItems()
	{
		return JobItems;
	}
	public final void setJobItems(java.util.ArrayList<SyncJobItem> value)
	{
		JobItems = value;
	}

	public CompleteSyncJobInfo()
	{
		setJobItems(new java.util.ArrayList<SyncJobItem>());
	}
}