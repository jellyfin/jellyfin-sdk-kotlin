package org.jellyfin.apiclient.model.net;

public class EndPointInfo
{
	private boolean IsLocal;
	public final boolean getIsLocal()
	{
		return IsLocal;
	}
	public final void setIsLocal(boolean value)
	{
		IsLocal = value;
	}
	private boolean IsInNetwork;
	public final boolean getIsInNetwork()
	{
		return IsInNetwork;
	}
	public final void setIsInNetwork(boolean value)
	{
		IsInNetwork = value;
	}
}