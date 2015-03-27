package mediabrowser.model.mediainfo;

import mediabrowser.model.dlna.*;
import mediabrowser.model.dto.*;

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
	private MediaSourceInfo MediaSource;
	public final MediaSourceInfo getMediaSource()
	{
		return MediaSource;
	}
	public final void setMediaSource(MediaSourceInfo value)
	{
		MediaSource = value;
	}
}