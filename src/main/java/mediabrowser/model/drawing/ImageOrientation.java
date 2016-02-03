package mediabrowser.model.drawing;

public enum ImageOrientation
{
	TopLeft(1),
	TopRight(2),
	BottomRight(3),
	BottomLeft(4),
	LeftTop(5),
	RightTop(6),
	RightBottom(7),
	LeftBottom(8);

	private int intValue;
	private static java.util.HashMap<Integer, ImageOrientation> mappings;
	private static java.util.HashMap<Integer, ImageOrientation> getMappings()
	{
		if (mappings == null)
		{
			synchronized (ImageOrientation.class)
			{
				if (mappings == null)
				{
					mappings = new java.util.HashMap<Integer, ImageOrientation>();
				}
			}
		}
		return mappings;
	}

	private ImageOrientation(int value)
	{
		intValue = value;
		getMappings().put(value, this);
	}

	public int getValue()
	{
		return intValue;
	}

	public static ImageOrientation forValue(int value)
	{
		return getMappings().get(value);
	}
}