package org.jellyfin.apiclient.model.devices;

public class ContentUploadHistory
{
	private String DeviceId;
	public final String getDeviceId()
	{
		return DeviceId;
	}
	public final void setDeviceId(String value)
	{
		DeviceId = value;
	}
	private java.util.ArrayList<LocalFileInfo> FilesUploaded;
	public final java.util.ArrayList<LocalFileInfo> getFilesUploaded()
	{
		return FilesUploaded;
	}
	public final void setFilesUploaded(java.util.ArrayList<LocalFileInfo> value)
	{
		FilesUploaded = value;
	}

	public ContentUploadHistory()
	{
		setFilesUploaded(new java.util.ArrayList<LocalFileInfo>());
	}
}