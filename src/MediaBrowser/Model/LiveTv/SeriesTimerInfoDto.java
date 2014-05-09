package MediaBrowser.Model.LiveTv;

import MediaBrowser.Model.Entities.*;

//C# TO JAVA CONVERTER TODO TASK: Java annotations will not correspond to .NET attributes:
//ORIGINAL LINE: [DebuggerDisplay("Name = {Name}")] public class SeriesTimerInfoDto : BaseTimerInfoDto
public class SeriesTimerInfoDto extends BaseTimerInfoDto
{
	/** 
	 Gets or sets a value indicating whether [record any time].
	 
	 <value><c>true</c> if [record any time]; otherwise, <c>false</c>.</value>
	*/
	private boolean privateRecordAnyTime;
	public final boolean getRecordAnyTime()
	{
		return privateRecordAnyTime;
	}
	public final void setRecordAnyTime(boolean value)
	{
		privateRecordAnyTime = value;
	}

	/** 
	 Gets or sets a value indicating whether [record any channel].
	 
	 <value><c>true</c> if [record any channel]; otherwise, <c>false</c>.</value>
	*/
	private boolean privateRecordAnyChannel;
	public final boolean getRecordAnyChannel()
	{
		return privateRecordAnyChannel;
	}
	public final void setRecordAnyChannel(boolean value)
	{
		privateRecordAnyChannel = value;
	}

	/** 
	 Gets or sets a value indicating whether [record new only].
	 
	 <value><c>true</c> if [record new only]; otherwise, <c>false</c>.</value>
	*/
	private boolean privateRecordNewOnly;
	public final boolean getRecordNewOnly()
	{
		return privateRecordNewOnly;
	}
	public final void setRecordNewOnly(boolean value)
	{
		privateRecordNewOnly = value;
	}

	/** 
	 Gets or sets the days.
	 
	 <value>The days.</value>
	*/
	private java.util.ArrayList<DayOfWeek> privateDays;
	public final java.util.ArrayList<DayOfWeek> getDays()
	{
		return privateDays;
	}
	public final void setDays(java.util.ArrayList<DayOfWeek> value)
	{
		privateDays = value;
	}

	/** 
	 Gets or sets the day pattern.
	 
	 <value>The day pattern.</value>
	*/
	private DayPattern privateDayPattern = new DayPattern();
	public final DayPattern getDayPattern()
	{
		return privateDayPattern;
	}
	public final void setDayPattern(DayPattern value)
	{
		privateDayPattern = value;
	}

	/** 
	 Gets or sets the image tags.
	 
	 <value>The image tags.</value>
	*/
	private java.util.HashMap<ImageType, String> privateImageTags;
	public final java.util.HashMap<ImageType, String> getImageTags()
	{
		return privateImageTags;
	}
	public final void setImageTags(java.util.HashMap<ImageType, String> value)
	{
		privateImageTags = value;
	}

	/** 
	 Gets a value indicating whether this instance has primary image.
	 
	 <value><c>true</c> if this instance has primary image; otherwise, <c>false</c>.</value>
	*/
//C# TO JAVA CONVERTER TODO TASK: Java annotations will not correspond to .NET attributes:
//ORIGINAL LINE: [IgnoreDataMember] public bool HasPrimaryImage
	public final boolean getHasPrimaryImage()
	{
		return getImageTags() != null && getImageTags().containsKey(ImageType.Primary);
	}

	public SeriesTimerInfoDto()
	{
		setImageTags(new java.util.HashMap<ImageType, String>());
		setDays(new java.util.ArrayList<DayOfWeek>());
	}
}