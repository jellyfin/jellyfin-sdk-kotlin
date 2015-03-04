package mediabrowser.model.sync;

public class LocalItemQuery
{
	private String ServerId;
	public final String getServerId()
	{
		return ServerId;
	}
	public final void setServerId(String value)
	{
		ServerId = value;
	}
	private String AlbumArtist;
	public final String getAlbumArtist()
	{
		return AlbumArtist;
	}
	public final void setAlbumArtist(String value)
	{
		AlbumArtist = value;
	}
	private String AlbumId;
	public final String getAlbumId()
	{
		return AlbumId;
	}
	public final void setAlbumId(String value)
	{
		AlbumId = value;
	}
	private String SeriesId;
	public final String getSeriesId()
	{
		return SeriesId;
	}
	public final void setSeriesId(String value)
	{
		SeriesId = value;
	}
	private String Type;
	public final String getType()
	{
		return Type;
	}
	public final void setType(String value)
	{
		Type = value;
	}
	private String MediaType;
	public final String getMediaType()
	{
		return MediaType;
	}
	public final void setMediaType(String value)
	{
		MediaType = value;
	}
	private String[] ExcludeTypes;
	public final String[] getExcludeTypes()
	{
		return ExcludeTypes;
	}
	public final void setExcludeTypes(String[] value)
	{
		ExcludeTypes = value;
	}

	public LocalItemQuery()
	{
		setExcludeTypes(new String[] { });
	}
}