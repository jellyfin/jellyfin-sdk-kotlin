package MediaBrowser.Model.LiveTv;

import MediaBrowser.Model.Dto.*;
import MediaBrowser.Model.Extensions.*;

/** 
 Class RecordingGroupDto.
*/
//C# TO JAVA CONVERTER TODO TASK: Java annotations will not correspond to .NET attributes:
//ORIGINAL LINE: [DebuggerDisplay("Name = {Name}, Count = {RecordingCount}")] public class RecordingGroupDto : IHasPropertyChangedEvent, IHasServerId
public class RecordingGroupDto implements IHasPropertyChangedEvent, IHasServerId
{
	/** 
	 Gets or sets the server identifier.
	 
	 <value>The server identifier.</value>
	*/
	private String ServerId;
	public final String getServerId()
	{
		return ServerId;
	}
	public final void setServerId(String value)
	{
		ServerId = value;
	}

	/** 
	 Gets or sets the name.
	 
	 <value>The name.</value>
	*/
	private String Name;
	public final String getName()
	{
		return Name;
	}
	public final void setName(String value)
	{
		Name = value;
	}

	/** 
	 Gets or sets the identifier.
	 
	 <value>The identifier.</value>
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
	 Gets or sets the recording count.
	 
	 <value>The recording count.</value>
	*/
	private int RecordingCount;
	public final int getRecordingCount()
	{
		return RecordingCount;
	}
	public final void setRecordingCount(int value)
	{
		RecordingCount = value;
	}

//C# TO JAVA CONVERTER TODO TASK: Events are not available in Java:
//	public event PropertyChangedEventHandler PropertyChanged;
}