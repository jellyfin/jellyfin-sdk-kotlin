package mediabrowser.model.providers;

public class RemoteSubtitleInfo
{
	private String ThreeLetterISOLanguageName;
	public final String getThreeLetterISOLanguageName()
	{
		return ThreeLetterISOLanguageName;
	}
	public final void setThreeLetterISOLanguageName(String value)
	{
		ThreeLetterISOLanguageName = value;
	}
	private String Id;
	public final String getId()
	{
		return Id;
	}
	public final void setId(String value)
	{
		Id = value;
	}
	private String ProviderName;
	public final String getProviderName()
	{
		return ProviderName;
	}
	public final void setProviderName(String value)
	{
		ProviderName = value;
	}
	private String Name;
	public final String getName()
	{
		return Name;
	}
	public final void setName(String value)
	{
		Name = value;
	}
	private String Format;
	public final String getFormat()
	{
		return Format;
	}
	public final void setFormat(String value)
	{
		Format = value;
	}
	private String Author;
	public final String getAuthor()
	{
		return Author;
	}
	public final void setAuthor(String value)
	{
		Author = value;
	}
	private String Comment;
	public final String getComment()
	{
		return Comment;
	}
	public final void setComment(String value)
	{
		Comment = value;
	}
	private java.util.Date DateCreated = null;
	public final java.util.Date getDateCreated()
	{
		return DateCreated;
	}
	public final void setDateCreated(java.util.Date value)
	{
		DateCreated = value;
	}
	private Float CommunityRating = null;
	public final Float getCommunityRating()
	{
		return CommunityRating;
	}
	public final void setCommunityRating(Float value)
	{
		CommunityRating = value;
	}
	private Integer DownloadCount = null;
	public final Integer getDownloadCount()
	{
		return DownloadCount;
	}
	public final void setDownloadCount(Integer value)
	{
		DownloadCount = value;
	}
	private Boolean IsHashMatch = null;
	public final Boolean getIsHashMatch()
	{
		return IsHashMatch;
	}
	public final void setIsHashMatch(Boolean value)
	{
		IsHashMatch = value;
	}
}