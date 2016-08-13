package mediabrowser.model.livetv;

import mediabrowser.model.dto.*;
import mediabrowser.model.extensions.*;

public class ListingsProviderInfo
{
	private String Id;
	public final String getId()
	{
		return Id;
	}
	public final void setId(String value)
	{
		Id = value;
	}
	private String Type;
	public final String getType()
	{
		return Type;
	}
	public final void setType(String value)
	{
		Type = value;
	}
	private String Username;
	public final String getUsername()
	{
		return Username;
	}
	public final void setUsername(String value)
	{
		Username = value;
	}
	private String Password;
	public final String getPassword()
	{
		return Password;
	}
	public final void setPassword(String value)
	{
		Password = value;
	}
	private String ListingsId;
	public final String getListingsId()
	{
		return ListingsId;
	}
	public final void setListingsId(String value)
	{
		ListingsId = value;
	}
	private String ZipCode;
	public final String getZipCode()
	{
		return ZipCode;
	}
	public final void setZipCode(String value)
	{
		ZipCode = value;
	}
	private String Country;
	public final String getCountry()
	{
		return Country;
	}
	public final void setCountry(String value)
	{
		Country = value;
	}
	private String Path;
	public final String getPath()
	{
		return Path;
	}
	public final void setPath(String value)
	{
		Path = value;
	}

	private String[] EnabledTuners;
	public final String[] getEnabledTuners()
	{
		return EnabledTuners;
	}
	public final void setEnabledTuners(String[] value)
	{
		EnabledTuners = value;
	}
	private boolean EnableAllTuners;
	public final boolean getEnableAllTuners()
	{
		return EnableAllTuners;
	}
	public final void setEnableAllTuners(boolean value)
	{
		EnableAllTuners = value;
	}
	private String[] NewsCategories;
	public final String[] getNewsCategories()
	{
		return NewsCategories;
	}
	public final void setNewsCategories(String[] value)
	{
		NewsCategories = value;
	}
	private String[] SportsCategories;
	public final String[] getSportsCategories()
	{
		return SportsCategories;
	}
	public final void setSportsCategories(String[] value)
	{
		SportsCategories = value;
	}
	private String[] KidsCategories;
	public final String[] getKidsCategories()
	{
		return KidsCategories;
	}
	public final void setKidsCategories(String[] value)
	{
		KidsCategories = value;
	}
	private String[] MovieCategories;
	public final String[] getMovieCategories()
	{
		return MovieCategories;
	}
	public final void setMovieCategories(String[] value)
	{
		MovieCategories = value;
	}
	private NameValuePair[] ChannelMappings;
	public final NameValuePair[] getChannelMappings()
	{
		return ChannelMappings;
	}
	public final void setChannelMappings(NameValuePair[] value)
	{
		ChannelMappings = value;
	}

	public ListingsProviderInfo()
	{
		setNewsCategories(new String[] {"news", "journalism", "documentary", "current affairs"});
		setSportsCategories(new String[] {"sports", "basketball", "baseball", "football"});
		setKidsCategories(new String[] {"kids", "family", "children", "childrens", "disney"});
		setMovieCategories(new String[] {"movie"});
		setEnabledTuners(new String[] { });
		setEnableAllTuners(true);
		setChannelMappings(new NameValuePair[] {});
	}

	public final String GetMappedChannel(String channelNumber)
	{
		for (NameValuePair mapping : getChannelMappings())
		{
			if (StringHelper.EqualsIgnoreCase(mapping.getName(), channelNumber))
			{
				return mapping.getValue();
			}
		}
		return channelNumber;
	}
}