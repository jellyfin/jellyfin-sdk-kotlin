package org.jellyfin.sdk.interaction

import android.annotation.SuppressLint
import android.content.Context
import android.os.Build
import android.provider.Settings
import org.jellyfin.sdk.model.DeviceInfo

@SuppressLint("HardwareIds")
public fun androidDevice(context: Context): DeviceInfo {
	val id = Settings.Secure.getString(context.contentResolver, Settings.Secure.ANDROID_ID)

	// Ensure device name has no special characters or fallback to manufacturer and model else the connection will fail
	val testDeviceName = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N_MR1) {
		val deviceName = Settings.Global.getString(context.contentResolver, Settings.Global.DEVICE_NAME)
		if (deviceName.matches("([^\\x20-\\x7E])".toRegex())) {
			deviceName
		} else {
			null
		}
	} else {
		null
	}

	// Use name from device settings
	val name = if (testDeviceName != null) {
		testDeviceName
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
	)
}
