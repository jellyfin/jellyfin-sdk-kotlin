package MediaBrowser.Model.Configuration;

import MediaBrowser.Model.Entities.*;
import MediaBrowser.Model.FileOrganization.*;
import MediaBrowser.Model.LiveTv.*;

public class PeopleMetadataOptions
{
	private boolean privateDownloadActorMetadata;
	public final boolean getDownloadActorMetadata()
	{
		return privateDownloadActorMetadata;
	}
	public final void setDownloadActorMetadata(boolean value)
	{
		privateDownloadActorMetadata = value;
	}
	private boolean privateDownloadDirectorMetadata;
	public final boolean getDownloadDirectorMetadata()
	{
		return privateDownloadDirectorMetadata;
	}
	public final void setDownloadDirectorMetadata(boolean value)
	{
		privateDownloadDirectorMetadata = value;
	}
	private boolean privateDownloadProducerMetadata;
	public final boolean getDownloadProducerMetadata()
	{
		return privateDownloadProducerMetadata;
	}
	public final void setDownloadProducerMetadata(boolean value)
	{
		privateDownloadProducerMetadata = value;
	}
	private boolean privateDownloadWriterMetadata;
	public final boolean getDownloadWriterMetadata()
	{
		return privateDownloadWriterMetadata;
	}
	public final void setDownloadWriterMetadata(boolean value)
	{
		privateDownloadWriterMetadata = value;
	}
	private boolean privateDownloadComposerMetadata;
	public final boolean getDownloadComposerMetadata()
	{
		return privateDownloadComposerMetadata;
	}
	public final void setDownloadComposerMetadata(boolean value)
	{
		privateDownloadComposerMetadata = value;
	}
	private boolean privateDownloadOtherPeopleMetadata;
	public final boolean getDownloadOtherPeopleMetadata()
	{
		return privateDownloadOtherPeopleMetadata;
	}
	public final void setDownloadOtherPeopleMetadata(boolean value)
	{
		privateDownloadOtherPeopleMetadata = value;
	}
	private boolean privateDownloadGuestStarMetadata;
	public final boolean getDownloadGuestStarMetadata()
	{
		return privateDownloadGuestStarMetadata;
	}
	public final void setDownloadGuestStarMetadata(boolean value)
	{
		privateDownloadGuestStarMetadata = value;
	}

	public PeopleMetadataOptions()
	{
		setDownloadActorMetadata(true);
		setDownloadDirectorMetadata(true);
	}
}