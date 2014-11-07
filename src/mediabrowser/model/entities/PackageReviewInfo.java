package mediabrowser.model.entities;

public class PackageReviewInfo
{
	/** 
	 The package id (database key) for this review
	*/
	private int id;
	public final int getid()
	{
		return id;
	}
	public final void setid(int value)
	{
		id = value;
	}

	/** 
	 The rating value
	*/
	private int rating;
	public final int getrating()
	{
		return rating;
	}
	public final void setrating(int value)
	{
		rating = value;
	}

	/** 
	 Whether or not this review recommends this item
	*/
	private boolean recommend;
	public final boolean getrecommend()
	{
		return recommend;
	}
	public final void setrecommend(boolean value)
	{
		recommend = value;
	}

	/** 
	 A short description of the review
	*/
	private String title;
	public final String gettitle()
	{
		return title;
	}
	public final void settitle(String value)
	{
		title = value;
	}

	/** 
	 A full review
	*/
	private String review;
	public final String getreview()
	{
		return review;
	}
	public final void setreview(String value)
	{
		review = value;
	}

	/** 
	 Time of review
	*/
	private java.util.Date timestamp = new java.util.Date(0);
	public final java.util.Date gettimestamp()
	{
		return timestamp;
	}
	public final void settimestamp(java.util.Date value)
	{
		timestamp = value;
	}

}