package org.jellyfin.apiclient.interaction.http;

import org.jellyfin.apiclient.interaction.QueryStringDictionary;

public class HttpRequest
{
    private String Method;
    public final String getMethod()
    {
        return Method;
    }
    public final void setMethod(String value)
    {
        Method = value;
    }
    private String RequestContent;
    public final String getRequestContent()
    {
        return RequestContent;
    }
    public final void setRequestContent(String value)
    {
        RequestContent = value;
    }
    private String RequestContentType;
    public final String getRequestContentType()
    {
        return RequestContentType;
    }
    public final void setRequestContentType(String value)
    {
        RequestContentType = value;
    }
    private HttpHeaders RequestHeaders;
    public final HttpHeaders getRequestHeaders()
    {
        return RequestHeaders;
    }
    public final void setRequestHeaders(HttpHeaders value)
    {
        RequestHeaders = value;
    }
    private String Url;
    public final String getUrl()
    {
        return Url;
    }
    public final void setUrl(String value)
    {
        Url = value;
    }

    private int Timeout;
    public final int getTimeout()
    {
        return Timeout;
    }
    public final void setTimeout(int value)
    {
        Timeout = value;
    }

    private QueryStringDictionary postData;
    public final QueryStringDictionary getPostData()
    {
        return postData;
    }
    public final void setPostData(QueryStringDictionary value)
    {
        postData = value;
    }

    private boolean EnableCaching;
    public final boolean getEnableCaching()
    {
        return EnableCaching;
    }
    public final void setEnableCaching(boolean value)
    {
        EnableCaching = value;
    }

    public HttpRequest()
    {
        setRequestHeaders(new HttpHeaders());
        setTimeout(30000);
    }
}
