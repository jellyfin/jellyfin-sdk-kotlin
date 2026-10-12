package org.jellyfin.sdk.ios

import org.jellyfin.sdk.model.DeviceInfo
import platform.Foundation.NSLocale
import platform.Foundation.NSUserDefaults
import platform.Foundation.NSUUID
import platform.Foundation.preferredLanguages
import platform.UIKit.UIDevice

private const val DEVICE_ID_KEY = "org.jellyfin.sdk.deviceId"

/**
 * Create a [DeviceInfo] for the current iOS device. Uses `identifierForVendor` as id, or a generated id stored in
 * [NSUserDefaults] when it is not available.
 */
public fun iosDevice(): DeviceInfo {
	val device = UIDevice.currentDevice
	val defaults = NSUserDefaults.standardUserDefaults

	val id = device.identifierForVendor?.UUIDString
		?: defaults.stringForKey(DEVICE_ID_KEY)
		?: NSUUID().UUIDString.also { defaults.setObject(it, DEVICE_ID_KEY) }

	@Suppress("UNCHECKED_CAST")
	val languages = (NSLocale.preferredLanguages as List<String>)

	return DeviceInfo(
		id = id,
		name = device.name,
		languages = languages,
	)
}
