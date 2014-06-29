package MediaBrowser.Model.Extensions;

public final class ListHelper
{
	public static boolean ContainsIgnoreCase(java.util.ArrayList<String> list, String value)
	{
		if (value == null)
		{
			throw new IllegalArgumentException("value");
		}

		return list.contains(value, StringComparer.OrdinalIgnoreCase);
	}
	public static boolean ContainsIgnoreCase(String[] list, String value)
	{
		if (value == null)
		{
			throw new IllegalArgumentException("value");
		}

		return list.Contains(value, StringComparer.OrdinalIgnoreCase);
	}
}