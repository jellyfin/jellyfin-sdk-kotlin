package MediaBrowser.Model.Entities;

public class PackageReviewInfo
{
	/** 
	 The package id (database key) for this review
	*/
	private int privateid;
	public final int getid()
	{
		return privateid;
	}
	public final void setid(int value)
	{
		privateid = value;
	}

	/** 
	 The rating value
	*/
	private int privaterating;
	public final int getrating()
	{
		return privaterating;
	}
	public final void setrating(int value)
	{
		privaterating = value;
	}

	/** 
	 Whether or not this review recommends this item
	*/
	private boolean privaterecommend;
	public final boolean getrecommend()
	{
		return privaterecommend;
	}
	public final void setrecommend(boolean value)
	{
		privaterecommend = value;
	}

	/** 
	 A short description of the review
	*/
	private String privatetitle;
	public final String gettitle()
	{
		return privatetitle;
	}
	public final void settitle(String value)
	{
		privatetitle = value;
	}

	/** 
	 A full review
	*/
	private String privatereview;
	public final String getreview()
	{
		return privatereview;
	}
	public final void setreview(String value)
	{
		privatereview = value;
	}

	/** 
	 Time of review
	*/
	private java.util.Date privatetimestamp = new java.util.Date(0);
	public final java.util.Date gettimestamp()
	{
		return privatetimestamp;
	}
	public final void settimestamp(java.util.Date value)
	{
		privatetimestamp = value;
	}

}