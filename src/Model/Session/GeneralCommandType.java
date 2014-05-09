package MediaBrowser.Model.Session;

/** 
 This exists simply to identify a set of known commands.
*/
public enum GeneralCommandType
{
	MoveUp(0),
	MoveDown(1),
	MoveLeft(2),
	MoveRight(3),
	PageUp(4),
	PageDown(5),
	PreviousLetter(6),
	NextLetter(7),
	ToggleOsd(8),
	ToggleContextMenu(9),
	Select(10),
	Back(11),
	TakeScreenshot(12),
	SendKey(13),
	SendString(14),
	GoHome(15),
	GoToSettings(16),
	VolumeUp(17),
	VolumeDown(18),
	Mute(19),
	Unmute(20),
	ToggleMute(21),
	SetVolume(22),
	SetAudioStreamIndex(23),
	SetSubtitleStreamIndex(24),
	ToggleFullscreen(25),
	DisplayContent(26),
	GoToSearch(27),
	DisplayMessage(28);

	private int intValue;
	private static java.util.HashMap<Integer, GeneralCommandType> mappings;
	private static java.util.HashMap<Integer, GeneralCommandType> getMappings()
	{
		if (mappings == null)
		{
			synchronized (GeneralCommandType.class)
			{
				if (mappings == null)
				{
					mappings = new java.util.HashMap<Integer, GeneralCommandType>();
				}
			}
		}
		return mappings;
	}

	private GeneralCommandType(int value)
	{
		intValue = value;
		getMappings().put(value, this);
	}

	public int getValue()
	{
		return intValue;
	}

	public static GeneralCommandType forValue(int value)
	{
		return getMappings().get(value);
	}
}