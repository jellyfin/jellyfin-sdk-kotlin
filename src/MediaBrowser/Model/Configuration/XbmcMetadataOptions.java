package MediaBrowser.Model.Configuration;

public class XbmcMetadataOptions
{
	private String privateUserId;
	public final String getUserId()
	{
		return privateUserId;
	}
	public final void setUserId(String value)
	{
		privateUserId = value;
	}

	private String privateReleaseDateFormat;
	public final String getReleaseDateFormat()
	{
		return privateReleaseDateFormat;
	}
	public final void setReleaseDateFormat(String value)
	{
		privateReleaseDateFormat = value;
	}

	private boolean privateSaveImagePathsInNfo;
	public final boolean getSaveImagePathsInNfo()
	{
		return privateSaveImagePathsInNfo;
	}
	public final void setSaveImagePathsInNfo(boolean value)
	{
		privateSaveImagePathsInNfo = value;
	}
	private boolean privateEnablePathSubstitution;
	public final boolean getEnablePathSubstitution()
	{
		return privateEnablePathSubstitution;
	}
	public final void setEnablePathSubstitution(boolean value)
	{
		privateEnablePathSubstitution = value;
	}

	public XbmcMetadataOptions()
	{
		setReleaseDateFormat("yyyy-MM-dd");

		setSaveImagePathsInNfo(true);
		setEnablePathSubstitution(true);
	}
}