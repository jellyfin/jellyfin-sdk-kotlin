package org.jellyfin.apiclient.model.updates;

/** 
 Enum PackageVersionClass
*/
public enum PackageVersionClass
{
	/** 
	 The release
	*/
	Release(0),
	/** 
	 The beta
	*/
	Beta(1),
	/** 
	 The dev
	*/
	Dev(2);

	private int intValue;
	private static java.util.HashMap<Integer, PackageVersionClass> mappings;
	private static java.util.HashMap<Integer, PackageVersionClass> getMappings()
	{
		if (mappings == null)
		{
			synchronized (PackageVersionClass.class)
			{
				if (mappings == null)
				{
					mappings = new java.util.HashMap<Integer, PackageVersionClass>();
				}
			}
		}
		return mappings;
	}

	private PackageVersionClass(int value)
	{
		intValue = value;
		getMappings().put(value, this);
	}

	public int getValue()
	{
		return intValue;
	}

	public static PackageVersionClass forValue(int value)
	{
		return getMappings().get(value);
	}
}