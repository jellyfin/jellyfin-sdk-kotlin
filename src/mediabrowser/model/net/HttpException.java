package mediabrowser.model.net;

/** 
 Class HttpException
*/
public class HttpException extends RuntimeException
{
	/** 
	 Gets or sets the status code.
	 
	 <value>The status code.</value>
	*/
	private HttpStatusCode StatusCode = null;
	public final HttpStatusCode getStatusCode()
	{
		return StatusCode;
	}
	public final void setStatusCode(HttpStatusCode value)
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

	/** 
	 Initializes a new instance of the <see cref="HttpException" /> class.
	 
	 @param message The message.
	 @param innerException The inner exception.
	*/
	public HttpException(String message, RuntimeException innerException)
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