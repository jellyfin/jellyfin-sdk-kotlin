package org.jellyfin.sdk.model.socket

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
public enum class GeneralCommandType {
	@SerialName("MoveUp")
	MoveUp,

	@SerialName("MoveDown")
	MoveDown,

	@SerialName("MoveLeft")
	MoveLeft,

	@SerialName("MoveRight")
	MoveRight,

	@SerialName("PageUp")
	PageUp,

	@SerialName("PageDown")
	PageDown,

	@SerialName("PreviousLetter")
	PreviousLetter,

	@SerialName("NextLetter")
	NextLetter,

	@SerialName("ToggleOsd")
	ToggleOsd,

	@SerialName("ToggleContextMenu")
	ToggleContextMenu,

	@SerialName("Select")
	Select,

	@SerialName("Back")
	Back,

	@SerialName("TakeScreenshot")
	TakeScreenshot,

	@SerialName("SendKey")
	SendKey,

	@SerialName("SendString")
	SendString,

	@SerialName("GoHome")
	GoHome,

	@SerialName("GoToSettings")
	GoToSettings,

	@SerialName("VolumeUp")
	VolumeUp,

	@SerialName("VolumeDown")
	VolumeDown,

	@SerialName("Mute")
	Mute,

	@SerialName("Unmute")
	Unmute,

	@SerialName("ToggleMute")
	ToggleMute,

	@SerialName("SetVolume")
	SetVolume,

	@SerialName("SetAudioStreamIndex")
	SetAudioStreamIndex,

	@SerialName("SetSubtitleStreamIndex")
	SetSubtitleStreamIndex,

	@SerialName("ToggleFullscreen")
	ToggleFullscreen,

	@SerialName("DisplayContent")
	DisplayContent,

	@SerialName("GoToSearch")
	GoToSearch,

	@SerialName("DisplayMessage")
	DisplayMessage,

	@SerialName("SetRepeatMode")
	SetRepeatMode,

	@SerialName("ChannelUp")
	ChannelUp,

	@SerialName("ChannelDown")
	ChannelDown,

	@SerialName("SetMaxStreamingBitrate")
	SetMaxStreamingBitrate,

	@SerialName("Guide")
	Guide,

	@SerialName("ToggleStats")
	ToggleStats,

	@SerialName("PlayMediaSource")
	PlayMediaSource,

	@SerialName("PlayTrailers")
	PlayTrailers,
}
