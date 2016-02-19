package mediabrowser.model.connect;

public final class ConnectPassword
{
	public static String PerformPreHashFilter(String password)
	{
		return password.replace("&", "&amp;").replace("/", "&#092;").replace("!", "&#33;").replace("$", "&#036;").replace("\"", "&quot;").replace("<", "&lt;").replace(">", "&gt;").replace("'", "&#39;");
	}
}