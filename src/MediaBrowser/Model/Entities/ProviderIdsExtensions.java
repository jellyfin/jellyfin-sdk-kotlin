package MediaBrowser.Model.Entities;

/** 
 Class ProviderIdsExtensions
*/
public final class ProviderIdsExtensions
{
	/** 
	 Determines whether [has provider identifier] [the specified instance].
	 
	 @param instance The instance.
	 @param provider The provider.
	 @return <c>true</c> if [has provider identifier] [the specified instance]; otherwise, <c>false</c>.
	*/
	public static boolean HasProviderId(IHasProviderIds instance, MetadataProviders provider)
	{
		return !tangible.DotNetToJavaStringHelper.isNullOrEmpty(MediaBrowser.Model.Entities.ProviderIdsExtensions.GetProviderId(instanceprovider.toString()));
	}

	/** 
	 Gets a provider id
	 
	 @param instance The instance.
	 @param provider The provider.
	 @return System.String.
	*/
	public static String GetProviderId(IHasProviderIds instance, MetadataProviders provider)
	{
		return MediaBrowser.Model.Entities.ProviderIdsExtensions.GetProviderId(instanceprovider.toString());
	}

	/** 
	 Gets a provider id
	 
	 @param instance The instance.
	 @param name The name.
	 @return System.String.
	*/
	public static String GetProviderId(IHasProviderIds instance, String name)
	{
		if (instance == null)
		{
			throw new IllegalArgumentException("instance");
		}

		if (instance.getProviderIds() == null)
		{
			return null;
		}

		String id = null;
		id = instance.getProviderIds().get(name);
		return id;
	}

	/** 
	 Sets a provider id
	 
	 @param instance The instance.
	 @param name The name.
	 @param value The value.
	*/
	public static void SetProviderId(IHasProviderIds instance, String name, String value)
	{
		if (instance == null)
		{
			throw new IllegalArgumentException("instance");
		}

		// If it's null remove the key from the dictionary
		if (tangible.DotNetToJavaStringHelper.isNullOrEmpty(value))
		{
			if (instance.getProviderIds() != null)
			{
				if (instance.getProviderIds().containsKey(name))
				{
					instance.getProviderIds().remove(name);
				}
			}
		}
		else
		{
			// Ensure it exists
			if (instance.getProviderIds() == null)
			{
				instance.setProviderIds(new java.util.HashMap<String, String>(StringComparer.OrdinalIgnoreCase));
			}

			instance.getProviderIds().put(name, value);
		}
	}

	/** 
	 Sets a provider id
	 
	 @param instance The instance.
	 @param provider The provider.
	 @param value The value.
	*/
	public static void SetProviderId(IHasProviderIds instance, MetadataProviders provider, String value)
	{
		MediaBrowser.Model.Entities.ProviderIdsExtensions.SetProviderId(instanceprovider.toString(), value);
	}
}