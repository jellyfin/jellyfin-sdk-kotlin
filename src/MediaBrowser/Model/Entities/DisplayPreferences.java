package MediaBrowser.Model.Entities;

import MediaBrowser.Model.Drawing.*;

/** 
 Defines the display preferences for any item that supports them (usually Folders)
*/
public class DisplayPreferences implements INotifyPropertyChanged
{
	/** 
	 Occurs when [property changed].
	*/
//C# TO JAVA CONVERTER TODO TASK: Events are not available in Java:
//	public event PropertyChangedEventHandler PropertyChanged;

	/** 
	 The image scale
	*/
	private static final double ImageScale = .9;

	/** 
	 Initializes a new instance of the <see cref="DisplayPreferences" /> class.
	*/
	public DisplayPreferences()
	{
		setRememberIndexing(false);
		setPrimaryImageHeight(250);
		setPrimaryImageWidth(250);
		setShowBackdrop(true);
		setCustomPrefs(new java.util.HashMap<String, String>());
	}

	/** 
	 Gets or sets the user id.
	 
	 <value>The user id.</value>
	*/
	private String privateId;
	public final String getId()
	{
		return privateId;
	}
	public final void setId(String value)
	{
		privateId = value;
	}
	/** 
	 Gets or sets the type of the view.
	 
	 <value>The type of the view.</value>
	*/
	private String privateViewType;
	public final String getViewType()
	{
		return privateViewType;
	}
	public final void setViewType(String value)
	{
		privateViewType = value;
	}
	/** 
	 Gets or sets the sort by.
	 
	 <value>The sort by.</value>
	*/
	private String privateSortBy;
	public final String getSortBy()
	{
		return privateSortBy;
	}
	public final void setSortBy(String value)
	{
		privateSortBy = value;
	}
	/** 
	 Gets or sets the index by.
	 
	 <value>The index by.</value>
	*/
	private String privateIndexBy;
	public final String getIndexBy()
	{
		return privateIndexBy;
	}
	public final void setIndexBy(String value)
	{
		privateIndexBy = value;
	}
	/** 
	 Gets or sets a value indicating whether [remember indexing].
	 
	 <value><c>true</c> if [remember indexing]; otherwise, <c>false</c>.</value>
	*/
	private boolean privateRememberIndexing;
	public final boolean getRememberIndexing()
	{
		return privateRememberIndexing;
	}
	public final void setRememberIndexing(boolean value)
	{
		privateRememberIndexing = value;
	}
	/** 
	 Gets or sets the height of the primary image.
	 
	 <value>The height of the primary image.</value>
	*/
	private int privatePrimaryImageHeight;
	public final int getPrimaryImageHeight()
	{
		return privatePrimaryImageHeight;
	}
	public final void setPrimaryImageHeight(int value)
	{
		privatePrimaryImageHeight = value;
	}
	/** 
	 Gets or sets the width of the primary image.
	 
	 <value>The width of the primary image.</value>
	*/
	private int privatePrimaryImageWidth;
	public final int getPrimaryImageWidth()
	{
		return privatePrimaryImageWidth;
	}
	public final void setPrimaryImageWidth(int value)
	{
		privatePrimaryImageWidth = value;
	}
	/** 
	 Gets or sets the custom prefs.
	 
	 <value>The custom prefs.</value>
	*/
	private java.util.HashMap<String, String> privateCustomPrefs;
	public final java.util.HashMap<String, String> getCustomPrefs()
	{
		return privateCustomPrefs;
	}
	public final void setCustomPrefs(java.util.HashMap<String, String> value)
	{
		privateCustomPrefs = value;
	}
	/** 
	 Gets or sets the scroll direction.
	 
	 <value>The scroll direction.</value>
	*/
	private ScrollDirection privateScrollDirection = getScrollDirection().values()[0];
	public final ScrollDirection getScrollDirection()
	{
		return privateScrollDirection;
	}
	public final void setScrollDirection(ScrollDirection value)
	{
		privateScrollDirection = value;
	}
	/** 
	 Gets or sets a value indicating whether to show backdrops on this item.
	 
	 <value><c>true</c> if showing backdrops; otherwise, <c>false</c>.</value>
	*/
	private boolean privateShowBackdrop;
	public final boolean getShowBackdrop()
	{
		return privateShowBackdrop;
	}
	public final void setShowBackdrop(boolean value)
	{
		privateShowBackdrop = value;
	}
	/** 
	 Gets or sets a value indicating whether [remember sorting].
	 
	 <value><c>true</c> if [remember sorting]; otherwise, <c>false</c>.</value>
	*/
	private boolean privateRememberSorting;
	public final boolean getRememberSorting()
	{
		return privateRememberSorting;
	}
	public final void setRememberSorting(boolean value)
	{
		privateRememberSorting = value;
	}
	/** 
	 Gets or sets the sort order.
	 
	 <value>The sort order.</value>
	*/
	private SortOrder privateSortOrder = getSortOrder().values()[0];
	public final SortOrder getSortOrder()
	{
		return privateSortOrder;
	}
	public final void setSortOrder(SortOrder value)
	{
		privateSortOrder = value;
	}
	/** 
	 Gets or sets a value indicating whether [show sidebar].
	 
	 <value><c>true</c> if [show sidebar]; otherwise, <c>false</c>.</value>
	*/
	private boolean privateShowSidebar;
	public final boolean getShowSidebar()
	{
		return privateShowSidebar;
	}
	public final void setShowSidebar(boolean value)
	{
		privateShowSidebar = value;
	}

	/** 
	 Increases the size of the image.
	*/
	public final void IncreaseImageSize()
	{
		double newWidth = getPrimaryImageWidth() / ImageScale;

		ImageSize size = DrawingUtils.Resize(getPrimaryImageWidth(), getPrimaryImageHeight(), newWidth, null, null, null);

		setPrimaryImageWidth((int)size.getWidth());
		setPrimaryImageHeight((int)size.getHeight());
	}

	/** 
	 Decreases the size of the image.
	*/
	public final void DecreaseImageSize()
	{
		ImageSize size = DrawingUtils.Scale(getPrimaryImageWidth(), getPrimaryImageHeight(), ImageScale);

		setPrimaryImageWidth((int)size.getWidth());
		setPrimaryImageHeight((int)size.getHeight());
	}
}