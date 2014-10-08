package MediaBrowser.Model.Dlna;

public class ResolutionConfiguration
{
	private int privateMaxWidth;
	public final int getMaxWidth()
	{
		return privateMaxWidth;
	}
	public final void setMaxWidth(int value)
	{
		privateMaxWidth = value;
	}
	private int privateMaxBitrate;
	public final int getMaxBitrate()
	{
		return privateMaxBitrate;
	}
	public final void setMaxBitrate(int value)
	{
		privateMaxBitrate = value;
	}

	public ResolutionConfiguration(int maxWidth, int maxBitrate)
	{
		setMaxWidth(maxWidth);
		setMaxBitrate(maxBitrate);
	}
}