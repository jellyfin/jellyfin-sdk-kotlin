package org.jellyfin.sdk.android

import android.annotation.SuppressLint
import android.content.Context
import android.os.Build
import android.provider.Settings
import org.jellyfin.sdk.model.DeviceInfo

private fun Context.getDeviceName(): String {
	// Use name from device settings
	if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N_MR1) {
		val name = Settings.Global.getString(contentResolver, Settings.Global.DEVICE_NAME)
		if (!name.isNullOrBlank()) return name
	}

	// Concatenate the name based on manufacturer and model
	val manufacturer = Build.MANUFACTURER
	val model = Build.MODEL

	return if (model.startsWith(manufacturer) || manufacturer.isBlank()) model
	else "$manufacturer $model"
}

public fun androidDevice(context: Context): DeviceInfo {
	@SuppressLint("HardwareIds")
	val id = Settings.Secure.getString(context.contentResolver, Settings.Secure.ANDROID_ID)
	val name = context.getDeviceName()

	return DeviceInfo(
		id = id,
		name = name
	)
}
