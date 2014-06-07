package MediaBrowser.Model.FileOrganization;

public class FileOrganizationResult
{
	/** 
	 Gets or sets the result identifier.
	 
	 <value>The result identifier.</value>
	*/
	private String privateId;
	public final String getId()
	{
		return privateId;
	}
	public final void setId(String value)
	{
		privateId = value;
	}

	/** 
	 Gets or sets the original path.
	 
	 <value>The original path.</value>
	*/
	private String privateOriginalPath;
	public final String getOriginalPath()
	{
		return privateOriginalPath;
	}
	public final void setOriginalPath(String value)
	{
		privateOriginalPath = value;
	}

	/** 
	 Gets or sets the name of the original file.
	 
	 <value>The name of the original file.</value>
	*/
	private String privateOriginalFileName;
	public final String getOriginalFileName()
	{
		return privateOriginalFileName;
	}
	public final void setOriginalFileName(String value)
	{
		privateOriginalFileName = value;
	}

	/** 
	 Gets or sets the name of the extracted.
	 
	 <value>The name of the extracted.</value>
	*/
	private String privateExtractedName;
	public final String getExtractedName()
	{
		return privateExtractedName;
	}
	public final void setExtractedName(String value)
	{
		privateExtractedName = value;
	}

	/** 
	 Gets or sets the extracted year.
	 
	 <value>The extracted year.</value>
	*/
	private Integer privateExtractedYear = new Integer();
	public final Integer getExtractedYear()
	{
		return privateExtractedYear;
	}
	public final void setExtractedYear(Integer value)
	{
		privateExtractedYear = value;
	}

	/** 
	 Gets or sets the extracted season number.
	 
	 <value>The extracted season number.</value>
	*/
	private Integer privateExtractedSeasonNumber = new Integer();
	public final Integer getExtractedSeasonNumber()
	{
		return privateExtractedSeasonNumber;
	}
	public final void setExtractedSeasonNumber(Integer value)
	{
		privateExtractedSeasonNumber = value;
	}

	/** 
	 Gets or sets the extracted episode number.
	 
	 <value>The extracted episode number.</value>
	*/
	private Integer privateExtractedEpisodeNumber = new Integer();
	public final Integer getExtractedEpisodeNumber()
	{
		return privateExtractedEpisodeNumber;
	}
	public final void setExtractedEpisodeNumber(Integer value)
	{
		privateExtractedEpisodeNumber = value;
	}

	/** 
	 Gets or sets the extracted ending episode number.
	 
	 <value>The extracted ending episode number.</value>
	*/
	private Integer privateExtractedEndingEpisodeNumber = new Integer();
	public final Integer getExtractedEndingEpisodeNumber()
	{
		return privateExtractedEndingEpisodeNumber;
	}
	public final void setExtractedEndingEpisodeNumber(Integer value)
	{
		privateExtractedEndingEpisodeNumber = value;
	}

	/** 
	 Gets or sets the target path.
	 
	 <value>The target path.</value>
	*/
	private String privateTargetPath;
	public final String getTargetPath()
	{
		return privateTargetPath;
	}
	public final void setTargetPath(String value)
	{
		privateTargetPath = value;
	}

	/** 
	 Gets or sets the date.
	 
	 <value>The date.</value>
	*/
	private java.util.Date privateDate = new java.util.Date(0);
	public final java.util.Date getDate()
	{
		return privateDate;
	}
	public final void setDate(java.util.Date value)
	{
		privateDate = value;
	}

	/** 
	 Gets or sets the error message.
	 
	 <value>The error message.</value>
	*/
	private String privateStatusMessage;
	public final String getStatusMessage()
	{
		return privateStatusMessage;
	}
	public final void setStatusMessage(String value)
	{
		privateStatusMessage = value;
	}

	/** 
	 Gets or sets the status.
	 
	 <value>The status.</value>
	*/
	private FileSortingStatus privateStatus = FileSortingStatus.values()[0];
	public final FileSortingStatus getStatus()
	{
		return privateStatus;
	}
	public final void setStatus(FileSortingStatus value)
	{
		privateStatus = value;
	}

	/** 
	 Gets or sets the type.
	 
	 <value>The type.</value>
	*/
	private FileOrganizerType privateType = FileOrganizerType.values()[0];
	public final FileOrganizerType getType()
	{
		return privateType;
	}
	public final void setType(FileOrganizerType value)
	{
		privateType = value;
	}

	/** 
	 Gets or sets the duplicate paths.
	 
	 <value>The duplicate paths.</value>
	*/
	private java.util.ArrayList<String> privateDuplicatePaths;
	public final java.util.ArrayList<String> getDuplicatePaths()
	{
		return privateDuplicatePaths;
	}
	public final void setDuplicatePaths(java.util.ArrayList<String> value)
	{
		privateDuplicatePaths = value;
	}

	/** 
	 Gets or sets the size of the file.
	 
	 <value>The size of the file.</value>
	*/
	private long privateFileSize;
	public final long getFileSize()
	{
		return privateFileSize;
	}
	public final void setFileSize(long value)
	{
		privateFileSize = value;
	}

	public FileOrganizationResult()
	{
		setDuplicatePaths(new java.util.ArrayList<String>());
	}
}