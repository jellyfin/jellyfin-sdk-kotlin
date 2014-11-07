package mediabrowser.model.updates;

/** 
 Class PackageInfo
*/
public class PackageInfo
{
	/** 
	 The internal id of this package.
	 
	 <value>The id.</value>
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
	 Gets or sets the name.
	 
	 <value>The name.</value>
	*/
	private String name;
	public final String getname()
	{
		return name;
	}
	public final void setname(String value)
	{
		name = value;
	}

	/** 
	 Gets or sets the short description.
	 
	 <value>The short description.</value>
	*/
	private String shortDescription;
	public final String getshortDescription()
	{
		return shortDescription;
	}
	public final void setshortDescription(String value)
	{
		shortDescription = value;
	}

	/** 
	 Gets or sets the overview.
	 
	 <value>The overview.</value>
	*/
	private String overview;
	public final String getoverview()
	{
		return overview;
	}
	public final void setoverview(String value)
	{
		overview = value;
	}

	/** 
	 Gets or sets a value indicating whether this instance is premium.
	 
	 <value><c>true</c> if this instance is premium; otherwise, <c>false</c>.</value>
	*/
	private boolean isPremium;
	public final boolean getisPremium()
	{
		return isPremium;
	}
	public final void setisPremium(boolean value)
	{
		isPremium = value;
	}

	/** 
	 Gets or sets a value indicating whether this instance is adult only content.
	 
	 <value><c>true</c> if this instance is adult; otherwise, <c>false</c>.</value>
	*/
	private boolean adult;
	public final boolean getadult()
	{
		return adult;
	}
	public final void setadult(boolean value)
	{
		adult = value;
	}

	/** 
	 Gets or sets the rich desc URL.
	 
	 <value>The rich desc URL.</value>
	*/
	private String richDescUrl;
	public final String getrichDescUrl()
	{
		return richDescUrl;
	}
	public final void setrichDescUrl(String value)
	{
		richDescUrl = value;
	}

	/** 
	 Gets or sets the thumb image.
	 
	 <value>The thumb image.</value>
	*/
	private String thumbImage;
	public final String getthumbImage()
	{
		return thumbImage;
	}
	public final void setthumbImage(String value)
	{
		thumbImage = value;
	}

	/** 
	 Gets or sets the preview image.
	 
	 <value>The preview image.</value>
	*/
	private String previewImage;
	public final String getpreviewImage()
	{
		return previewImage;
	}
	public final void setpreviewImage(String value)
	{
		previewImage = value;
	}

	/** 
	 Gets or sets the type.
	 
	 <value>The type.</value>
	*/
	private PackageType type = PackageType.values()[0];
	public final PackageType gettype()
	{
		return type;
	}
	public final void settype(PackageType value)
	{
		type = value;
	}

	/** 
	 Gets or sets the target filename.
	 
	 <value>The target filename.</value>
	*/
	private String targetFilename;
	public final String gettargetFilename()
	{
		return targetFilename;
	}
	public final void settargetFilename(String value)
	{
		targetFilename = value;
	}

	/** 
	 Gets or sets the owner.
	 
	 <value>The owner.</value>
	*/
	private String owner;
	public final String getowner()
	{
		return owner;
	}
	public final void setowner(String value)
	{
		owner = value;
	}

	/** 
	 Gets or sets the category.
	 
	 <value>The category.</value>
	*/
	private String category;
	public final String getcategory()
	{
		return category;
	}
	public final void setcategory(String value)
	{
		category = value;
	}

	/** 
	 Gets or sets the catalog tile color.
	 
	 <value>The owner.</value>
	*/
	private String tileColor;
	public final String gettileColor()
	{
		return tileColor;
	}
	public final void settileColor(String value)
	{
		tileColor = value;
	}

	/** 
	 Gets or sets the feature id of this package (if premium).
	 
	 <value>The feature id.</value>
	*/
	private String featureId;
	public final String getfeatureId()
	{
		return featureId;
	}
	public final void setfeatureId(String value)
	{
		featureId = value;
	}

	/** 
	 Gets or sets the registration info for this package (if premium).
	 
	 <value>The registration info.</value>
	*/
	private String regInfo;
	public final String getregInfo()
	{
		return regInfo;
	}
	public final void setregInfo(String value)
	{
		regInfo = value;
	}

	/** 
	 Gets or sets the price for this package (if premium).
	 
	 <value>The price.</value>
	*/
	private float price;
	public final float getprice()
	{
		return price;
	}
	public final void setprice(float value)
	{
		price = value;
	}

	/** 
	 Gets or sets the target system for this plug-in (Server, MBTheater, MBClassic).
	 
	 <value>The target system.</value>
	*/
	private PackageTargetSystem targetSystem = PackageTargetSystem.values()[0];
	public final PackageTargetSystem gettargetSystem()
	{
		return targetSystem;
	}
	public final void settargetSystem(PackageTargetSystem value)
	{
		targetSystem = value;
	}

	/** 
	 The guid of the assembly associated with this package (if a plug-in).
	 This is used to identify the proper item for automatic updates.
	 
	 <value>The name.</value>
	*/
	private String guid;
	public final String getguid()
	{
		return guid;
	}
	public final void setguid(String value)
	{
		guid = value;
	}

	/** 
	 Gets or sets the total number of ratings for this package.
	 
	 <value>The total ratings.</value>
	*/
	private int totalRatings;
	public final int gettotalRatings()
	{
		return totalRatings;
	}
	public final void settotalRatings(int value)
	{
		totalRatings = value;
	}

	/** 
	 Gets or sets the average rating for this package .
	 
	 <value>The rating.</value>
	*/
	private float avgRating;
	public final float getavgRating()
	{
		return avgRating;
	}
	public final void setavgRating(float value)
	{
		avgRating = value;
	}

	/** 
	 Gets or sets whether or not this package is registered.
	 
	 <value>True if registered.</value>
	*/
	private boolean isRegistered;
	public final boolean getisRegistered()
	{
		return isRegistered;
	}
	public final void setisRegistered(boolean value)
	{
		isRegistered = value;
	}

	/** 
	 Gets or sets the expiration date for this package.
	 
	 <value>Expiration Date.</value>
	*/
	private java.util.Date expDate = new java.util.Date(0);
	public final java.util.Date getexpDate()
	{
		return expDate;
	}
	public final void setexpDate(java.util.Date value)
	{
		expDate = value;
	}

	/** 
	 Gets or sets the versions.
	 
	 <value>The versions.</value>
	*/
	private java.util.ArrayList<PackageVersionInfo> versions;
	public final java.util.ArrayList<PackageVersionInfo> getversions()
	{
		return versions;
	}
	public final void setversions(java.util.ArrayList<PackageVersionInfo> value)
	{
		versions = value;
	}

	/** 
	 Initializes a new instance of the <see cref="PackageInfo"/> class.
	*/
	public PackageInfo()
	{
		setversions(new java.util.ArrayList<PackageVersionInfo>());
	}
}