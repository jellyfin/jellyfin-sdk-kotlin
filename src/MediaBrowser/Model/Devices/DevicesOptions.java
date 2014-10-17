package MediaBrowser.Model.Devices;

public class DevicesOptions
{
	private String[] EnabledCameraUploadDevices;
	public final String[] getEnabledCameraUploadDevices()
	{
		return EnabledCameraUploadDevices;
	}
	public final void setEnabledCameraUploadDevices(String[] value)
	{
		EnabledCameraUploadDevices = value;
	}
	private String CameraUploadPath;
	public final String getCameraUploadPath()
	{
		return CameraUploadPath;
	}
	public final void setCameraUploadPath(String value)
	{
		CameraUploadPath = value;
	}
	private boolean EnableCameraUploadSubfolders;
	public final boolean getEnableCameraUploadSubfolders()
	{
		return EnableCameraUploadSubfolders;
	}
	public final void setEnableCameraUploadSubfolders(boolean value)
	{
		EnableCameraUploadSubfolders = value;
	}

	public DevicesOptions()
	{
		setEnabledCameraUploadDevices(new String[] { });
	}
}