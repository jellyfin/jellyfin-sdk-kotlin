package org.jellyfin.apiclient.interaction

import android.annotation.SuppressLint
import android.content.Context
import android.os.Build
import android.provider.Settings
import org.jellyfin.apiclient.model.DeviceInfo

/**
 * Helper function used in [androidDevice] to normalize device names:
 *
 * - Removes special characters from the name.
 * - Trims the whitespace at the start and end of the name.
 *
 * Returns a copy of the device with the normalized name.
 */
fun DeviceInfo.normalize() = copy(
	name = name.replace("[^\\w\\s]".toRegex(), "").trim()
)

@SuppressLint("HardwareIds")
fun androidDevice(context: Context): DeviceInfo {
	val id = Settings.Secure.getString(context.contentResolver, Settings.Secure.ANDROID_ID)

	// Use name from device settings
	val name = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N_MR1) {
		Settings.Global.getString(context.contentResolver, Settings.Global.DEVICE_NAME)
	} else {
		// Concatenate the name based on manufacturer and model
		val manufacturer = Build.MANUFACTURER
		val model = Build.MODEL

		if (model.startsWith(manufacturer) || manufacturer.isBlank()) model
		else "$manufacturer $model"
	}

	return DeviceInfo(
		id = id,
		name = name
	).normalize()
}
