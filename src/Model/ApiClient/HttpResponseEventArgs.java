package MediaBrowser.Model.ApiClient;

/** 
 Class HttpResponseEventArgs
*/
public class HttpResponseEventArgs extends EventArgs
{
	/** 
	 Gets or sets the URL.
	 
	 <value>The URL.</value>
	*/
	private String privateUrl;
	public final String getUrl()
	{
		return privateUrl;
	}
	public final void setUrl(String value)
	{
		privateUrl = value;
	}
	/** 
	 Gets or sets the status code.
	 
	 <value>The status code.</value>
	*/
	private HttpStatusCode privateStatusCode = HttpStatusCode.values()[0];
	public final HttpStatusCode getStatusCode()
	{
		return privateStatusCode;
	}
	public final void setStatusCode(HttpStatusCode value)
	{
		privateStatusCode = value;
	}
}