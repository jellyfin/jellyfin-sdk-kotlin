package mediabrowser.model.apiclient;

public final class ApiHelpers
{
	/** 
	 Gets the name of the slug.
	 
	 @param name The name.
	 @return System.String.
	*/
	public static String GetSlugName(String name)
	{
		if (tangible.DotNetToJavaStringHelper.isNullOrEmpty(name))
		{
			throw new IllegalArgumentException("name");
		}

		return name.replace('/', '-').replace('?', '-').replace('&', '-');
	}
}