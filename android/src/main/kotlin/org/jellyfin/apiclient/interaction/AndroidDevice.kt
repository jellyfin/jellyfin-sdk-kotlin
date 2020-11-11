package org.jellyfin.apiclient.interaction

import android.annotation.SuppressLint
import android.content.Context
import android.os.Build
import android.provider.Settings
import org.jellyfin.apiclient.interaction.device.IDevice

data class AndroidDevice(
	override val deviceId: String,
	override val deviceName: String
) : IDevice {
	companion object {
		private fun String.normalize() = replace("[^\\x20-\\x7E]".toRegex(), "").trim().replace("\\s{2,}".toRegex(), " ")

		@SuppressLint("HardwareIds")
		fun getAutomaticId(context: Context): String =
			Settings.Secure.getString(context.contentResolver, Settings.Secure.ANDROID_ID)

		fun getAutomaticName(context: Context): String {
			// Use name from device settings
			if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N_MR1) {
				return Settings.Global.getString(context.contentResolver, Settings.Global.DEVICE_NAME).normalize()
			}

			// Concatenate the name based on manufacturer and model
			val manufacturer = Build.MANUFACTURER.normalize()
			val model = Build.MODEL.normalize()

			return if (model.startsWith(manufacturer) || manufacturer.isBlank()) model
			else "$manufacturer $model"
		}

		@JvmStatic
		fun fromContext(context: Context) = AndroidDevice(
			deviceId = getAutomaticId(context),
			deviceName = getAutomaticName(context)
		)
	}
}
