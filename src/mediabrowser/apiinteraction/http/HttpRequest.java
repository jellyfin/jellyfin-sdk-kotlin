package mediabrowser.apiinteraction.http;

import mediabrowser.apiinteraction.QueryStringDictionary;

public class HttpRequest
{
    private String privateMethod;
    public final String getMethod()
    {
        return privateMethod;
    }
    public final void setMethod(String value)
    {
        privateMethod = value;
    }
    private String privateRequestContent;
    public final String getRequestContent()
    {
        return privateRequestContent;
    }
    public final void setRequestContent(String value)
    {
        privateRequestContent = value;
    }
    private String privateRequestContentType;
    public final String getRequestContentType()
    {
        return privateRequestContentType;
    }
    public final void setRequestContentType(String value)
    {
        privateRequestContentType = value;
    }
    private HttpHeaders privateRequestHeaders;
    public final HttpHeaders getRequestHeaders()
    {
        return privateRequestHeaders;
    }
    public final void setRequestHeaders(HttpHeaders value)
    {
        privateRequestHeaders = value;
    }
    private String privateUrl;
    public final String getUrl()
    {
        return privateUrl;
    }
    public final void setUrl(String value)
    {
        privateUrl = value;
    }

    private int privateTimeout;
    public final int getTimeout()
    {
        return privateTimeout;
    }
    public final void setTimeout(int value)
    {
        privateTimeout = value;
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

    public HttpRequest()
    {
        setRequestHeaders(new HttpHeaders());
        setTimeout(20000);
    }
}
