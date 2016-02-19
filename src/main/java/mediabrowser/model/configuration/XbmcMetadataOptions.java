package mediabrowser.model.configuration;

public class XbmcMetadataOptions
{
	private String UserId;
	public final String getUserId()
	{
		return UserId;
	}
	public final void setUserId(String value)
	{
		UserId = value;
	}

	private String ReleaseDateFormat;
	public final String getReleaseDateFormat()
	{
		return ReleaseDateFormat;
	}
	public final void setReleaseDateFormat(String value)
	{
		ReleaseDateFormat = value;
	}

	private boolean SaveImagePathsInNfo;
	public final boolean getSaveImagePathsInNfo()
	{
		return SaveImagePathsInNfo;
	}
	public final void setSaveImagePathsInNfo(boolean value)
	{
		SaveImagePathsInNfo = value;
	}
	private boolean EnablePathSubstitution;
	public final boolean getEnablePathSubstitution()
	{
		return EnablePathSubstitution;
	}
	public final void setEnablePathSubstitution(boolean value)
	{
		EnablePathSubstitution = value;
	}

	private boolean EnableExtraThumbsDuplication;
	public final boolean getEnableExtraThumbsDuplication()
	{
		return EnableExtraThumbsDuplication;
	}
	public final void setEnableExtraThumbsDuplication(boolean value)
	{
		EnableExtraThumbsDuplication = value;
	}

	public XbmcMetadataOptions()
	{
		setReleaseDateFormat("yyyy-MM-dd");

		setSaveImagePathsInNfo(true);
		setEnablePathSubstitution(true);
		setEnableExtraThumbsDuplication(true);
	}
}