package MediaBrowser.Model.Updates;

/** 
 Class PackageInfo
*/
public class PackageInfo
{
	/** 
	 The internal id of this package.
	 
	 <value>The id.</value>
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
	 Gets or sets the name.
	 
	 <value>The name.</value>
	*/
	private String privatename;
	public final String getname()
	{
		return privatename;
	}
	public final void setname(String value)
	{
		privatename = value;
	}

	/** 
	 Gets or sets the short description.
	 
	 <value>The short description.</value>
	*/
	private String privateshortDescription;
	public final String getshortDescription()
	{
		return privateshortDescription;
	}
	public final void setshortDescription(String value)
	{
		privateshortDescription = value;
	}

	/** 
	 Gets or sets the overview.
	 
	 <value>The overview.</value>
	*/
	private String privateoverview;
	public final String getoverview()
	{
		return privateoverview;
	}
	public final void setoverview(String value)
	{
		privateoverview = value;
	}

	/** 
	 Gets or sets a value indicating whether this instance is premium.
	 
	 <value><c>true</c> if this instance is premium; otherwise, <c>false</c>.</value>
	*/
	private boolean privateisPremium;
	public final boolean getisPremium()
	{
		return privateisPremium;
	}
	public final void setisPremium(boolean value)
	{
		privateisPremium = value;
	}

	/** 
	 Gets or sets a value indicating whether this instance is adult only content.
	 
	 <value><c>true</c> if this instance is adult; otherwise, <c>false</c>.</value>
	*/
	private boolean privateadult;
	public final boolean getadult()
	{
		return privateadult;
	}
	public final void setadult(boolean value)
	{
		privateadult = value;
	}

	/** 
	 Gets or sets the rich desc URL.
	 
	 <value>The rich desc URL.</value>
	*/
	private String privaterichDescUrl;
	public final String getrichDescUrl()
	{
		return privaterichDescUrl;
	}
	public final void setrichDescUrl(String value)
	{
		privaterichDescUrl = value;
	}

	/** 
	 Gets or sets the thumb image.
	 
	 <value>The thumb image.</value>
	*/
	private String privatethumbImage;
	public final String getthumbImage()
	{
		return privatethumbImage;
	}
	public final void setthumbImage(String value)
	{
		privatethumbImage = value;
	}

	/** 
	 Gets or sets the preview image.
	 
	 <value>The preview image.</value>
	*/
	private String privatepreviewImage;
	public final String getpreviewImage()
	{
		return privatepreviewImage;
	}
	public final void setpreviewImage(String value)
	{
		privatepreviewImage = value;
	}

	/** 
	 Gets or sets the type.
	 
	 <value>The type.</value>
	*/
	private PackageType privatetype = PackageType.values()[0];
	public final PackageType gettype()
	{
		return privatetype;
	}
	public final void settype(PackageType value)
	{
		privatetype = value;
	}

	/** 
	 Gets or sets the target filename.
	 
	 <value>The target filename.</value>
	*/
	private String privatetargetFilename;
	public final String gettargetFilename()
	{
		return privatetargetFilename;
	}
	public final void settargetFilename(String value)
	{
		privatetargetFilename = value;
	}

	/** 
	 Gets or sets the owner.
	 
	 <value>The owner.</value>
	*/
	private String privateowner;
	public final String getowner()
	{
		return privateowner;
	}
	public final void setowner(String value)
	{
		privateowner = value;
	}

	/** 
	 Gets or sets the category.
	 
	 <value>The category.</value>
	*/
	private String privatecategory;
	public final String getcategory()
	{
		return privatecategory;
	}
	public final void setcategory(String value)
	{
		privatecategory = value;
	}

	/** 
	 Gets or sets the catalog tile color.
	 
	 <value>The owner.</value>
	*/
	private String privatetileColor;
	public final String gettileColor()
	{
		return privatetileColor;
	}
	public final void settileColor(String value)
	{
		privatetileColor = value;
	}

	/** 
	 Gets or sets the feature id of this package (if premium).
	 
	 <value>The feature id.</value>
	*/
	private String privatefeatureId;
	public final String getfeatureId()
	{
		return privatefeatureId;
	}
	public final void setfeatureId(String value)
	{
		privatefeatureId = value;
	}

	/** 
	 Gets or sets the registration info for this package (if premium).
	 
	 <value>The registration info.</value>
	*/
	private String privateregInfo;
	public final String getregInfo()
	{
		return privateregInfo;
	}
	public final void setregInfo(String value)
	{
		privateregInfo = value;
	}

	/** 
	 Gets or sets the price for this package (if premium).
	 
	 <value>The price.</value>
	*/
	private float privateprice;
	public final float getprice()
	{
		return privateprice;
	}
	public final void setprice(float value)
	{
		privateprice = value;
	}

	/** 
	 Gets or sets the target system for this plug-in (Server, MBTheater, MBClassic).
	 
	 <value>The target system.</value>
	*/
	private PackageTargetSystem privatetargetSystem = PackageTargetSystem.values()[0];
	public final PackageTargetSystem gettargetSystem()
	{
		return privatetargetSystem;
	}
	public final void settargetSystem(PackageTargetSystem value)
	{
		privatetargetSystem = value;
	}

	/** 
	 The guid of the assembly associated with this package (if a plug-in).
	 This is used to identify the proper item for automatic updates.
	 
	 <value>The name.</value>
	*/
	private String privateguid;
	public final String getguid()
	{
		return privateguid;
	}
	public final void setguid(String value)
	{
		privateguid = value;
	}

	/** 
	 Gets or sets the total number of ratings for this package.
	 
	 <value>The total ratings.</value>
	*/
	private int privatetotalRatings;
	public final int gettotalRatings()
	{
		return privatetotalRatings;
	}
	public final void settotalRatings(int value)
	{
		privatetotalRatings = value;
	}

	/** 
	 Gets or sets the average rating for this package .
	 
	 <value>The rating.</value>
	*/
	private float privateavgRating;
	public final float getavgRating()
	{
		return privateavgRating;
	}
	public final void setavgRating(float value)
	{
		privateavgRating = value;
	}

	/** 
	 Gets or sets whether or not this package is registered.
	 
	 <value>True if registered.</value>
	*/
	private boolean privateisRegistered;
	public final boolean getisRegistered()
	{
		return privateisRegistered;
	}
	public final void setisRegistered(boolean value)
	{
		privateisRegistered = value;
	}

	/** 
	 Gets or sets the expiration date for this package.
	 
	 <value>Expiration Date.</value>
	*/
	private java.util.Date privateexpDate = new java.util.Date(0);
	public final java.util.Date getexpDate()
	{
		return privateexpDate;
	}
	public final void setexpDate(java.util.Date value)
	{
		privateexpDate = value;
	}

	/** 
	 Gets or sets the versions.
	 
	 <value>The versions.</value>
	*/
	private java.util.ArrayList<PackageVersionInfo> privateversions;
	public final java.util.ArrayList<PackageVersionInfo> getversions()
	{
		return privateversions;
	}
	public final void setversions(java.util.ArrayList<PackageVersionInfo> value)
	{
		privateversions = value;
	}

	/** 
	 Initializes a new instance of the <see cref="PackageInfo"/> class.
	*/
	public PackageInfo()
	{
		setversions(new java.util.ArrayList<PackageVersionInfo>());
	}
}