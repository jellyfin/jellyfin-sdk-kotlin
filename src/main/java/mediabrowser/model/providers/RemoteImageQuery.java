package mediabrowser.model.providers;

import mediabrowser.model.entities.*;

public class RemoteImageQuery
{
	private String ProviderName;
	public final String getProviderName()
	{
		return ProviderName;
	}
	public final void setProviderName(String value)
	{
		ProviderName = value;
	}

	private ImageType ImageType;
	public final ImageType getImageType()
	{
		return ImageType;
	}
	public final void setImageType(ImageType value)
	{
		ImageType = value;
	}

	private boolean IncludeDisabledProviders;
	public final boolean getIncludeDisabledProviders()
	{
		return IncludeDisabledProviders;
	}
	public final void setIncludeDisabledProviders(boolean value)
	{
		IncludeDisabledProviders = value;
	}

	private boolean IncludeAllLanguages;
	public final boolean getIncludeAllLanguages()
	{
		return IncludeAllLanguages;
	}
	public final void setIncludeAllLanguages(boolean value)
	{
		IncludeAllLanguages = value;
	}
}