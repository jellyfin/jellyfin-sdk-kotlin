package MediaBrowser.Model.Entities;

import MediaBrowser.Model.Drawing.*;
import MediaBrowser.Model.Extensions.*;

/** 
 Defines the display preferences for any item that supports them (usually Folders)
*/
public class DisplayPreferences implements IHasPropertyChangedEvent
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
	private String Id;
	public final String getId()
	{
		return Id;
	}
	public final void setId(String value)
	{
		Id = value;
	}
	/** 
	 Gets or sets the type of the view.
	 
	 <value>The type of the view.</value>
	*/
	private String ViewType;
	public final String getViewType()
	{
		return ViewType;
	}
	public final void setViewType(String value)
	{
		ViewType = value;
	}
	/** 
	 Gets or sets the sort by.
	 
	 <value>The sort by.</value>
	*/
	private String SortBy;
	public final String getSortBy()
	{
		return SortBy;
	}
	public final void setSortBy(String value)
	{
		SortBy = value;
	}
	/** 
	 Gets or sets the index by.
	 
	 <value>The index by.</value>
	*/
	private String IndexBy;
	public final String getIndexBy()
	{
		return IndexBy;
	}
	public final void setIndexBy(String value)
	{
		IndexBy = value;
	}
	/** 
	 Gets or sets a value indicating whether [remember indexing].
	 
	 <value><c>true</c> if [remember indexing]; otherwise, <c>false</c>.</value>
	*/
	private boolean RememberIndexing;
	public final boolean getRememberIndexing()
	{
		return RememberIndexing;
	}
	public final void setRememberIndexing(boolean value)
	{
		RememberIndexing = value;
	}
	/** 
	 Gets or sets the height of the primary image.
	 
	 <value>The height of the primary image.</value>
	*/
	private int PrimaryImageHeight;
	public final int getPrimaryImageHeight()
	{
		return PrimaryImageHeight;
	}
	public final void setPrimaryImageHeight(int value)
	{
		PrimaryImageHeight = value;
	}
	/** 
	 Gets or sets the width of the primary image.
	 
	 <value>The width of the primary image.</value>
	*/
	private int PrimaryImageWidth;
	public final int getPrimaryImageWidth()
	{
		return PrimaryImageWidth;
	}
	public final void setPrimaryImageWidth(int value)
	{
		PrimaryImageWidth = value;
	}
	/** 
	 Gets or sets the custom prefs.
	 
	 <value>The custom prefs.</value>
	*/
	private java.util.HashMap<String, String> CustomPrefs;
	public final java.util.HashMap<String, String> getCustomPrefs()
	{
		return CustomPrefs;
	}
	public final void setCustomPrefs(java.util.HashMap<String, String> value)
	{
		CustomPrefs = value;
	}
	/** 
	 Gets or sets the scroll direction.
	 
	 <value>The scroll direction.</value>
	*/
	private ScrollDirection ScrollDirection = getScrollDirection().values()[0];
	public final ScrollDirection getScrollDirection()
	{
		return ScrollDirection;
	}
	public final void setScrollDirection(ScrollDirection value)
	{
		ScrollDirection = value;
	}
	/** 
	 Gets or sets a value indicating whether to show backdrops on this item.
	 
	 <value><c>true</c> if showing backdrops; otherwise, <c>false</c>.</value>
	*/
	private boolean ShowBackdrop;
	public final boolean getShowBackdrop()
	{
		return ShowBackdrop;
	}
	public final void setShowBackdrop(boolean value)
	{
		ShowBackdrop = value;
	}
	/** 
	 Gets or sets a value indicating whether [remember sorting].
	 
	 <value><c>true</c> if [remember sorting]; otherwise, <c>false</c>.</value>
	*/
	private boolean RememberSorting;
	public final boolean getRememberSorting()
	{
		return RememberSorting;
	}
	public final void setRememberSorting(boolean value)
	{
		RememberSorting = value;
	}
	/** 
	 Gets or sets the sort order.
	 
	 <value>The sort order.</value>
	*/
	private SortOrder SortOrder = getSortOrder().values()[0];
	public final SortOrder getSortOrder()
	{
		return SortOrder;
	}
	public final void setSortOrder(SortOrder value)
	{
		SortOrder = value;
	}
	/** 
	 Gets or sets a value indicating whether [show sidebar].
	 
	 <value><c>true</c> if [show sidebar]; otherwise, <c>false</c>.</value>
	*/
	private boolean ShowSidebar;
	public final boolean getShowSidebar()
	{
		return ShowSidebar;
	}
	public final void setShowSidebar(boolean value)
	{
		ShowSidebar = value;
	}
	/** 
	 Gets or sets the client
	*/
	private String Client;
	public final String getClient()
	{
		return Client;
	}
	public final void setClient(String value)
	{
		Client = value;
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