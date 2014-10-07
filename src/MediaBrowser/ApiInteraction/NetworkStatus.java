package MediaBrowser.ApiInteraction;

public class NetworkStatus {

    private boolean isNetworkAvailable;
    public final boolean getIsNetworkAvailable()
    {
        return isNetworkAvailable;
    }
    public final void setIsNetworkAvailable(boolean value)
    {
        isNetworkAvailable = value;
    }

    private Boolean isLocalNetworkAvailable = null;
    public final Boolean getIsLocalNetworkAvailable()
    {
        return isLocalNetworkAvailable;
    }
    public final void setIsLocalNetworkAvailable(Boolean value)
    {
        isLocalNetworkAvailable = value;
    }

    private Boolean isRemoteNetworkAvailable = null;
    public final Boolean getIsRemoteNetworkAvailable()
    {
        return isRemoteNetworkAvailable;
    }
    public final void setIsRemoteNetworkAvailable(Boolean value)
    {
        isRemoteNetworkAvailable = value;
    }

    public boolean GetIsLocalNetworkAvailable()
    {
       return isLocalNetworkAvailable == null ?
                isNetworkAvailable :
                isLocalNetworkAvailable;
    }

    public boolean GetIsRemoteNetworkAvailable()
    {
        return isRemoteNetworkAvailable == null ?
                isNetworkAvailable :
                isRemoteNetworkAvailable;
    }
}
