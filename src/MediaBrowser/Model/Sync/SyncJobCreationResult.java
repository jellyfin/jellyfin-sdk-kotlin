package MediaBrowser.Model.Sync;

public class SyncJobCreationResult
{
	private SyncJob privateJob;
	public final SyncJob getJob()
	{
		return privateJob;
	}
	public final void setJob(SyncJob value)
	{
		privateJob = value;
	}
}