package MediaBrowser.Model.Sync;

public class SyncJobCreationResult
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
}