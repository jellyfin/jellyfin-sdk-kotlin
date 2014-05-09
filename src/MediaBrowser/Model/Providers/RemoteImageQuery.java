package MediaBrowser.Model.Providers;

import MediaBrowser.Model.Entities.*;

public class RemoteImageQuery
{
	private String privateProviderName;
	public final String getProviderName()
	{
		return privateProviderName;
	}
	public final void setProviderName(String value)
	{
		privateProviderName = value;
	}

	private ImageType privateImageType;
	public final ImageType getImageType()
	{
		return privateImageType;
	}
	public final void setImageType(ImageType value)
	{
		privateImageType = value;
	}

	private boolean privateIncludeDisabledProviders;
	public final boolean getIncludeDisabledProviders()
	{
		return privateIncludeDisabledProviders;
	}
	public final void setIncludeDisabledProviders(boolean value)
	{
		privateIncludeDisabledProviders = value;
	}

	private boolean privateIncludeAllLanguages;
	public final boolean getIncludeAllLanguages()
	{
		return privateIncludeAllLanguages;
	}
	public final void setIncludeAllLanguages(boolean value)
	{
		privateIncludeAllLanguages = value;
	}
}