package MediaBrowser.Model.Dlna;

public class DeviceIdentification
{
	/** 
	 Gets or sets the name of the friendly.
	 
	 <value>The name of the friendly.</value>
	*/
	private String privateFriendlyName;
	public final String getFriendlyName()
	{
		return privateFriendlyName;
	}
	public final void setFriendlyName(String value)
	{
		privateFriendlyName = value;
	}
	/** 
	 Gets or sets the model number.
	 
	 <value>The model number.</value>
	*/
	private String privateModelNumber;
	public final String getModelNumber()
	{
		return privateModelNumber;
	}
	public final void setModelNumber(String value)
	{
		privateModelNumber = value;
	}
	/** 
	 Gets or sets the serial number.
	 
	 <value>The serial number.</value>
	*/
	private String privateSerialNumber;
	public final String getSerialNumber()
	{
		return privateSerialNumber;
	}
	public final void setSerialNumber(String value)
	{
		privateSerialNumber = value;
	}
	/** 
	 Gets or sets the name of the model.
	 
	 <value>The name of the model.</value>
	*/
	private String privateModelName;
	public final String getModelName()
	{
		return privateModelName;
	}
	public final void setModelName(String value)
	{
		privateModelName = value;
	}
	/** 
	 Gets or sets the model description.
	 
	 <value>The model description.</value>
	*/
	private String privateModelDescription;
	public final String getModelDescription()
	{
		return privateModelDescription;
	}
	public final void setModelDescription(String value)
	{
		privateModelDescription = value;
	}
	/** 
	 Gets or sets the device description.
	 
	 <value>The device description.</value>
	*/
	private String privateDeviceDescription;
	public final String getDeviceDescription()
	{
		return privateDeviceDescription;
	}
	public final void setDeviceDescription(String value)
	{
		privateDeviceDescription = value;
	}
	/** 
	 Gets or sets the model URL.
	 
	 <value>The model URL.</value>
	*/
	private String privateModelUrl;
	public final String getModelUrl()
	{
		return privateModelUrl;
	}
	public final void setModelUrl(String value)
	{
		privateModelUrl = value;
	}
	/** 
	 Gets or sets the manufacturer.
	 
	 <value>The manufacturer.</value>
	*/
	private String privateManufacturer;
	public final String getManufacturer()
	{
		return privateManufacturer;
	}
	public final void setManufacturer(String value)
	{
		privateManufacturer = value;
	}
	/** 
	 Gets or sets the manufacturer URL.
	 
	 <value>The manufacturer URL.</value>
	*/
	private String privateManufacturerUrl;
	public final String getManufacturerUrl()
	{
		return privateManufacturerUrl;
	}
	public final void setManufacturerUrl(String value)
	{
		privateManufacturerUrl = value;
	}
	/** 
	 Gets or sets the headers.
	 
	 <value>The headers.</value>
	*/
	private HttpHeaderInfo[] privateHeaders;
	public final HttpHeaderInfo[] getHeaders()
	{
		return privateHeaders;
	}
	public final void setHeaders(HttpHeaderInfo[] value)
	{
		privateHeaders = value;
	}

	public DeviceIdentification()
	{
		setHeaders(new HttpHeaderInfo[] {});
	}
}