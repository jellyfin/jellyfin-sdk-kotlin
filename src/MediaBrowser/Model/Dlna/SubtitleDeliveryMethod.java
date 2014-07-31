package MediaBrowser.Model.Dlna;

import MediaBrowser.Model.Drawing.*;
import MediaBrowser.Model.Dto.*;
import MediaBrowser.Model.Entities.*;
import MediaBrowser.Model.Extensions.*;
import MediaBrowser.Model.MediaInfo.*;

public enum SubtitleDeliveryMethod
{
	/** 
	 The encode
	*/
	Encode(0),
	/** 
	 Internal format is supported natively
	*/
	Direct(1),
	/** 
	 The embed
	*/
	Embed(2),
	/** 
	 The external
	*/
	External(3);

	private int intValue;
	private static java.util.HashMap<Integer, SubtitleDeliveryMethod> mappings;
	private static java.util.HashMap<Integer, SubtitleDeliveryMethod> getMappings()
	{
		if (mappings == null)
		{
			synchronized (SubtitleDeliveryMethod.class)
			{
				if (mappings == null)
				{
					mappings = new java.util.HashMap<Integer, SubtitleDeliveryMethod>();
				}
			}
		}
		return mappings;
	}

	private SubtitleDeliveryMethod(int value)
	{
		intValue = value;
		getMappings().put(value, this);
	}

	public int getValue()
	{
		return intValue;
	}

	public static SubtitleDeliveryMethod forValue(int value)
	{
		return getMappings().get(value);
	}
}