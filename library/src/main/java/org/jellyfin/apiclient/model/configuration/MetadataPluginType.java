package org.jellyfin.apiclient.model.configuration;

/** 
 Enum MetadataPluginType
*/
public enum MetadataPluginType
{
	LocalImageProvider,
	ImageFetcher,
	ImageSaver,
	LocalMetadataProvider,
	MetadataFetcher,
	MetadataSaver;

	public int getValue()
	{
		return this.ordinal();
	}

	public static MetadataPluginType forValue(int value)
	{
		return values()[value];
	}
}