package mediabrowser.model.livetv;

import mediabrowser.model.entities.*;

/** 
 Class SeriesTimerInfoDto.
*/
//C# TO JAVA CONVERTER TODO TASK: Java annotations will not correspond to .NET attributes:
//ORIGINAL LINE: [DebuggerDisplay("Name = {Name}")] public class SeriesTimerInfoDto : BaseTimerInfoDto
public class SeriesTimerInfoDto extends BaseTimerInfoDto
{
	public SeriesTimerInfoDto()
	{
		setImageTags(new java.util.HashMap<ImageType, String>());
		setDays(new java.util.ArrayList<String>());
		setType("SeriesTimer");
	}

	/** 
	 Gets or sets a value indicating whether [record any time].
	 
	 <value><c>true</c> if [record any time]; otherwise, <c>false</c>.</value>
	*/
	private boolean RecordAnyTime;
	public final boolean getRecordAnyTime()
	{
		return RecordAnyTime;
	}
	public final void setRecordAnyTime(boolean value)
	{
		RecordAnyTime = value;
	}

	private boolean SkipEpisodesInLibrary;
	public final boolean getSkipEpisodesInLibrary()
	{
		return SkipEpisodesInLibrary;
	}
	public final void setSkipEpisodesInLibrary(boolean value)
	{
		SkipEpisodesInLibrary = value;
	}

	/** 
	 Gets or sets a value indicating whether [record any channel].
	 
	 <value><c>true</c> if [record any channel]; otherwise, <c>false</c>.</value>
	*/
	private boolean RecordAnyChannel;
	public final boolean getRecordAnyChannel()
	{
		return RecordAnyChannel;
	}
	public final void setRecordAnyChannel(boolean value)
	{
		RecordAnyChannel = value;
	}

	private int KeepUpTo;
	public final int getKeepUpTo()
	{
		return KeepUpTo;
	}
	public final void setKeepUpTo(int value)
	{
		KeepUpTo = value;
	}

	/** 
	 Gets or sets a value indicating whether [record new only].
	 
	 <value><c>true</c> if [record new only]; otherwise, <c>false</c>.</value>
	*/
	private boolean RecordNewOnly;
	public final boolean getRecordNewOnly()
	{
		return RecordNewOnly;
	}
	public final void setRecordNewOnly(boolean value)
	{
		RecordNewOnly = value;
	}

	/** 
	 Gets or sets the days.
	 
	 <value>The days.</value>
	*/
	private java.util.ArrayList<String> Days;
	public final java.util.ArrayList<String> getDays()
	{
		return Days;
	}
	public final void setDays(java.util.ArrayList<String> value)
	{
		Days = value;
	}

	/** 
	 Gets or sets the day pattern.
	 
	 <value>The day pattern.</value>
	*/
	private DayPattern DayPattern = null;
	public final DayPattern getDayPattern()
	{
		return DayPattern;
	}
	public final void setDayPattern(DayPattern value)
	{
		DayPattern = value;
	}

	/** 
	 Gets or sets the image tags.
	 
	 <value>The image tags.</value>
	*/
	private java.util.HashMap<ImageType, String> ImageTags;
	public final java.util.HashMap<ImageType, String> getImageTags()
	{
		return ImageTags;
	}
	public final void setImageTags(java.util.HashMap<ImageType, String> value)
	{
		ImageTags = value;
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
}