package MediaBrowser.Model.Extensions;

public final class ListHelper
{
	public static boolean ContainsIgnoreCase(Iterable<String> list, String value)
	{
		if (value == null)
		{
			throw new IllegalArgumentException("value");
		}

		return list.Contains(value, StringComparer.OrdinalIgnoreCase);
	}
}