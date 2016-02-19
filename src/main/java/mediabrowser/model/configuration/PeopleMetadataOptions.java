package mediabrowser.model.configuration;

public class PeopleMetadataOptions
{
	private boolean DownloadActorMetadata;
	public final boolean getDownloadActorMetadata()
	{
		return DownloadActorMetadata;
	}
	public final void setDownloadActorMetadata(boolean value)
	{
		DownloadActorMetadata = value;
	}
	private boolean DownloadDirectorMetadata;
	public final boolean getDownloadDirectorMetadata()
	{
		return DownloadDirectorMetadata;
	}
	public final void setDownloadDirectorMetadata(boolean value)
	{
		DownloadDirectorMetadata = value;
	}
	private boolean DownloadProducerMetadata;
	public final boolean getDownloadProducerMetadata()
	{
		return DownloadProducerMetadata;
	}
	public final void setDownloadProducerMetadata(boolean value)
	{
		DownloadProducerMetadata = value;
	}
	private boolean DownloadWriterMetadata;
	public final boolean getDownloadWriterMetadata()
	{
		return DownloadWriterMetadata;
	}
	public final void setDownloadWriterMetadata(boolean value)
	{
		DownloadWriterMetadata = value;
	}
	private boolean DownloadComposerMetadata;
	public final boolean getDownloadComposerMetadata()
	{
		return DownloadComposerMetadata;
	}
	public final void setDownloadComposerMetadata(boolean value)
	{
		DownloadComposerMetadata = value;
	}
	private boolean DownloadOtherPeopleMetadata;
	public final boolean getDownloadOtherPeopleMetadata()
	{
		return DownloadOtherPeopleMetadata;
	}
	public final void setDownloadOtherPeopleMetadata(boolean value)
	{
		DownloadOtherPeopleMetadata = value;
	}
	private boolean DownloadGuestStarMetadata;
	public final boolean getDownloadGuestStarMetadata()
	{
		return DownloadGuestStarMetadata;
	}
	public final void setDownloadGuestStarMetadata(boolean value)
	{
		DownloadGuestStarMetadata = value;
	}

	public PeopleMetadataOptions()
	{
		setDownloadActorMetadata(true);
		setDownloadDirectorMetadata(true);
	}
}