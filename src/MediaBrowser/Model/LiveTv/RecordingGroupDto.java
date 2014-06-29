package MediaBrowser.Model.LiveTv;

import MediaBrowser.Model.Extensions.*;

/** 
 Class RecordingGroupDto.
*/
//C# TO JAVA CONVERTER TODO TASK: Java annotations will not correspond to .NET attributes:
//ORIGINAL LINE: [DebuggerDisplay("Name = {Name}, Count = {RecordingCount}")] public class RecordingGroupDto : IHasPropertyChangedEvent
public class RecordingGroupDto implements IHasPropertyChangedEvent
{
	/** 
	 Gets or sets the name.
	 
	 <value>The name.</value>
	*/
	private String privateName;
	public final String getName()
	{
		return privateName;
	}
	public final void setName(String value)
	{
		privateName = value;
	}

	/** 
	 Gets or sets the identifier.
	 
	 <value>The identifier.</value>
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
	 Gets or sets the recording count.
	 
	 <value>The recording count.</value>
	*/
	private int privateRecordingCount;
	public final int getRecordingCount()
	{
		return privateRecordingCount;
	}
	public final void setRecordingCount(int value)
	{
		privateRecordingCount = value;
	}

//C# TO JAVA CONVERTER TODO TASK: Events are not available in Java:
//	public event PropertyChangedEventHandler PropertyChanged;
}