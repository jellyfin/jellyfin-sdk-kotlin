package MediaBrowser.Model.Providers;

public class RemoteSubtitleInfo
{
	private String privateThreeLetterISOLanguageName;
	public final String getThreeLetterISOLanguageName()
	{
		return privateThreeLetterISOLanguageName;
	}
	public final void setThreeLetterISOLanguageName(String value)
	{
		privateThreeLetterISOLanguageName = value;
	}
	private String privateId;
	public final String getId()
	{
		return privateId;
	}
	public final void setId(String value)
	{
		privateId = value;
	}
	private String privateProviderName;
	public final String getProviderName()
	{
		return privateProviderName;
	}
	public final void setProviderName(String value)
	{
		privateProviderName = value;
	}
	private String privateName;
	public final String getName()
	{
		return privateName;
	}
	public final void setName(String value)
	{
		privateName = value;
	}
	private String privateFormat;
	public final String getFormat()
	{
		return privateFormat;
	}
	public final void setFormat(String value)
	{
		privateFormat = value;
	}
	private String privateAuthor;
	public final String getAuthor()
	{
		return privateAuthor;
	}
	public final void setAuthor(String value)
	{
		privateAuthor = value;
	}
	private String privateComment;
	public final String getComment()
	{
		return privateComment;
	}
	public final void setComment(String value)
	{
		privateComment = value;
	}
	private java.util.Date privateDateCreated = new java.util.Date();
	public final java.util.Date getDateCreated()
	{
		return privateDateCreated;
	}
	public final void setDateCreated(java.util.Date value)
	{
		privateDateCreated = value;
	}
	private Float privateCommunityRating = new Float();
	public final Float getCommunityRating()
	{
		return privateCommunityRating;
	}
	public final void setCommunityRating(Float value)
	{
		privateCommunityRating = value;
	}
	private Integer privateDownloadCount = new Integer();
	public final Integer getDownloadCount()
	{
		return privateDownloadCount;
	}
	public final void setDownloadCount(Integer value)
	{
		privateDownloadCount = value;
	}
	private Boolean privateIsHashMatch = new Boolean();
	public final Boolean getIsHashMatch()
	{
		return privateIsHashMatch;
	}
	public final void setIsHashMatch(Boolean value)
	{
		privateIsHashMatch = value;
	}
}