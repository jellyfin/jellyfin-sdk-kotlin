package org.jellyfin.apiclient.interaction

import android.annotation.SuppressLint
import android.content.Context
import android.os.Build
import android.provider.Settings
import org.jellyfin.apiclient.model.DeviceInfo

@SuppressLint("HardwareIds")
public fun androidDevice(context: Context): DeviceInfo {
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
	)
}
