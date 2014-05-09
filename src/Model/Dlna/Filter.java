package MediaBrowser.Model.Dlna;

public class Filter
{
	private java.util.ArrayList<String> _fields;
	private boolean _all;

	public Filter()
	{
		this("*");

	}

	public Filter(String filter)
	{
		_all = String.equals(filter, "*", StringComparison.OrdinalIgnoreCase);

		java.util.ArrayList<String> list = new java.util.ArrayList<String>();
		for (String s : ((filter != null) ? filter : "").split(new char[] {','}, StringSplitOptions.RemoveEmptyEntries))
		{
			list.add(s);
		}
		_fields = list;
	}

	public final boolean Contains(String field)
	{
		return _all || _fields.contains(field, StringComparer.OrdinalIgnoreCase);
	}
}