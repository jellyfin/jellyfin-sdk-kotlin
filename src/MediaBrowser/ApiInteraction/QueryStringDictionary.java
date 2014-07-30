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
		super(StringComparer.OrdinalIgnoreCase);
	}

	/** 
	 Adds the specified name.
	 
	 @param name The name.
	 @param value The value.
	*/
	public final void Add(String name, int value)
	{
		this.put(name, (new Integer(value)).toString(CultureInfo.InvariantCulture));
	}

	/** 
	 Adds the specified name.
	 
	 @param name The name.
	 @param value The value.
	*/
	public final void Add(String name, long value)
	{
		this.put(name, (new Long(value)).toString(CultureInfo.InvariantCulture));
	}

	/** 
	 Adds the specified name.
	 
	 @param name The name.
	 @param value The value.
	*/
	public final void Add(String name, double value)
	{
		this.put(name, (new Double(value)).toString(CultureInfo.InvariantCulture));
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
			this.put(name, value);
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
			this.put(name, value);
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
			this.put(name, value);
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
			this.put(name, value);
		}
	}

	/** 
	 Adds the specified name.
	 
	 @param name The name.
	 @param value The value.
	 @exception System.ArgumentNullException value
	*/
	public final void Add(String name, Iterable<Integer> value)
	{
		if (value == null)
		{
			throw new IllegalArgumentException("value");
		}

		this.put(name, tangible.DotNetToJavaStringHelper.join(",", value.Select(v => v.toString(CultureInfo.InvariantCulture)).ToArray()));
	}

	/** 
	 Adds if not null.
	 
	 @param name The name.
	 @param value The value.
	*/
	public final void AddIfNotNull(String name, Iterable<Integer> value)
	{
		if (value != null)
		{
			this.put(name, value);
		}
	}

	/** 
	 Adds the specified name.
	 
	 @param name The name.
	 @param value The value.
	 @exception System.ArgumentNullException value
	*/
	public final void Add(String name, Iterable<String> value)
	{
		if (value == null)
		{
			throw new IllegalArgumentException("value");
		}

		String paramValue = tangible.DotNetToJavaStringHelper.join(",", value.ToArray());

		this.put(name, paramValue);
	}

	/** 
	 Adds if not null.
	 
	 @param name The name.
	 @param value The value.
	*/
	public final void AddIfNotNull(String name, Iterable<String> value)
	{
		if (value != null)
		{
			this.put(name, value);
		}
	}

	/** 
	 Adds the specified name.
	 
	 @param name The name.
	 @param value The value.
	 @param delimiter The delimiter.
	 @exception ArgumentNullException value
	*/
	public final void Add(String name, Iterable<String> value, String delimiter)
	{
		if (value == null)
		{
			throw new IllegalArgumentException("value");
		}

		String paramValue = tangible.DotNetToJavaStringHelper.join(delimiter, value.ToArray());

		this.put(name, paramValue);
	}

	/** 
	 Adds if not null.
	 
	 @param name The name.
	 @param value The value.
	 @param delimiter The delimiter.
	*/
	public final void AddIfNotNull(String name, Iterable<String> value, String delimiter)
	{
		if (value != null)
		{
			this.put(name, value, delimiter);
		}
	}

	/** 
	 Gets the query string.
	 
	 @return System.String.
	*/
	public final String GetQueryString()
	{
		String[] queryParams = this.Select(i => String.format("%1$s=%2$s", i.Key, GetEncodedValue(i.Value))).ToArray();

		return tangible.DotNetToJavaStringHelper.join("&", queryParams);
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
}