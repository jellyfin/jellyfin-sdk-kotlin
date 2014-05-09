package MediaBrowser.Model.Entities;

/** 
 Class ItemReview
*/
public class ItemReview
{
	/** 
	 Gets or sets the name of the reviewer.
	 
	 <value>The name of the reviewer.</value>
	*/
	private String privateReviewerName;
	public final String getReviewerName()
	{
		return privateReviewerName;
	}
	public final void setReviewerName(String value)
	{
		privateReviewerName = value;
	}

	/** 
	 Gets or sets the publisher.
	 
	 <value>The publisher.</value>
	*/
	private String privatePublisher;
	public final String getPublisher()
	{
		return privatePublisher;
	}
	public final void setPublisher(String value)
	{
		privatePublisher = value;
	}

	/** 
	 Gets or sets the date.
	 
	 <value>The date.</value>
	*/
	private java.util.Date privateDate = new java.util.Date(0);
	public final java.util.Date getDate()
	{
		return privateDate;
	}
	public final void setDate(java.util.Date value)
	{
		privateDate = value;
	}

	/** 
	 Gets or sets the score.
	 
	 <value>The score.</value>
	*/
	private Float privateScore = new Float();
	public final Float getScore()
	{
		return privateScore;
	}
	public final void setScore(Float value)
	{
		privateScore = value;
	}

	/** 
	 Gets or sets a value indicating whether this <see cref="ItemReview"/> is likes.
	 
	 <value><c>null</c> if [likes] contains no value, <c>true</c> if [likes]; otherwise, <c>false</c>.</value>
	*/
	private Boolean privateLikes = new Boolean();
	public final Boolean getLikes()
	{
		return privateLikes;
	}
	public final void setLikes(Boolean value)
	{
		privateLikes = value;
	}

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
	 Gets or sets the caption.
	 
	 <value>The caption.</value>
	*/
	private String privateCaption;
	public final String getCaption()
	{
		return privateCaption;
	}
	public final void setCaption(String value)
	{
		privateCaption = value;
	}
}