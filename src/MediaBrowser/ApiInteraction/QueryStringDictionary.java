package MediaBrowser.ApiInteraction;

import tangible.*;

/** 
 Class QueryStringDictionary
*/
public class QueryStringDictionary extends java.util.HashMap<String, String>
{
	/** 
	 Initializes a new instance of the <see cref="QueryStringDictionary" /> class.
	*/
	public QueryStringDictionary()
	{
		super();
	}

	/** 
	 Adds the specified name.
	 
	 @param name The name.
	 @param value The value.
	*/
	public final void Add(String name, int value)
	{
		this.put(name, (new Integer(value)).toString());
	}

    /**
     Adds the specified name.

     @param name The name.
     @param value The value.
     */
    public final void Add(String name, String value)
    {
        this.put(name, (new Integer(value)).toString());
    }

	/** 
	 Adds the specified name.
	 
	 @param name The name.
	 @param value The value.
	*/
	public final void Add(String name, long value)
	{
		this.put(name, (new Long(value)).toString());
	}

	/** 
	 Adds the specified name.
	 
	 @param name The name.
	 @param value The value.
	*/
	public final void Add(String name, double value)
	{
		this.put(name, (new Double(value)).toString());
	}

	/** 
	 Adds if not null or empty.
	 
	 @param name The name.
	 @param value The value.
	*/
	public final void AddIfNotNullOrEmpty(String name, String value)
	{
		if (!tangible.DotNetToJavaStringHelper.isNullOrEmpty(value))
		{
			this.put(name, value);
		}
	}

	/** 
	 Adds if not null.
	 
	 @param name The name.
	 @param value The value.
	*/
	public final void AddIfNotNull(String name, Integer value)
	{
		if (value != null)
		{
			this.Add(name, value);
		}
	}

	/** 
	 Adds if not null.
	 
	 @param name The name.
	 @param value The value.
	*/
	public final void AddIfNotNull(String name, Double value)
	{
		if (value != null)
		{
			this.Add(name, value);
		}
	}

	/** 
	 Adds if not null.
	 
	 @param name The name.
	 @param value The value.
	*/
	public final void AddIfNotNull(String name, Long value)
	{
		if (value != null)
		{
			this.Add(name, value);
		}
	}

	/** 
	 Adds the specified name.
	 
	 @param name The name.
	 @param value if set to <c>true</c> [value].
	*/
	public final void Add(String name, boolean value)
	{
		this.put(name, (new Boolean(value)).toString());
	}

	/** 
	 Adds if not null.
	 
	 @param name The name.
	 @param value if set to <c>true</c> [value].
	*/
	public final void AddIfNotNull(String name, Boolean value)
	{
		if (value != null)
		{
			this.Add(name, value);
		}
	}

	/** 
	 Adds the specified name.
	 
	 @param name The name.
	 @param value The value.
	 @exception System.ArgumentNullException value
	*/
	public final void Add(String name, Integer[] value)
	{
		if (value == null)
		{
			throw new IllegalArgumentException("value");
		}

        String attValue = tangible.DotNetToJavaStringHelper.join(",", GetStrings(value));

        this.put(name, attValue);
	}

    private String[] GetStrings(Integer[] value)
    {
        String[] vals = new String[value.length];

        for (int i=0; i< value.length; i++) {
            vals[i] = value[i].toString();
        }

        return vals;
    }

    public final void Add(String name, int[] value)
    {
        if (value == null)
        {
            throw new IllegalArgumentException("value");
        }

        String attValue = tangible.DotNetToJavaStringHelper.join(",", GetStrings(value));

        this.put(name, attValue);
    }

    private String[] GetStrings(int[] value)
    {
        String[] vals = new String[value.length];

        for (int i=0; i< value.length; i++) {
            vals[i] = new Integer(value[i]).toString();
        }

        return vals;
    }

    /**
     Adds if not null.

     @param name The name.
     @param value The value.
     */
    public final void AddIfNotNull(String name, int[] value)
    {
        if (value != null)
        {
            this.Add(name, value);
        }
    }

	/** 
	 Adds if not null.
	 
	 @param name The name.
	 @param value The value.
	*/
	public final void AddIfNotNull(String name, Integer[] value)
	{
		if (value != null)
		{
			this.Add(name, value);
		}
	}

	/** 
	 Adds the specified name.
	 
	 @param name The name.
	 @param value The value.
	 @exception System.ArgumentNullException value
	*/
	public final void Add(String name, String[] value)
	{
		if (value == null)
		{
			throw new IllegalArgumentException("value");
		}

		Add(name, value, ",");
	}

	/** 
	 Adds if not null.
	 
	 @param name The name.
	 @param value The value.
	*/
	public final void AddIfNotNull(String name, String[] value)
	{
		if (value != null)
		{
			this.Add(name, value);
		}
	}

	/** 
	 Adds the specified name.
	 
	 @param name The name.
	 @param value The value.
	 @param delimiter The delimiter.
	 @exception ArgumentNullException value
	*/
	public final void Add(String name, String[] value, String delimiter)
	{
		if (value == null)
		{
			throw new IllegalArgumentException("value");
		}

		String paramValue = tangible.DotNetToJavaStringHelper.join(delimiter, value);

		this.put(name, paramValue);
	}

	/** 
	 Adds if not null.
	 
	 @param name The name.
	 @param value The value.
	 @param delimiter The delimiter.
	*/
	public final void AddIfNotNull(String name, String[] value, String delimiter)
	{
		if (value != null)
		{
			this.Add(name, value, delimiter);
		}
	}

	/** 
	 Gets the query string.
	 
	 @return System.String.
	*/
	public final String GetQueryString()
	{
        int size = this.size();
        String[] vals = new String[size];

        int index = 0;
        for(String key : this.keySet())
        {
            String paramValue = this.get(key);
            vals[index] = String.format("%1$s=%2$s", key, GetEncodedValue(paramValue));
            index++;
        }

        return tangible.DotNetToJavaStringHelper.join("&", vals);
	}

	/** 
	 Gets the encoded value.
	 
	 @param value The value.
	 @return System.String.
	*/
	private String GetEncodedValue(String value)
	{
		return value;
	}

	/** 
	 Gets the URL.
	 
	 @param prefix The prefix.
	 @return System.String.
	*/
	public final String GetUrl(String prefix)
	{
		String query = GetQueryString();

		if (tangible.DotNetToJavaStringHelper.isNullOrEmpty(query))
		{
			return prefix;
		}

		return prefix + "?" + query;
	}

    public final <T extends Enum<T>> void Add(String name, T value)
    {
        this.Add(name, value.toString());
    }

    public final <T extends Enum<T>> void AddIfNotNull(String name, T value)
    {
        if (value != null)
        {
            this.Add(name, value);
        }
    }

    public final <T extends Enum<T>> void Add(String name, T[] values, String delimiter)
    {
        this.Add(name, GetStrings(values), delimiter);
    }

    public final <T extends Enum<T>> void AddIfNotNull(String name, T[] values, String delimiter)
    {
        if (values != null)
        {
            this.Add(name, values, delimiter);
        }
    }

    public final <T extends Enum<T>> void Add(String name, T[] values)
    {
        this.Add(name, values, ",");
    }

    public final <T extends Enum<T>> void AddIfNotNull(String name, T[] values)
    {
        this.AddIfNotNull(name, values, ",");
    }

    private <T extends Enum<T>> String[] GetStrings(T[] value)
    {
        String[] vals = new String[value.length];

        for (int i=0; i< value.length; i++) {
            vals[i] = value[i].toString();
        }

        return vals;
    }
}