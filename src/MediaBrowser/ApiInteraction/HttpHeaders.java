package MediaBrowser.ApiInteraction;

public class HttpHeaders extends java.util.HashMap<String,String>
{
    /**
     Gets or sets the authorization scheme.

     <value>The authorization scheme.</value>
     */
    private String privateAuthorizationScheme;
    public final String getAuthorizationScheme()
    {
        return privateAuthorizationScheme;
    }
    public final void setAuthorizationScheme(String value)
    {
        privateAuthorizationScheme = value;
    }
    /**
     Gets or sets the authorization parameter.

     <value>The authorization parameter.</value>
     */
    private String privateAuthorizationParameter;
    public final String getAuthorizationParameter()
    {
        return privateAuthorizationParameter;
    }
    public final void setAuthorizationParameter(String value)
    {
        privateAuthorizationParameter = value;
    }

    /**
     Sets the access token.

     @param token The token.
     */
    public final void SetAccessToken(String token)
    {
        if (tangible.DotNetToJavaStringHelper.isNullOrEmpty(token))
        {
            this.remove("X-MediaBrowser-Token");
        }
        else
        {
            this.put("X-MediaBrowser-Token", token);
        }
    }
}
