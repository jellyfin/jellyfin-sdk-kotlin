package MediaBrowser.Model.Net;

import java.util.Map;

/**
 Class HttpException
*/
public class HttpException extends RuntimeException
{
	/** 
	 Gets or sets the status code.
	 
	 <value>The status code.</value>
	*/
	private Integer StatusCode = null;
	public final Integer getStatusCode()
	{
		return StatusCode;
	}
	public final void setStatusCode(Integer value)
	{
		StatusCode = value;
	}

	/** 
	 Gets or sets a value indicating whether this instance is timed out.
	 
	 <value><c>true</c> if this instance is timed out; otherwise, <c>false</c>.</value>
	*/
	private boolean IsTimedOut;
	public final boolean getIsTimedOut()
	{
		return IsTimedOut;
	}
	public final void setIsTimedOut(boolean value)
	{
		IsTimedOut = value;
	}

    private Map<String,String> headers;
    public final Map<String,String> getHeaders()
    {
        return headers;
    }
    public final void setHeaders(Map<String,String> value)
    {
        headers = value;
    }

	/** 
	 Initializes a new instance of the <see cref="HttpException" /> class.
	 
	 @param message The message.
	 @param innerException The inner exception.
	*/
	public HttpException(String message, Exception innerException)
	{
		super(message, innerException);

	}

	/** 
	 Initializes a new instance of the <see cref="HttpException" /> class.
	 
	 @param message The message.
	*/
	public HttpException(String message)
	{
		super(message);
	}
}