package MediaBrowser.Model.Dlna;

//C# TO JAVA CONVERTER WARNING: Unsigned integer types have no direct equivalent in Java:
//ORIGINAL LINE: [Flags] public enum DlnaFlags : ulong
public class DlnaFlags 
{
	public static final DlnaFlags BackgroundTransferMode = new DlnaFlags((1 << 22));
	public static final DlnaFlags ByteBasedSeek = new DlnaFlags((1 << 29));
	public static final DlnaFlags ConnectionStall = new DlnaFlags((1 << 21));
	public static final DlnaFlags DlnaV15 = new DlnaFlags((1 << 20));
	public static final DlnaFlags InteractiveTransferMode = new DlnaFlags((1 << 23));
	public static final DlnaFlags PlayContainer = new DlnaFlags((1 << 28));
	public static final DlnaFlags RtspPause = new DlnaFlags((1 << 25));
	public static final DlnaFlags S0Increase = new DlnaFlags((1 << 27));
	public static final DlnaFlags SenderPaced = new DlnaFlags((1L << 31));
	public static final DlnaFlags SnIncrease = new DlnaFlags((1 << 26));
	public static final DlnaFlags StreamingTransferMode = new DlnaFlags((1 << 24));
	public static final DlnaFlags TimeBasedSeek = new DlnaFlags((1 << 30));

	private int intValue;
	private static java.util.HashMap<Integer, DlnaFlags> mappings;
	private static java.util.HashMap<Integer, DlnaFlags> getMappings()
	{
		if (mappings == null)
		{
			synchronized (DlnaFlags.class)
			{
				if (mappings == null)
				{
					mappings = new java.util.HashMap<Integer, DlnaFlags>();
				}
			}
		}
		return mappings;
	}

	private DlnaFlags(int value)
	{
		intValue = value;
		synchronized (DlnaFlags.class)
		{
			getMappings().put(value, this);
		}
	}

	public int getValue()
	{
		return intValue;
	}

	public static DlnaFlags forValue(int value)
	{
		synchronized (DlnaFlags.class)
		{
			DlnaFlags enumObj = getMappings().get(value);
			if (enumObj == null)
			{
				return new DlnaFlags(value);
			}
			else
			{
				return enumObj;
			}
		}
	}
}