package mediabrowser.model.mediainfo;

import mediabrowser.model.dlna.*;

public class PlaybackInfoRequest
{
	private DeviceProfile DeviceProfile;
	public final DeviceProfile getDeviceProfile()
	{
		return DeviceProfile;
	}
	public final void setDeviceProfile(DeviceProfile value)
	{
		DeviceProfile = value;
	}
}