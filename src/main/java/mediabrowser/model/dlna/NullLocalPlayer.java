package mediabrowser.model.dlna;

public class NullLocalPlayer implements ILocalPlayer
{
	public final boolean CanAccessFile(String path)
	{
		return false;
	}

	public final boolean CanAccessDirectory(String path)
	{
		return false;
	}

	public final boolean CanAccessUrl(String url, boolean requiresCustomRequestHeaders)
	{
		return false;
	}
}