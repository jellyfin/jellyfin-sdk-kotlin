package MediaBrowser.Model.Dlna;

public class ResolutionConfiguration
{
	private int MaxWidth;
	public final int getMaxWidth()
	{
		return MaxWidth;
	}
	public final void setMaxWidth(int value)
	{
		MaxWidth = value;
	}
	private int MaxBitrate;
	public final int getMaxBitrate()
	{
		return MaxBitrate;
	}
	public final void setMaxBitrate(int value)
	{
		MaxBitrate = value;
	}

	public ResolutionConfiguration(int maxWidth, int maxBitrate)
	{
		setMaxWidth(maxWidth);
		setMaxBitrate(maxBitrate);
	}
}