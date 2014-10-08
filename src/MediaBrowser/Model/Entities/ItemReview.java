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
	private String ReviewerName;
	public final String getReviewerName()
	{
		return ReviewerName;
	}
	public final void setReviewerName(String value)
	{
		ReviewerName = value;
	}

	/** 
	 Gets or sets the publisher.
	 
	 <value>The publisher.</value>
	*/
	private String Publisher;
	public final String getPublisher()
	{
		return Publisher;
	}
	public final void setPublisher(String value)
	{
		Publisher = value;
	}

	/** 
	 Gets or sets the date.
	 
	 <value>The date.</value>
	*/
	private java.util.Date Date = new java.util.Date(0);
	public final java.util.Date getDate()
	{
		return Date;
	}
	public final void setDate(java.util.Date value)
	{
		Date = value;
	}

	/** 
	 Gets or sets the score.
	 
	 <value>The score.</value>
	*/
	private Float Score = null;
	public final Float getScore()
	{
		return Score;
	}
	public final void setScore(Float value)
	{
		Score = value;
	}

	/** 
	 Gets or sets a value indicating whether this <see cref="ItemReview"/> is likes.
	 
	 <value><c>null</c> if [likes] contains no value, <c>true</c> if [likes]; otherwise, <c>false</c>.</value>
	*/
	private Boolean Likes = null;
	public final Boolean getLikes()
	{
		return Likes;
	}
	public final void setLikes(Boolean value)
	{
		Likes = value;
	}

	/** 
	 Gets or sets the URL.
	 
	 <value>The URL.</value>
	*/
	private String Url;
	public final String getUrl()
	{
		return Url;
	}
	public final void setUrl(String value)
	{
		Url = value;
	}

	/** 
	 Gets or sets the caption.
	 
	 <value>The caption.</value>
	*/
	private String Caption;
	public final String getCaption()
	{
		return Caption;
	}
	public final void setCaption(String value)
	{
		Caption = value;
	}
}