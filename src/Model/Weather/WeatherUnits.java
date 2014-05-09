package MediaBrowser.Model.Weather;

/** 
 Enum WeatherUnits
*/
public enum WeatherUnits
{
	/** 
	 The fahrenheit
	*/
	Fahrenheit,
	/** 
	 The celsius
	*/
	Celsius;

	public int getValue()
	{
		return this.ordinal();
	}

	public static WeatherUnits forValue(int value)
	{
		return values()[value];
	}
}