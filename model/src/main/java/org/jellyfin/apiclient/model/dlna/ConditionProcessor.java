package org.jellyfin.apiclient.model.dlna;

import org.jellyfin.apiclient.model.extensions.*;
import org.jellyfin.apiclient.model.mediainfo.*;

public class ConditionProcessor
{
	public final boolean IsVideoConditionSatisfied(ProfileCondition condition, Integer width, Integer height, Integer bitDepth, Integer videoBitrate, String videoProfile, Double videoLevel, Float videoFramerate, Integer packetLength, TransportStreamTimestamp timestamp, Boolean isAnamorphic, Integer refFrames, Integer numVideoStreams, Integer numAudioStreams, String videoCodecTag, Boolean isAvc)
	{
		switch (condition.getProperty())
		{
			case IsAnamorphic:
				return IsConditionSatisfied(condition, isAnamorphic);
			case IsAvc:
				return IsConditionSatisfied(condition, isAvc);
			case VideoFramerate:
				return IsConditionSatisfied(condition, videoFramerate);
			case VideoLevel:
				return IsConditionSatisfied(condition, videoLevel);
			case VideoProfile:
				return IsConditionSatisfied(condition, videoProfile);
			case VideoCodecTag:
				return IsConditionSatisfied(condition, videoCodecTag);
			case PacketLength:
				return IsConditionSatisfied(condition, packetLength);
			case VideoBitDepth:
				return IsConditionSatisfied(condition, bitDepth);
			case VideoBitrate:
				return IsConditionSatisfied(condition, videoBitrate);
			case Height:
				return IsConditionSatisfied(condition, height);
			case Width:
				return IsConditionSatisfied(condition, width);
			case RefFrames:
				return IsConditionSatisfied(condition, refFrames);
			case NumAudioStreams:
				return IsConditionSatisfied(condition, numAudioStreams);
			case NumVideoStreams:
				return IsConditionSatisfied(condition, numVideoStreams);
			case VideoTimestamp:
				return IsConditionSatisfied(condition, timestamp);
			default:
				return true;
		}
	}

	public final boolean IsImageConditionSatisfied(ProfileCondition condition, Integer width, Integer height)
	{
		switch (condition.getProperty())
		{
			case Height:
				return IsConditionSatisfied(condition, height);
			case Width:
				return IsConditionSatisfied(condition, width);
			default:
				throw new IllegalArgumentException("Unexpected condition on image file: " + condition.getProperty());
		}
	}

	public final boolean IsAudioConditionSatisfied(ProfileCondition condition, Integer audioChannels, Integer audioBitrate)
	{
		switch (condition.getProperty())
		{
			case AudioBitrate:
				return IsConditionSatisfied(condition, audioBitrate);
			case AudioChannels:
				return IsConditionSatisfied(condition, audioChannels);
			default:
				throw new IllegalArgumentException("Unexpected condition on audio file: " + condition.getProperty());
		}
	}

	public final boolean IsVideoAudioConditionSatisfied(ProfileCondition condition, Integer audioChannels, Integer audioBitrate, String audioProfile, Boolean isSecondaryTrack)
	{
		switch (condition.getProperty())
		{
			case AudioProfile:
				return IsConditionSatisfied(condition, audioProfile);
			case AudioBitrate:
				return IsConditionSatisfied(condition, audioBitrate);
			case AudioChannels:
				return IsConditionSatisfied(condition, audioChannels);
			case IsSecondaryAudio:
				return IsConditionSatisfied(condition, isSecondaryTrack);
			default:
				throw new IllegalArgumentException("Unexpected condition on audio file: " + condition.getProperty());
		}
	}

	private boolean IsConditionSatisfied(ProfileCondition condition, Integer currentValue) throws NumberFormatException
	{
		if (currentValue == null)
		{
			// If the value is unknown, it satisfies if not marked as required
			return !condition.getIsRequired();
		}

		int expected = Integer.parseInt(condition.getValue());
		switch (condition.getCondition())
		{
			case Equals:
				return currentValue.equals(expected);
			case GreaterThanEqual:
				return currentValue >= expected;
			case LessThanEqual:
				return currentValue <= expected;
			case NotEquals:
				return !currentValue.equals(expected);
			default:
				throw new IllegalStateException("Unexpected ProfileConditionType");
		}
	}

	private boolean IsConditionSatisfied(ProfileCondition condition, String currentValue)
	{
		if (currentValue == null || currentValue.isEmpty())
		{
			// If the value is unknown, it satisfies if not marked as required
			return !condition.getIsRequired();
		}

		String expected = condition.getValue();
		switch (condition.getCondition())
		{
			case EqualsAny:
				return ListHelper.ContainsIgnoreCase(expected.split("[|]", -1), currentValue);
			case Equals:
				return StringHelper.equalsIgnoreCase(currentValue, expected);
			case NotEquals:
				return !StringHelper.equalsIgnoreCase(currentValue, expected);
			default:
				throw new IllegalStateException("Unexpected ProfileConditionType");
		}
	}

	private boolean IsConditionSatisfied(ProfileCondition condition, Boolean currentValue)
	{
		if (currentValue == null)
		{
			// If the value is unknown, it satisfies if not marked as required
			return !condition.getIsRequired();
		}

		boolean expected = Boolean.parseBoolean(condition.getValue());
		switch (condition.getCondition())
		{
			case Equals:
				return currentValue == expected;
			case NotEquals:
				return currentValue != expected;
			default:
				throw new IllegalStateException("Unexpected ProfileConditionType");
		}
	}

	private boolean IsConditionSatisfied(ProfileCondition condition, Float currentValue) throws NumberFormatException
	{
		if (currentValue == null)
		{
			// If the value is unknown, it satisfies if not marked as required
			return !condition.getIsRequired();
		}

		float expected = Float.parseFloat(condition.getValue());
		switch (condition.getCondition())
		{
			case Equals:
				return currentValue.equals(expected);
			case GreaterThanEqual:
				return currentValue >= expected;
			case LessThanEqual:
				return currentValue <= expected;
			case NotEquals:
				return !currentValue.equals(expected);
			default:
				throw new IllegalStateException("Unexpected ProfileConditionType");
		}
	}

	private boolean IsConditionSatisfied(ProfileCondition condition, Double currentValue) throws NumberFormatException
	{
		if (currentValue == null)
		{
			// If the value is unknown, it satisfies if not marked as required
			return !condition.getIsRequired();
		}

		double expected = Double.parseDouble(condition.getValue());
		switch (condition.getCondition())
		{
			case Equals:
				return currentValue.equals(expected);
			case GreaterThanEqual:
				return currentValue >= expected;
			case LessThanEqual:
				return currentValue <= expected;
			case NotEquals:
				return !currentValue.equals(expected);
			default:
				throw new IllegalStateException("Unexpected ProfileConditionType");
		}
	}

	private boolean IsConditionSatisfied(ProfileCondition condition, TransportStreamTimestamp timestamp)
	{
		if (timestamp == null)
		{
			// If the value is unknown, it satisfies if not marked as required
			return !condition.getIsRequired();
		}

		TransportStreamTimestamp expected = TransportStreamTimestamp.valueOf(condition.getValue());
		switch (condition.getCondition())
		{
			case Equals:
				return timestamp == expected;
			case NotEquals:
				return timestamp != expected;
			default:
				throw new IllegalStateException("Unexpected ProfileConditionType");
		}
	}
}
